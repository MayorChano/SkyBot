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

package ml.duncte123.skybot.commands.`fun`

import me.duncte123.botcommons.messaging.EmbedUtils
import me.duncte123.botcommons.messaging.MessageUtils.sendEmbed
import me.duncte123.botcommons.web.WebUtils
import ml.duncte123.skybot.Author
import ml.duncte123.skybot.Settings
import ml.duncte123.skybot.objects.command.Command
import ml.duncte123.skybot.objects.command.CommandCategory
import ml.duncte123.skybot.objects.command.CommandContext
import ml.duncte123.skybot.utils.EarthUtils.Companion.sendRedditPost
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent
import java.util.*


@Author(nickname = "duncte123", author = "Duncan Sterken")
class JokeCommand : Command() {

    /*
     * This keeps track of where we are in the jokes
     */
    private val jokeIndex: MutableMap<String, Int>

    init {
        this.category = CommandCategory.FUN
        this.jokeIndex = TreeMap()
    }

    override fun executeCommand(ctx: CommandContext) {

        if (ctx.invoke == "meme") {
            WebUtils.ins.getJSONObject("https://api-to.get-a.life/meme").async {
                val url = it.getString("url")
                sendEmbed(ctx.event, EmbedUtils.embedImage(url))
            }

            return
        }

        when (ctx.random.nextInt(2)) {
            0 -> sendRedditPost("Jokes", jokeIndex, ctx.event)
            1 -> sendRanddomJoke(ctx.event)
        }

    }

    override fun help() = "See a funny joke. Dad's love them!\n" +
        "Usage: `${Settings.PREFIX}$name`"

    override fun getName() = "joke"

    override fun getAliases() = arrayOf("meme")

    private fun sendRanddomJoke(event: GuildMessageReceivedEvent) {
        WebUtils.ins.getJSONObject("https://icanhazdadjoke.com/").async {
            sendEmbed(event, EmbedUtils.embedMessage(it.getString("joke")))
        }
    }
}
