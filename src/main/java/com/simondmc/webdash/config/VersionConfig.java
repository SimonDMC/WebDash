package com.simondmc.webdash.config;

public class VersionConfig {
    private static final String CONFIG_NAME = "version.yml";

    public static void init() {
        Configs.addOverwritable(CONFIG_NAME);
    }

    public static String getVersion() {
        String ver;
        try {
            ver = Configs.get(CONFIG_NAME).getConfig().getString("version");
        } catch (Exception e) {
            ver = "?";
        }
        return ver;
    }
}
