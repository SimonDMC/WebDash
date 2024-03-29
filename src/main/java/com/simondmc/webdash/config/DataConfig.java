package com.simondmc.webdash.config;

import com.simondmc.webdash.data.KeyHandler;
import com.simondmc.webdash.data.NotificationHandler;
import com.simondmc.webdash.data.StatusHandler;

import java.util.List;

public class DataConfig {
    private static final String CONFIG_NAME = "data.yml";

    public static void init() {
        Configs.add(CONFIG_NAME);
        KeyHandler.init();
        StatusHandler.init();
        NotificationHandler.init();
    }

    public static boolean getEnabled() {
        boolean enabled;
        try {
            enabled = Configs.get(CONFIG_NAME).getConfig().getBoolean("enabled");
        } catch (Exception e) {
            enabled = true;
        }
        return enabled;
    }

    public static void setEnabled(Boolean enabled) {
        Configs.get(CONFIG_NAME).getConfig().set("enabled", enabled);
        Configs.get(CONFIG_NAME).save();
    }

    public static String getKey() {
        String key;
        try {
            key = Configs.get(CONFIG_NAME).getConfig().getString("key");
        } catch (Exception e) {
            key = null;
        }
        return key;
    }

    public static void setKey(String key) {
        Configs.get(CONFIG_NAME).getConfig().set("key", key);
        Configs.get(CONFIG_NAME).save();
    }

    public static boolean getKeyEnabled() {
        boolean enabled;
        try {
            enabled = Configs.get(CONFIG_NAME).getConfig().getBoolean("key-enabled");
        } catch (Exception e) {
            enabled = false;
        }
        return enabled;
    }

    public static void setKeyEnabled(Boolean enabled) {
        Configs.get(CONFIG_NAME).getConfig().set("key-enabled", enabled);
        Configs.get(CONFIG_NAME).save();
    }

    public static List<String> getPlayersToNotify() {
        return Configs.get(CONFIG_NAME).getConfig().getStringList("notify");
    }

    public static void setPlayersToNotify(List<String> list) {
        Configs.get(CONFIG_NAME).getConfig().set("notify", list);
        Configs.get(CONFIG_NAME).save();
    }

    public static boolean getConsoleNotifyEnabled() {
        boolean enabled;
        try {
            enabled = Configs.get(CONFIG_NAME).getConfig().getBoolean("console-notify");
        } catch (Exception e) {
            enabled = false;
        }
        return enabled;
    }

    public static void setConsoleNotifyEnabled(Boolean enabled) {
        Configs.get(CONFIG_NAME).getConfig().set("console-notify", enabled);
        Configs.get(CONFIG_NAME).save();
    }
}
