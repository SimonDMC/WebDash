package com.simondmc.webdash.websocket.handlers;

import com.simondmc.webdash.data.NotificationHandler;
import com.simondmc.webdash.route.Route;
import com.simondmc.webdash.route.RouteHandler;
import com.simondmc.webdash.websocket.WSSHandler;
import com.simondmc.webdash.websocket.generic.SpecificIncomingSocketHandler;
import org.java_websocket.WebSocket;

public class EditHandler implements SpecificIncomingSocketHandler {
    @Override
    public void handle(WebSocket conn, String message) {
        // parse body
        String id = message.split("§§§")[0];
        String name = message.split("§§§")[1];
        String command = message.split("§§§")[2];
        String color = message.split("§§§")[3];

        Route route = RouteHandler.getRoute(id);
        Route oldRoute = new Route(route.getName(), route.getCommand(), route.getColor());

        // edit route
        route.setName(name);
        route.setCommand(command);
        route.setColor(color);

        // broadcast changes
        WSSHandler.send(RouteHandler.getJSON());

        // notify
        NotificationHandler.notifyRouteEdit(oldRoute, route);
    }
}
