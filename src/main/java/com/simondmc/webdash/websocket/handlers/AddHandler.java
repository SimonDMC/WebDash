package com.simondmc.webdash.websocket.handlers;

import com.simondmc.webdash.route.Route;
import com.simondmc.webdash.route.RouteHandler;
import com.simondmc.webdash.websocket.WSSHandler;
import com.simondmc.webdash.websocket.generic.SpecificIncomingSocketHandler;
import org.java_websocket.WebSocket;

public class AddHandler implements SpecificIncomingSocketHandler {
    @Override
    public void handle(WebSocket conn, String message) {
        // parse body
        String name = message.split("§§§")[0];
        String command = message.split("§§§")[1];
        String color = message.split("§§§")[2];

        // add route
        RouteHandler.addRoute(new Route(name, command, color));

        // broadcast changes
        WSSHandler.send(RouteHandler.getJSON());
    }
}
