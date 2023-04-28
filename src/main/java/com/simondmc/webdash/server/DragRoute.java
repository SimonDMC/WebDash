package com.simondmc.webdash.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class DragRoute implements HttpHandler {

    @Override
    public void handle(HttpExchange he) throws IOException {
        // parse POST request
        InputStreamReader isr = new InputStreamReader(he.getRequestBody(), "utf-8");
        BufferedReader br = new BufferedReader(isr);
        String body = br.readLine();

        String response = "OK";
        int status = 200;

        // parse body
        int from = Integer.parseInt(body.split("§§§")[0]);
        int to = Integer.parseInt(body.split("§§§")[1]);

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

        if (route == null) {
            response = "Route not found";
            status = 404;
        } else {
            route.setIndex(to);
        }

        // send response
        if (!WebServer.CORS) he.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        if (!WebServer.CORS) he.getResponseHeaders().add("Access-Control-Allow-Methods", "DELETE");
        he.sendResponseHeaders(status, response.length());
        OutputStream os = he.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}