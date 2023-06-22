package com.simondmc.webdash;

import com.simondmc.webdash.command.WebDashCommand;
import com.simondmc.webdash.command.WebDashTabCompleter;
import com.simondmc.webdash.config.*;
import com.simondmc.webdash.websocket.WSSHandler;
import com.simondmc.webdash.server.WebServer;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class WebDash extends JavaPlugin {

    public static WebDash plugin;
    public static Logger logger;

    @Override
    public void onEnable() {
        plugin = this;
        logger = getLogger();

        // register commands
        getCommand("webdash").setExecutor(new WebDashCommand());
        getCommand("wd").setExecutor(new WebDashCommand());
        getCommand("webdash").setTabCompleter(new WebDashTabCompleter());
        getCommand("wd").setTabCompleter(new WebDashTabCompleter());

        // copy over necessary images
        saveImages();

        // init main config
        Configs.add("config.yml");

        // init routes config
        // this config stores buttons/routes added by players
        RoutesConfig.init();

        // init data config
        // this config stores key data and on/off status
        DataConfig.init();

        // init messages config
        // this config is used as a language file
        MessagesConfig.init();

        // init version config
        // this config stores the current version of the plugin for access by info message
        VersionConfig.init();

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
}
