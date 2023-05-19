package com.simondmc.webdash.key;

import com.simondmc.webdash.WebDash;
import com.simondmc.webdash.config.Configs;
import com.simondmc.webdash.config.DataConfig;

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

        return true;
    }

    public static boolean disableKey() {
        // already off
        if (!enabled) return false;

        enabled = false;
        DataConfig.setKeyEnabled(false);

        return true;
    }

    public static void generateKey() {
        int length = 100;
        try {
            length = Configs.reloadAndGet("config.yml").getConfig().getInt("key-length");
        } catch (Exception e) {
            WebDash.logger.warning("Invalid key length in config.yml! Using default length of 100");
        }
        KeyGenerator keygen = new KeyGenerator(length);
        key = keygen.getKey();
        DataConfig.setKey(key);
    }

    public static boolean isEnabled() {
        return enabled;
    }

    public static String getKey() {
        return key;
    }

}
