package com.jetpacker06.b2;

import net.hypixel.api.HypixelAPI;
import net.hypixel.api.apache.ApacheHttpClient;
import net.hypixel.api.http.HypixelHttpClient;

import java.util.UUID;

public class HypixelExample {
    public static void main(String[] args) {
        HypixelHttpClient client = new ApacheHttpClient(UUID.fromString("your-api-key-here"));
        HypixelAPI hypixelAPI = new HypixelAPI(client);
        hypixelAPI.getPlayerByName("Hypixel")
                .exceptionally(throwable -> {
                    // Handle exceptions here
                    throwable.printStackTrace();
                    return null;
                })
                .thenAccept(System.out::println);
    }
}
