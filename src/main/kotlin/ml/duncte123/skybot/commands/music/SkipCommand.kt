/*
 * Skybot, a multipurpose discord bot
 *      Copyright (C) 2017 - 2019  Duncan "duncte123" Sterken & Ramid "ramidzkh" Khan
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package ml.duncte123.skybot.commands.music

import me.duncte123.botcommons.messaging.MessageUtils
import ml.duncte123.skybot.Author
import ml.duncte123.skybot.objects.TrackUserData
import ml.duncte123.skybot.objects.command.CommandContext
import ml.duncte123.skybot.objects.command.MusicCommand

@Author(nickname = "Sanduhr32", author = "Maurice R S")
class SkipCommand : MusicCommand() {
    override fun executeCommand(ctx: CommandContext) {

        val event = ctx.event
        val args = ctx.args

        if (!channelChecks(event, ctx.audioUtils)) {
            return
        }

        val mng = getMusicManager(event.guild, ctx.audioUtils)
        val scheduler = mng.scheduler
        mng.latestChannel = -1

        if (mng.player.playingTrack == null) {
            MessageUtils.sendMsg(event, "The player is not playing.")
            return
        }

        val count = if (args.isNotEmpty()) {
            if (!args[0].matches("\\d{1,10}".toRegex())) {
                1
            } else {
                args[0].toInt().coerceIn(1, scheduler.queue.size.coerceAtLeast(1))
            }
        } else {
            1
        }

        repeat(count) {
            scheduler.nextTrack()
        }

        if (mng.player.playingTrack != null) {
            val trackUserData = mng.player.playingTrack.userData

            val user = if (trackUserData != null && trackUserData is TrackUserData) {
                val userData = trackUserData
                ctx.jda.getUserById(userData.userId)
            } else {
                ctx.author
            }


            MessageUtils.sendMsg(event, "Successfully skipped $count tracks.\n" +
                "Now playing: ${mng.player.playingTrack.info.title}\n" +
                "Requester: ${user.asTag}")
        } else {
            MessageUtils.sendMsg(event, "Successfully skipped $count tracks.\n" +
                "Queue is now empty.")
        }
        mng.latestChannel = event.channel.idLong
    }

    override fun help(): String = "Skips the current track."

    override fun getName(): String = "skip"

    override fun getAliases(): Array<String> = arrayOf("next", "nexttrack", "skiptrack")
}
