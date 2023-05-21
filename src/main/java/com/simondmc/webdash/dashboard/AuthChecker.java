package com.simondmc.webdash.dashboard;

import com.simondmc.webdash.server.WebServer;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public class AuthChecker {

    public static boolean isUnauthorized(HttpExchange he) throws IOException {
        // key protection off
        if (!KeyHandler.isEnabled()) return false;

        String key = he.getRequestHeaders().getFirst("Authorization");
        // missing key
        if (key == null) return true;
        boolean authorized = key.equals(KeyHandler.getKey());

        // respond 401 if not authorized
        if (!authorized) {
            if (!WebServer.CORS) he.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            if (!WebServer.CORS) he.getResponseHeaders().add("Access-Control-Allow-Methods", "POST, PUT, PATCH, GET, DELETE, OPTIONS");
            if (!WebServer.CORS) he.getResponseHeaders().add("Access-Control-Allow-Headers", "Origin, X-Api-Key, X-Requested-With, Content-Type, Accept, Authorization");
            he.sendResponseHeaders(401, 0);
            he.getResponseBody().close();
        }

        return !authorized;
    }
}
