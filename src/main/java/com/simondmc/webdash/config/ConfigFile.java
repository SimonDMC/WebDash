package com.simondmc.webdash.config;

import com.simondmc.webdash.WebDash;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigFile {

    private FileConfiguration config;
    private String name;

    public ConfigFile(String name, boolean overwrite) {
        this.name = name;
        File customConfigFile = new File(WebDash.plugin.getDataFolder(), name);
        if (!customConfigFile.exists() || overwrite) {
            customConfigFile.getParentFile().mkdirs();
            WebDash.plugin.saveResource(name, overwrite);
        }

        config = new YamlConfiguration();
        try {
            config.load(customConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            config.save(new File(WebDash.plugin.getDataFolder(), name));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public void reload() {
        File customConfigFile = new File(WebDash.plugin.getDataFolder(), name);
        config = YamlConfiguration.loadConfiguration(customConfigFile);
    }
}
