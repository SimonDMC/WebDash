package com.simondmc.webdash.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class GetRoute implements HttpHandler {

    @Override
    public void handle(HttpExchange he) throws IOException {

        String response = RouteHandler.getJSON();

        // send response
        if (!WebServer.CORS) he.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        if (!WebServer.CORS) he.getResponseHeaders().add("Access-Control-Allow-Methods", "GET");
        he.sendResponseHeaders(200, response.length());
        OutputStream os = he.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
