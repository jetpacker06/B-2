package com.jetpacker06.b2;

import com.jetpacker06.b2.util.SlashCommandField;
import com.jetpacker06.b2.util.Util;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import static net.dv8tion.jda.api.interactions.commands.OptionType.BOOLEAN;
import static net.dv8tion.jda.api.interactions.commands.OptionType.INTEGER;

public class SlashCommands extends ListenerAdapter {
    public static Map<String, Runnable> slashCommandsMap = new HashMap<>();
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        String commandSent = event.getName();
        if (Util.isStringInList(commandSent, slashCommandsMap.keySet().toArray(new String[0]))) {
            slashCommandsMap.get(commandSent).run();
        }
    }
    public static void addCommand(CommandListUpdateAction commands, String name, String description, Runnable runnable, SlashCommandField... fields) {
        SlashCommandData command = Commands.slash(name, description);
        for (SlashCommandField field : fields) {
            command.addOption(field.optionType(), field.name(), field.description());
        }
        commands.addCommands(command);
        slashCommandsMap.put(name, runnable);
    }
    public static void registerSlashCommands(JDA jda) {
        CommandListUpdateAction commands = jda.updateCommands();
        addCommand(commands, "tostacks", "How many stacks of 64 are in this number?",
            () -> {
                SlashCommandInteractionEvent event = (SlashCommandInteractionEvent) B2.recentEvent;
                int stacksize = 64;
                if (event.getOption("stacks_to_16") != null) {
                    stacksize = event.getOption("stacks_to_16").getAsBoolean() ? 16 : 64;
                }
                int number = event.getOption("number").getAsInt();
                int numberOfStacks = 0;
                if (number < 0) {
                    event.reply("Must not be negative.").queue();
                    return;
                }
                while (number > stacksize) {
                    number -= stacksize;
                    numberOfStacks++;
                }
                String output = "";
                output += String.valueOf(event.getOption("number").getAsInt());
                output += " contains ";
                output += String.valueOf(numberOfStacks);
                output += numberOfStacks != 1 ? " stacks of " : " stack of ";
                output += String.valueOf(stacksize);
                output += " and ";
                output += String.valueOf(number);
                output += ".";
                event.reply(output).queue();

            },
            new SlashCommandField(INTEGER, "number", "Number to convert to stacks.", true),
            new SlashCommandField(BOOLEAN, "stacks_to_16", "Put true if it should use stacks of 16 instead of 64.", false)
        );
        addCommand(commands, "fromstacks", "Convert an amount of stacks to a total number of items.",
            () -> {
                SlashCommandInteractionEvent event = (SlashCommandInteractionEvent) B2.recentEvent;
                int stacksize = 64;
                int originalNumber = event.getOption("number").getAsInt();
                if (event.getOption("stacks_to_16") != null) {
                    stacksize = event.getOption("stacks_to_16").getAsBoolean() ? 16 : 64;
                }
                int outputint = originalNumber * stacksize;
                String output = "";
                output += String.valueOf(originalNumber);
                output += " stacks of ";
                output += String.valueOf(stacksize);
                output += " is ";
                output += String.valueOf(outputint);
                output += " total items.";
                event.reply(output).queue();

            },
            new SlashCommandField(INTEGER, "number", "Number to convert from stacks.", true),
            new SlashCommandField(BOOLEAN, "stacks_to_16", "Put true if it should use stacks of 16 instead of 64.", false)
        );
        addCommand(commands, "heheheha", "HEHEHEHA",
                () -> {
                    SlashCommandInteractionEvent event = (SlashCommandInteractionEvent) B2.recentEvent;
                    MessageEmbed embed = Util.blueBuilder().setImage("https://c.tenor.com/MOZ0kG5WZ2wAAAAC/heheheha-clash-royale.gif").build();
                    Message message = new MessageBuilder().setEmbeds(embed).build();
                    event.reply(message).queue();
                }
        );
        addCommand(commands, "grr", "GRR",
                () -> {
                    SlashCommandInteractionEvent event = (SlashCommandInteractionEvent) B2.recentEvent;
                    MessageEmbed embed = Util.blueBuilder().setImage("https://c.tenor.com/zzC5RuN0fzoAAAAM/clash-royale.gif").build();
                    Message message = new MessageBuilder().setEmbeds(embed).build();
                    event.reply(message).queue();
                }
        );
        addCommand(commands, "hehagrr", "50/50",
                () -> {
                    SlashCommandInteractionEvent event = (SlashCommandInteractionEvent) B2.recentEvent;
                    MessageEmbed embed = Util.blueBuilder().setImage(
                        (Math.random() < 0.5d) ? "https://c.tenor.com/MOZ0kG5WZ2wAAAAC/heheheha-clash-royale.gif" : "https://c.tenor.com/zzC5RuN0fzoAAAAM/clash-royale.gif"
                    ).build();
                    Message message = new MessageBuilder().setEmbeds(embed).build();
                    event.reply(message).queue();
                }
        );

        commands.queue();
    }
}
