package com.simondmc.webdash.config;

import java.util.HashMap;

public class Configs {

    private static final HashMap<String, ConfigFile> configList = new HashMap<>();

    public static void add(String configName) {
        configList.put(configName, new ConfigFile(configName));
    }

    public static ConfigFile get(String configName) {
        return configList.get(configName);
    }
}
