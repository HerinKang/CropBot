package org.hd.cropbot.listeners;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.GenericMessageEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.user.UserTypingEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class EventListeners extends ListenerAdapter {

    farm farm = new farm(3);
    @Override
    public void onMessageReactionAdd(@NotNull MessageReactionAddEvent event) {
        //when user reacts to a message with an emoji
        User user = event.getUser();
        String emoji = event.getReaction().getReactionEmote().getAsReactionCode();
        String channelMention = event.getChannel().getAsMention();

        String message = user.getAsTag() + " thinks " + emoji + " is appropriate to use in " + channelMention + " channel...";
        event.getGuild().getDefaultChannel().sendMessage(message).queue();
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        String command = event.getName();
        if (command.equals("test")) {
            //gets data user passes into option argument (gets the message the user sends in this case)
            OptionMapping messageOption = event.getOption("message");
            String message = messageOption.getAsString();

            //check for null argument if using optional option

            //event.getChannel().sendMessage(message).queue();
            event.reply("your message was sent").setEphemeral(true).queue();
        } else if (command.equals("farm")) {
            event.reply(farm.displayFarm()).queue();
        } else if (command.equals("plant")) {
            farm.plantPlant(new plot(0,":seedling:",0,0));
            event.reply(farm.displayFarm()).queue();
        }
    }

    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {
        ArrayList<CommandData> commandData = new ArrayList<>();

        //test <message>
        OptionData testoption = new OptionData(OptionType.STRING, "message","a message the user sends",true);
        commandData.add(Commands.slash("test","test simple slash command").addOptions(testoption));

        commandData.add(Commands.slash("farm","open farm"));

        commandData.add(Commands.slash("plant","plant a plant"));

        event.getGuild().updateCommands().addCommands(commandData).queue();

    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();
        if (message.startsWith("I'm") || message.startsWith("i'm")) {
            String thing = message.substring(message.indexOf(" "));
            event.getChannel().sendMessage("hi" + thing + ", I'm cropbot!").queue();
        }
    }
}
