package com.simondmc.webdash.config;

import java.util.HashMap;

public class Configs {

    private static final HashMap<String, ConfigFile> configList = new HashMap<>();

    public static void add(String configName) {
        configList.put(configName, new ConfigFile(configName, false));
    }
    public static void addOverwritable(String configName) {
        configList.put(configName, new ConfigFile(configName, true));
    }

    public static ConfigFile get(String configName) {
        return configList.get(configName);
    }

    public static ConfigFile reloadAndGet(String configName) {
        configList.get(configName).reload();
        return configList.get(configName);
    }
}
