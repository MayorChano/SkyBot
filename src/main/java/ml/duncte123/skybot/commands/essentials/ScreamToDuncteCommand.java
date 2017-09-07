package ml.duncte123.skybot.commands.essentials;

import ml.duncte123.skybot.objects.command.Command;
import ml.duncte123.skybot.utils.Config;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import org.apache.commons.lang3.StringUtils;

public class ScreamToDuncteCommand extends Command {
    /**
     * This is a check to see if the command is save to execute
     * @param args The command agruments
     * @param event a instance of {@link net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent GuildMessageReceivedEvent}
     * @return true if we are the command is safe to run
     */
    @Override
    public boolean called(String[] args, GuildMessageReceivedEvent event) {

        if(args.length < 1) {
            sendMsg(event, "WHAT THE HELL AM I SUPPOSED TO SCREAM???\nUsage: "+Config.prefix+"scream [words]");
            return false;
        }

        return true;
    }

    /**
     * This is the action of the command, the thing you want the command to to needs to be in here
     * @param args The command agruments
     * @param event a instance of {@link net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent GuildMessageReceivedEvent}
     */
    @Override
    public void action(String[] args, GuildMessageReceivedEvent event) {
        User duncte = event.getJDA().getUserById(Config.ownerId);
        String from = event.getAuthor().getName()+"#"+event.getAuthor().getDiscriminator()+"("+event.getAuthor().getId()+")";
        String message = StringUtils.join(args, " ");

        duncte.openPrivateChannel().queue(
                privateChannel -> privateChannel.sendMessage(from + " screams at you: " + message).queue()
        );

        sendMsg(event, "Scream has been send");
    }

    /**
     * The usage instructions of the command
     * @return a String
     */
    @Override
    public String help() {
        return "Use this to scream at the owner";
    }
}