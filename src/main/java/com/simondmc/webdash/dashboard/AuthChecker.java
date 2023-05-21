package com.simondmc.webdash.dashboard;

import com.simondmc.webdash.server.WebServer;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public class AuthChecker {

    public static boolean isUnauthorized(HttpExchange he) throws IOException {
        // WebDash is disabled
        if (!StatusHandler.isEnabled()) {
            if (!WebServer.CORS) he.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            if (!WebServer.CORS) he.getResponseHeaders().add("Access-Control-Allow-Methods", "POST, PUT, PATCH, GET, DELETE, OPTIONS");
            if (!WebServer.CORS) he.getResponseHeaders().add("Access-Control-Allow-Headers", "Origin, X-Api-Key, X-Requested-With, Content-Type, Accept, Authorization");
            he.sendResponseHeaders(403, 0);
            he.getResponseBody().close();

            return true;
        }

        // key protection off
        if (!KeyHandler.isEnabled()) return false;

        String key = he.getRequestHeaders().getFirst("Authorization");

        // if key is missing or wrong
        if (key == null || !key.equals(KeyHandler.getKey())) {
            if (!WebServer.CORS) he.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            if (!WebServer.CORS) he.getResponseHeaders().add("Access-Control-Allow-Methods", "POST, PUT, PATCH, GET, DELETE, OPTIONS");
            if (!WebServer.CORS) he.getResponseHeaders().add("Access-Control-Allow-Headers", "Origin, X-Api-Key, X-Requested-With, Content-Type, Accept, Authorization");
            he.sendResponseHeaders(401, 0);
            he.getResponseBody().close();

            return true;
        }

        return false;
    }
}
