package com.simondmc.webdash.dashboard;

import com.simondmc.webdash.WebDash;
import com.simondmc.webdash.config.Configs;
import com.simondmc.webdash.config.DataConfig;
import com.simondmc.webdash.websocket.WSSHandler;

public class KeyHandler {

    private static boolean enabled;
    private static String key;

    public static void init() {
        enabled = DataConfig.getKeyEnabled();
        key = DataConfig.getKey();
    }

    public static boolean enableKey() {
        // already on
        if (enabled) return false;

        enabled = true;
        DataConfig.setKeyEnabled(true);

        // generate key if doesn't exist
        if (key == null) generateKey();

        // close all connections
        WSSHandler.closeAll();

        return true;
    }

    public static boolean disableKey() {
        // already off
        if (!enabled) return false;

        enabled = false;
        DataConfig.setKeyEnabled(false);

        // close all connections
        WSSHandler.closeAll();

        return true;
    }

    public static void generateKey() {
        int length = 200;
        boolean invalid = false;
        try {
            length = Configs.reloadAndGet("config.yml").getConfig().getInt("key-length");
        } catch (Exception e) {
            invalid = true;
        }
        if (length < 1 || length > 10000) {
            invalid = true;
        }
        if (invalid) {
            WebDash.logger.warning("Invalid key length in config.yml! Using default length of 200");
            length = 200;
        }
        KeyGenerator keygen = new KeyGenerator(length);
        key = keygen.getKey();
        DataConfig.setKey(key);

        // close all connections if key is enabled
        if (enabled) {
            WSSHandler.closeAll();
        }
    }

    public static boolean isEnabled() {
        return enabled;
    }

    public static String getKey() {
        return key;
    }

}
