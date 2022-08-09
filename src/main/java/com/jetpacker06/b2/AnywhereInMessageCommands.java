package com.jetpacker06.b2;

import com.jetpacker06.b2.util.Util;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class AnywhereInMessageCommands extends ListenerAdapter {
    public static Map<String, Runnable> anywhereInMessageCommandsMap = new HashMap<>();
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();
        String[] splitMessage = message.split(" ");
        for (String s : splitMessage) {
            if (Util.isStringInList(s, anywhereInMessageCommandsMap.keySet().toArray(new String[0]))) {
                anywhereInMessageCommandsMap.get(s).run();
            }
        }
    }
    public static void addCommand(String command, Runnable runnable) {
        anywhereInMessageCommandsMap.put(command, runnable);
    }
    public static void registerAnywhereInMessageCommands() {
        addCommand("cracked", () -> {
            MessageReceivedEvent event = (MessageReceivedEvent) B2.recentEvent;
            event.getChannel().sendMessageEmbeds(Util.createImageEmbed("https://thumbs.gfycat.com/CircularEachBlackrhino-max-1mb.gif")).queue();
        });
    }
}
