package com.simondmc.webdash.websocket.handlers;

import com.simondmc.webdash.WebDash;
import com.simondmc.webdash.route.RouteHandler;
import com.simondmc.webdash.websocket.generic.SpecificIncomingSocketHandler;
import org.bukkit.Bukkit;
import org.java_websocket.WebSocket;

public class PressHandler implements SpecificIncomingSocketHandler {
    @Override
    public void handle(WebSocket conn, String message) {
        // run sync
        WebDash.plugin.getServer().getScheduler().scheduleSyncDelayedTask(WebDash.plugin, () -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), RouteHandler.getRoute(message).getCommand()));
    }
}
