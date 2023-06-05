package com.simondmc.webdash.websocket.handlers;

import com.simondmc.webdash.config.RoutesConfig;
import com.simondmc.webdash.route.Route;
import com.simondmc.webdash.route.RouteHandler;
import com.simondmc.webdash.websocket.WSSHandler;
import com.simondmc.webdash.websocket.generic.SpecificIncomingSocketHandler;
import org.java_websocket.WebSocket;

public class DragHandler implements SpecificIncomingSocketHandler {
    @Override
    public void handle(WebSocket conn, String message) {
        // parse body
        int from = Integer.parseInt(message.split("§§§")[0]);
        int to = Integer.parseInt(message.split("§§§")[1]);

        // set dragged route after reordering
        Route route = RouteHandler.getRoute(from);

        // reorder routes
        if (from < to) {
            for (int i = from + 1; i <= to; i++) {
                RouteHandler.getRoute(i).setIndex(i - 1);
            }
        } else {
            for (int i = from - 1; i >= to; i--) {
                RouteHandler.getRoute(i).setIndex(i + 1);
            }
        }

        route.setIndex(to);

        // save routes
        RoutesConfig.saveRoutes();

        // broadcast changes
        WSSHandler.send(RouteHandler.getJSON());
    }
}
