package com.simondmc.webdash;

import com.simondmc.webdash.command.WebDashCommand;
import com.simondmc.webdash.server.WebServer;
import com.sun.net.httpserver.HttpServer;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class WebDash extends JavaPlugin {

    public static WebDash plugin;
    public static Logger logger;
    HttpServer server;

    @Override
    public void onEnable() {
        plugin = this;
        logger = getLogger();

        // start web server
        WebServer.start();

        // register commands
        getCommand("webdash").setExecutor(new WebDashCommand());

        // copy HTML file into plugin directory
        saveResource("index.html", true);

        // copy config file into plugin directory
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        WebServer.stop();
    }
}
