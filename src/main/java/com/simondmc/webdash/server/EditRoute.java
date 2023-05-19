package com.simondmc.webdash.server;

import com.simondmc.webdash.config.RoutesConfig;
import com.simondmc.webdash.key.AuthChecker;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class EditRoute implements HttpHandler {

    @Override
    public void handle(HttpExchange he) throws IOException {
        // auth check
        if (AuthChecker.isUnauthorized(he)) return;

        // parse POST request
        InputStreamReader isr = new InputStreamReader(he.getRequestBody(), "utf-8");
        BufferedReader br = new BufferedReader(isr);
        String body = br.readLine();

        String response;
        int status;

        // parse body
        String id = body.split("§§§")[0];
        String name = body.split("§§§")[1];
        String command = body.split("§§§")[2];
        String color = body.split("§§§")[3];

        // edit route
        Route route = RouteHandler.getRoute(id);

        if (route == null) {
            response = "Route not found";
            status = 404;
        } else {
            response = "Edited";
            status = 200;

            route.setName(name);
            route.setCommand(command);
            route.setColor(color);
        }

        // save routes
        RoutesConfig.saveRoutes();

        // send response
        if (!WebServer.CORS) he.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        if (!WebServer.CORS) he.getResponseHeaders().add("Access-Control-Allow-Methods", "POST");
        he.sendResponseHeaders(status, response.length());
        OutputStream os = he.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}