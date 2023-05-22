package com.simondmc.webdash.server;

import com.simondmc.webdash.WebDash;
import com.simondmc.webdash.config.Configs;
import com.simondmc.webdash.dashboard.KeyHandler;
import com.sun.net.httpserver.HttpServer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.net.InetSocketAddress;

public class WebServer {

    public static boolean CORS = false;

    private static HttpServer server;

    private static boolean running = false;

    public static boolean isRunning() {
        return running;
    }

    public static boolean start() {
        try {
            int port = 0;
            boolean invalid = false;
            try {
                port = Configs.reloadAndGet("config.yml").getConfig().getInt("port");
            } catch (Exception e) {
                invalid = true;
            }
            if (port < 0 || port > 65535) invalid = true;
            if (invalid) {
                WebDash.logger.warning("Invalid port in config.yml! Using default port 26666");
                port = 26666;
            }
            WebDash.logger.info("Starting server at port " + port);

            // start server
            server = HttpServer.create(new InetSocketAddress(port), 0);
            server.createContext("/", new MainDashboard());
            server.createContext("/send", new SendRoute());
            server.createContext("/get", new GetRoute());
            server.createContext("/delete", new DeleteRoute());
            server.createContext("/period", new PeriodRoute());
            server.createContext("/add", new AddRoute());
            server.createContext("/edit", new EditRoute());
            server.createContext("/drag", new DragRoute());
            server.setExecutor(null);
            server.start();
            running = true;
            // send with color
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "WebDash server started at " + getBaseLink());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            // send with color
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Failed to start WebDash server at " + getBaseLink());
            return false;
        }
    }

    public static void stop() {
        WebDash.logger.info("Stopping WebDash server!");
        server.stop(0);
        running = false;
    }

    public static String getBaseLink() {
        int port = WebDash.plugin.getConfig().getInt("port");
        String ip = Bukkit.getIp();
        if (ip.equals("")) {
            ip = "localhost";
        }
        return "http://" + ip + ":" + port;
    }

    public static String getLink() {
        String link = getBaseLink();
        if (KeyHandler.isEnabled()) {
            link += "?key=";
            link += KeyHandler.getKey();
        }
        return link;
    }
}
