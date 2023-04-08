package com.simondmc.webdash.server;

import com.simondmc.webdash.WebDash;
import com.simondmc.webdash.config.Configs;
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

            // start server
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

            server = HttpServer.create(new InetSocketAddress(port), 0);
            WebDash.logger.info("Creating server at port " + port);
            server.createContext("/", new MainDashboard());
            server.createContext("/send", new SendRoute());
            server.createContext("/get", new GetRoute());
            server.createContext("/delete", new DeleteRoute());
            server.createContext("/period", new PeriodRoute());
            server.createContext("/add", new AddRoute());
            server.createContext("/edit", new EditRoute());
            server.setExecutor(null);
            server.start();
            running = true;
            // send with color
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "WebDash server started at " + getLink());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            // send with color
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Failed to start WebDash server at " + getLink());
            return false;
        }
    }

    public static void stop() {
        WebDash.logger.info("Stopping the server!");
        server.stop(0);
        running = false;
    }

    public static String getLink() {
        int port = WebDash.plugin.getConfig().getInt("port");
        String ip = Bukkit.getIp();
        if (ip.equals("")) {
            ip = "localhost";
        }
        return "http://" + ip + ":" + port;
    }
}
