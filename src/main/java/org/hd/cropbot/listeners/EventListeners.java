package org.hd.cropbot.listeners;

import net.dv8tion.jda.api.EmbedBuilder;
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

import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class EventListeners extends ListenerAdapter {

    farm farm = new farm(3);
    ArrayList<plant> options = new ArrayList<>();
    ArrayList<String> things = new ArrayList<>();
    Inventory inventory = new Inventory(things);

    shop shop = new shop(25,3);
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime now;
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

        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("Welcome to CropBot!");
        eb.setColor(Color.GREEN);
        eb.setFooter("BoilerMake X - Herin Kang and Dylan Boyer");
        eb.addField("Here are a list of general commands:", "/farm - Displays your current farm, as is.\n" +
                "/plant - Plants a seedling in the first available spot. \n" +
                "/expand - Expands the farm by 1 row and 1 column. The new tiles become dirt.", false);
        eb.setImage("https://cdn.discordapp.com/attachments/1024123337900818533/1066178407953616956/discord_pfp.jpg");

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
            farm.plantPlant(1);
            event.reply(farm.displayFarm()).queue();
        } else if (command.equals("expand")) {
            farm.resizePlot();
            event.reply(farm.displayFarm()).queue();
        } else if (command.equals("help")) {
            event.getChannel().sendMessageEmbeds(eb.build()).queue();
        } else if (command.equals("shop")) {
            options.add(new plant(":sunflower:",30, 50));
            options.add(new plant(":tulip:",50, 100));
            shop.setOptions(options);
            shop.randomizeNewShop();
            event.reply(shop.displayShop()).queue();
        } else if (command.equals("buy")) {
            OptionMapping messageOption = event.getOption("pos");
            int boughtPos = messageOption.getAsInt();
            System.out.println(shop.getPlant(boughtPos).getName());
            shop.buyPlant(boughtPos);
            event.reply(shop.displayShop()).queue();
        } else if (command.equals("inventory")) {
            event.reply(inventory.getFormattedInventory()).queue();
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

        commandData.add(Commands.slash("expand", "expand current plot size by 1"));

        commandData.add(Commands.slash("help", "provides info on various CropBot commands"));

        commandData.add(Commands.slash("shop", "opens shop"));

        OptionData buyOption = new OptionData(OptionType.INTEGER, "pos","thing to be bought",true);
        commandData.add(Commands.slash("buy", "buys thing").addOptions(buyOption));

        commandData.add(Commands.slash("inventory", "opens inventory"));

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
