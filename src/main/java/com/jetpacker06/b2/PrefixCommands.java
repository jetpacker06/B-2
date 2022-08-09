package com.jetpacker06.b2;

import com.jetpacker06.b2.util.Util;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class PrefixCommands extends ListenerAdapter {
    public static Map<String, Runnable> prefixCommandsMap = new HashMap<>();

    @Override
    public void onGenericEvent(@NotNull GenericEvent event) {
        B2.recentEvent = event;
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (event.getMessage().getAuthor().isBot()) return;
        String message = event.getMessage().getContentRaw();
        if (Util.isStringInList(message, getCommandsList())) {
            prefixCommandsMap.get(message).run();
        }
    }
    public static void addCommand(String command, Runnable task) {
        prefixCommandsMap.put(B2.PREFIX + command, task);
    }
    public static String[] getCommandsList() {
        return prefixCommandsMap.keySet().toArray(new String[0]);
    }
    public static void registerPrefixCommands() {
        addCommand("test", () -> {
            Util.sendMessage("oi");
        });
        addCommand("hellothere", () -> {
            Util.sendMessage("general kenobi");
        });
    }
}
