package com.simondmc.webdash.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class GetRoute implements HttpHandler {

    @Override
    public void handle(HttpExchange he) throws IOException {
        // compile JSON from routes
        StringBuilder json = new StringBuilder("{\"buttons\": [");
        for (Route route : RouteHandler.getRoutes()) {
            json.append(route.toJson()).append(",");
        }
        // remove trailing comma
        if (json.charAt(json.length() - 1) == ',') {
            json.deleteCharAt(json.length() - 1);
        }
        json.append("]}");

        // send response
        he.sendResponseHeaders(200, json.length());
        OutputStream os = he.getResponseBody();
        os.write(json.toString().getBytes());
        os.close();
    }
}
