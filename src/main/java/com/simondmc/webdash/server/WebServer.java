package com.simondmc.webdash.server;

import com.simondmc.webdash.WebDash;
import com.simondmc.webdash.config.Configs;
import com.simondmc.webdash.data.KeyHandler;
import com.simondmc.webdash.util.ChatUtil;
import com.sun.net.httpserver.HttpServer;
import org.bukkit.Bukkit;

import java.net.InetSocketAddress;

public class WebServer {
    private static HttpServer server;

    private static boolean running = false;

    public static boolean isRunning() {
        return running;
    }

    public static boolean start() {
        try {
            int port = getPort();
            WebDash.logger.info("Starting web server at port " + port);

            // start server
            server = HttpServer.create(new InetSocketAddress(port), 0);
            server.createContext("/", new StaticFileServer());
            server.setExecutor(null);
            server.start();
            running = true;
            // send with color
            ChatUtil.sendColored("§aWebDash server started at " + getBaseLink());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            ChatUtil.sendColored("§cFailed to start WebDash server at " + getBaseLink());
            return false;
        }
    }

    private static int getPort() {
        try {
            int port = Configs.reloadAndGet("config.yml").getConfig().getInt("port");
            if (port < 0 || port > 65535) {
                throw new Exception();
            }
            return port;
        } catch (Exception e) {
            WebDash.logger.warning("Invalid port in config.yml! Using default port 26666");
            return 26666;
        }
    }

    public static void stop() {
        WebDash.logger.info("Stopping web server!");
        server.stop(0);
        running = false;
    }

    public static String getBaseLink() {
        int port = getPort();
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
