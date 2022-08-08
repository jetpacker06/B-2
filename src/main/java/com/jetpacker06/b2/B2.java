package com.jetpacker06.b2;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class B2 {
    public static String KEY = System.getenv("B2DiscordBotKey");
    public static String PREFIX = "$";
    public static void main(String[] args) throws LoginException {
        JDABuilder builder = JDABuilder.createDefault(KEY);
        Commands.registerCommands();
        builder.addEventListeners(new Commands());
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.competing("WW4"));
        builder.enableIntents(GatewayIntent.MESSAGE_CONTENT);
        builder.build();
        log("Roger roger.");
    }
    public static void log(Object message) {
        System.out.println(message);
    }
}
