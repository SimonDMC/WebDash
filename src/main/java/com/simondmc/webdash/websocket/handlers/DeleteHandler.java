package com.simondmc.webdash.websocket.handlers;

import com.simondmc.webdash.route.RouteHandler;
import com.simondmc.webdash.websocket.WSSHandler;
import com.simondmc.webdash.websocket.generic.SpecificIncomingSocketHandler;
import org.java_websocket.WebSocket;

public class DeleteHandler implements SpecificIncomingSocketHandler {
    @Override
    public void handle(WebSocket conn, String message) {
        RouteHandler.removeRoute(message);

        // broadcast changes
        WSSHandler.send(RouteHandler.getJSON());
    }
}
