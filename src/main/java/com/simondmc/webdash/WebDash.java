package com.simondmc.webdash;

import com.simondmc.webdash.command.WebDashCommand;
import com.simondmc.webdash.command.WebDashTabCompleter;
import com.simondmc.webdash.config.Configs;
import com.simondmc.webdash.config.DataConfig;
import com.simondmc.webdash.config.RoutesConfig;
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

        // init main config
        Configs.add("config.yml");

        // init routes config
        RoutesConfig.init();

        // init data config
        DataConfig.init();

        // start web server
        WebServer.start();
    }

    @Override
    public void onDisable() {
        if (WebServer.isRunning()) {
            WebServer.stop();
        }
    }
}
