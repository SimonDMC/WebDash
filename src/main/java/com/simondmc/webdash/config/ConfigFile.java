package com.simondmc.webdash.config;

import com.simondmc.webdash.WebDash;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;

public class ConfigFile {

    private FileConfiguration config;
    private String name;

    public ConfigFile(String name, boolean overwrite) {
        this.name = name;
        File customConfigFile = new File(WebDash.plugin.getDataFolder(), name);
        if (!customConfigFile.exists() || overwrite) {
            customConfigFile.getParentFile().mkdirs();
            WebDash.plugin.saveResource(name, overwrite);
        } else {
            // if any keys are missing, set them to default values
            // to preserve comments from default config, a fresh copy is loaded and existing values are copied over
            try {
                // rename existing config
                File existingConfig = new File(WebDash.plugin.getDataFolder(), name + ".old");
                if (!customConfigFile.renameTo(existingConfig)) {
                    throw new Exception("Failed to rename existing config file!");
                }

                // load default config
                WebDash.plugin.saveResource(name, false);

                // copy existing values to new config
                YamlConfiguration existingConfigYaml = new YamlConfiguration();
                existingConfigYaml.load(existingConfig);
                YamlConfiguration defaultConfigYaml = new YamlConfiguration();
                defaultConfigYaml.load(customConfigFile);
                for (String key : existingConfigYaml.getKeys(true)) {
                    defaultConfigYaml.set(key, existingConfigYaml.get(key));
                }

                // save new config
                defaultConfigYaml.save(customConfigFile);

                // delete old config
                if (!existingConfig.delete()) {
                    throw new Exception("Failed to delete old config file!");
                }
            } catch (Exception e) {
                WebDash.logger.warning("File updating failed! Keeping old config file.");
            }
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
