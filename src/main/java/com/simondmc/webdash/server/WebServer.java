package com.simondmc.webdash.server;

import com.simondmc.webdash.WebDash;
import com.sun.net.httpserver.HttpServer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.net.InetSocketAddress;

public class WebServer {

    private static HttpServer server;

    private static boolean running = false;

    public static boolean isRunning() {
        return running;
    }

    public static boolean start() {
        try {
            // reload config
            WebDash.plugin.reloadConfig();

            // start server
            int port = WebDash.plugin.getConfig().getInt("port");
            server = HttpServer.create(new InetSocketAddress(port), 0);
            WebDash.logger.info("Creating server at port " + port);
            server.createContext("/", new MainDashboard());
            server.createContext("/send", new SendRoute());
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
