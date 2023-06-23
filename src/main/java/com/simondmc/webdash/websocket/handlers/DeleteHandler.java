package com.simondmc.webdash.websocket.handlers;

import com.simondmc.webdash.data.NotificationHandler;
import com.simondmc.webdash.route.Route;
import com.simondmc.webdash.route.RouteHandler;
import com.simondmc.webdash.websocket.generic.SpecificIncomingSocketHandler;
import org.java_websocket.WebSocket;

public class DeleteHandler implements SpecificIncomingSocketHandler {
    @Override
    public void handle(WebSocket conn, String message) {
        Route route = RouteHandler.getRoute(message);
        RouteHandler.removeRoute(message);

        if (route != null) {
            // notify
            NotificationHandler.notifyRouteRemove(route, null);
        }
    }
}
