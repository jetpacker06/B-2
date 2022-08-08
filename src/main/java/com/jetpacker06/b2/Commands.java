package com.jetpacker06.b2;

import com.jetpacker06.b2.util.Util;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class Commands extends ListenerAdapter {
    public static GenericEvent recentEvent;
    public static Map<String, Runnable> MapOfCommands = new HashMap<>();

    @Override
    public void onGenericEvent(@NotNull GenericEvent event) {
        recentEvent = event;
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (event.getMessage().getAuthor().isBot()) return;
        String message = event.getMessage().getContentRaw();
        if (Util.isStringInList(message, getCommandsList())) {
            MapOfCommands.get(message).run();
        }
    }
    public static void addCommand(String command, Runnable task) {
        MapOfCommands.put(B2.PREFIX + command, task);
    }
    public static String[] getCommandsList() {
        return MapOfCommands.keySet().toArray(new String[0]);
    }
    public static void sendMessage(String message) {
        ((MessageReceivedEvent) recentEvent).getChannel().sendMessage(message).queue();
    }
    public static void registerCommands() {
        addCommand("test", () -> {
            sendMessage("oi");
        });
        addCommand("hellothere", () -> {
            sendMessage("general kenobi");
        });
    }
}
