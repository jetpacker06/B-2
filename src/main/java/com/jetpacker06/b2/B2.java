package com.jetpacker06.b2;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class B2 {
    public static String BOT_KEY = System.getenv("B2DiscordBotKey");
    public static String HYPIXEL_KEY = System.getenv("HypixelAPIKey");
    public static String PREFIX = "$";
    public static GenericEvent recentEvent;
    public static void main(String[] args) throws LoginException {
        PrefixCommands.registerPrefixCommands();
        AnywhereInMessageCommands.registerAnywhereInMessageCommands();
        JDA jda = JDABuilder.createDefault(BOT_KEY)
        .addEventListeners(new PrefixCommands(), new SlashCommands(), new AnywhereInMessageCommands())
        .setStatus(OnlineStatus.ONLINE)
        .setActivity(Activity.competing("WW4"))
        .enableIntents(GatewayIntent.MESSAGE_CONTENT)
        .build();
        log("Roger roger.");

        SlashCommands.registerSlashCommands(jda);
    }
    public static void log(Object message) {
        System.out.println(message);
    }
}
