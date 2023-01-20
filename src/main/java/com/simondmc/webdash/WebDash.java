package com.simondmc.webdash;

import com.simondmc.webdash.command.BindCommand;
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
        // Plugin startup logic
        plugin = this;
        logger = getLogger();

        // start web server
        try {
            server = new WebServer().create();
            server.start();
            logger.info("Server started");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // register commands
        getCommand("bind").setExecutor(new BindCommand());

        // copy HTML file into plugin directory
        saveResource("index.html", true);

        // copy config file into plugin directory
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        logger.info("Stopping the server!");
        server.stop(0);
    }
}
