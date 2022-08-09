package com.jetpacker06.b2.util;

import com.jetpacker06.b2.B2;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Objects;

public class Util {
    public static boolean isStringInList(String thing, String[] list) {
        boolean flag = false;
        for (String s : list) {
            if (Objects.equals(s, thing)) {
                flag = true;
                break;
            }
        }
        return flag;
    }
    public static void sendMessage(String message) {
        ((MessageReceivedEvent) B2.recentEvent).getChannel().sendMessage(message).queue();
    }
    public static EmbedBuilder blueBuilder() {
        return new EmbedBuilder().setColor(0x000066);
    }
    public static MessageEmbed createImageEmbed(String url) {
        return blueBuilder().setImage(url).build();
    }
}
