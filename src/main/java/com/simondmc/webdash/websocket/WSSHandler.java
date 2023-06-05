package com.simondmc.webdash.websocket;

import com.simondmc.webdash.WebDash;
import com.simondmc.webdash.config.Configs;
import com.simondmc.webdash.util.ConsoleUtil;
import org.bukkit.Bukkit;

import java.net.InetSocketAddress;

public class WSSHandler {
    private static WebSocketServer server;
    private static boolean isRunning = false;
    public static void start() {
        String host = getHost();
        int port = getPort();

        WebDash.logger.info("Starting socket server at port " + port);

        try {
            server = new WebSocketServer(new InetSocketAddress(host, port));
            Thread socketThread = new Thread(() -> server.run());
            socketThread.start();
        } catch (Exception e) {
            e.printStackTrace();
            ConsoleUtil.sendColored("§cFailed to start WebDash socket server at " + getLink());
        }
        isRunning = true;
        ConsoleUtil.sendColored("§aWebDash socket server started at " + getLink());
    }

    private static String getHost() {
        String host = Bukkit.getIp();
        if (host.equals("")) {
            host = "localhost";
        }
        return host;
    }

    private static int getPort() {
        try {
            int port = Configs.reloadAndGet("config.yml").getConfig().getInt("socket-port");
            if (port < 0 || port > 65535) {
                throw new Exception();
            }
            return port;
        } catch (Exception e) {
            WebDash.logger.warning("Invalid socket port in config.yml! Using default port 26667");
            return 26667;
        }
    }

    private static String getLink() {
        return "ws://" + getHost() + ":" + getPort();
    }

    public static void send(String message) {
        server.broadcast(message);
    }

    public static void stop() {
        try {
            server.stop();
        }
        catch (Exception e) {
            return;
        }
        isRunning = false;
    }

    public static boolean isRunning() {
        return isRunning;
    }
}
