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

package ml.duncte123.skybot.commands.guild.mod;

import me.duncte123.botcommons.messaging.MessageUtils;
import ml.duncte123.skybot.Author;
import ml.duncte123.skybot.Settings;
import ml.duncte123.skybot.objects.command.Command;
import ml.duncte123.skybot.objects.command.CommandCategory;
import ml.duncte123.skybot.objects.command.CommandContext;
import ml.duncte123.skybot.utils.ModerationUtils;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.exceptions.HierarchyException;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static ml.duncte123.skybot.utils.ModerationUtils.canInteract;

@Author(nickname = "duncte123", author = "Duncan Sterken")
public class SoftbanCommand extends Command {

    public SoftbanCommand() {
        this.category = CommandCategory.MOD_ADMIN;
    }

    @Override
    public void executeCommand(@NotNull CommandContext ctx) {

        final GuildMessageReceivedEvent event = ctx.getEvent();
        final List<String> args = ctx.getArgs();

        if (!event.getMember().hasPermission(Permission.KICK_MEMBERS)) {
            MessageUtils.sendMsg(event, "You need the kick members permission for this command, please contact your server administrator about this");
            return;
        }

        if (event.getMessage().getMentionedMembers().isEmpty() || args.isEmpty()) {
            MessageUtils.sendMsg(event, "Usage is " + Settings.PREFIX + getName() + " <@user> [Reason]");
            return;
        }

        final Member toBanMember = event.getMessage().getMentionedMembers().get(0);

        if (!canInteract(ctx.getMember(), toBanMember, "softban", ctx.getChannel())) {
            return;
        }

        try {
            final User toBan = toBanMember.getUser();
            if (toBan.equals(event.getAuthor()) &&
                !event.getGuild().getMember(event.getAuthor()).canInteract(event.getGuild().getMember(toBan))) {
                MessageUtils.sendMsg(event, "You are not permitted to perform this action.");
                return;
            }
            final String reason = StringUtils.join(args.subList(1, args.size()), " ");

            event.getGuild().getController().ban(toBan.getId(), 1, "Kicked by: " + event.getAuthor().getName() + "\nReason: " + reason).queue(
                nothing -> {
                    ModerationUtils.modLog(event.getAuthor(), toBan, "kicked", reason, ctx.getGuild());
                    MessageUtils.sendSuccess(event.getMessage());
                    event.getGuild().getController().unban(toBan.getId()).reason("(softban) Kicked by: " + event.getAuthor().getName()).queue();
                }
            );
        } catch (HierarchyException e) {
            //e.printStackTrace();
            MessageUtils.sendMsg(event, "I can't ban that member because his roles are above or equals to mine.");
        }
    }

    @Override
    public String help() {
        return "Kicks a user from the guild **(THIS WILL DELETE MESSAGES)**";
    }

    @Override
    public String getName() {
        return "softban";
    }
}
