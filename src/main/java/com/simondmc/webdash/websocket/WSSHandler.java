package com.simondmc.webdash.websocket;

import com.simondmc.webdash.WebDash;
import com.simondmc.webdash.config.Configs;
import com.simondmc.webdash.route.RouteHandler;
import com.simondmc.webdash.util.ChatUtil;
import org.bukkit.Bukkit;
import org.java_websocket.WebSocket;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

public class WSSHandler {
    private static final List<WebSocket> authenticatedSockets = new ArrayList<>();
    private static WebSocketServer server;
    private static boolean isRunning = false;

    public static boolean start() {
        String host = getHost();
        int port = getPort();

        WebDash.logger.info("Starting socket server at port " + port);

        try {
            server = new WebSocketServer(new InetSocketAddress(host, port));
            Thread socketThread = new Thread(() -> server.run());
            socketThread.start();
        } catch (Exception e) {
            e.printStackTrace();
            ChatUtil.sendColored("§cFailed to start WebDash socket server at " + getLink());
            return false;
        }
        isRunning = true;
        ChatUtil.sendColored("§aWebDash socket server started at " + getLink());
        return true;
    }

    private static String getHost() {
        String host = Bukkit.getIp();
        if (host.equals("")) {
            host = "localhost";
        }
        return host;
    }

    public static int getPort() {
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
        for (WebSocket socket : authenticatedSockets) {
            socket.send(message);
        }
    }

    public static void stop() {
        WebDash.logger.info("Stopping socket server!");
        try {
            server.stop();
        } catch (Exception e) {
            return;
        }
        isRunning = false;
    }

    public static void authConnection(WebSocket socket) {
        authenticatedSockets.add(socket);
        socket.send(RouteHandler.getJSON());
    }

    public static boolean isAuthed(WebSocket socket) {
        return authenticatedSockets.contains(socket);
    }

    public static void closeAll() {
        for (WebSocket socket : server.getConnections()) {
            socket.close();
        }
    }

    public static boolean isRunning() {
        return isRunning;
    }
}
