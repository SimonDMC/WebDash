package com.simondmc.webdash.key;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public class AuthChecker {

    public static boolean isUnauthorized(HttpExchange he) throws IOException {
        // key protection off
        if (!KeyHandler.isEnabled()) return false;

        String key = he.getRequestHeaders().getFirst("Key");
        // missing key
        if (key == null) return true;
        boolean authorized = key.equals(KeyHandler.getKey());

        // respond 401 if not authorized
        if (!authorized) {
            he.sendResponseHeaders(401, 0);
            he.getResponseBody().close();
        }

        return !authorized;
    }
}
