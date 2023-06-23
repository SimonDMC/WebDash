package com.simondmc.webdash;

import com.simondmc.webdash.command.WebDashCommand;
import com.simondmc.webdash.command.WebDashTabCompleter;
import com.simondmc.webdash.config.*;
import com.simondmc.webdash.server.WebServer;
import com.simondmc.webdash.websocket.WSSHandler;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class WebDash extends JavaPlugin {

    public static WebDash plugin;
    public static Logger logger;

    @Override
    public void onEnable() {
        plugin = this;
        logger = getLogger();

        // register command
        getCommand("webdash").setExecutor(new WebDashCommand());
        getCommand("webdash").setTabCompleter(new WebDashTabCompleter());

        // copy over necessary images from resources to plugin folder
        saveImages();

        // initialize config files
        initConfigs();

        // start web server
        WebServer.start();

        // start socket server
        WSSHandler.start();
    }

    @Override
    public void onDisable() {
        if (WebServer.isRunning()) {
            WebServer.stop();
        }
        WSSHandler.stop();
    }

    void saveImages() {
        saveResource("dash/favicon.ico", true);
        saveResource("dash/icon-192.png", true);
        saveResource("dash/icon-512.png", true);
    }

    void initConfigs() {
        // user config
        Configs.add("config.yml");

        // routes.yml stores buttons/routes added by players
        RoutesConfig.init();

        // data.yml stores key data, on/off status and players to notify
        DataConfig.init();

        // messages.yml is used as a language file
        MessagesConfig.init();

        // version.yml stores the current version of the plugin for access by info message
        VersionConfig.init();
    }
}
