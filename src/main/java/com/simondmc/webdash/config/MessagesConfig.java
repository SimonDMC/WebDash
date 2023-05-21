package com.simondmc.webdash.config;

import com.simondmc.webdash.key.KeyHandler;

public class MessagesConfig {
    private static final String CONFIG_NAME = "messages.yml";

    public static void init() {
        Configs.addOverwritable(CONFIG_NAME);
    }

    public static String get(String key) {
        String message;
        try {
            message = Configs.get(CONFIG_NAME).getConfig().getString(key);
        } catch (Exception e) {
            message = "§cNo message for " + key + " in messages.yml!";
        }
        return message;
    }
}
