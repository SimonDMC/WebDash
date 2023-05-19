package com.simondmc.webdash.server;

import com.simondmc.webdash.key.AuthChecker;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class AddRoute implements HttpHandler {

    @Override
    public void handle(HttpExchange he) throws IOException {
        // auth check
        if (AuthChecker.isUnauthorized(he)) return;

        // parse POST request
        InputStreamReader isr = new InputStreamReader(he.getRequestBody(), "utf-8");
        BufferedReader br = new BufferedReader(isr);
        String body = br.readLine();

        String response = "Created";
        int status = 201;

        // parse body
        String name = body.split("§§§")[0];
        String command = body.split("§§§")[1];
        String color = body.split("§§§")[2];

        // add route
        RouteHandler.addRoute(new Route(name, command, color));

        // send response
        if (!WebServer.CORS) he.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        if (!WebServer.CORS) he.getResponseHeaders().add("Access-Control-Allow-Methods", "POST");
        if (!WebServer.CORS) he.getResponseHeaders().add("Access-Control-Allow-Headers", "Origin, X-Api-Key, X-Requested-With, Content-Type, Accept, Authorization");
        he.sendResponseHeaders(status, response.length());
        OutputStream os = he.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}