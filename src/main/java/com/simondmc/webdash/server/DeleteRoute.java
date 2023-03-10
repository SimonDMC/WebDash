package com.simondmc.webdash.server;

import com.simondmc.webdash.WebDash;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Objects;

public class DeleteRoute implements HttpHandler {

    @Override
    public void handle(HttpExchange he) throws IOException {
        // parse DELETE request
        InputStreamReader isr = new InputStreamReader(he.getRequestBody(), "utf-8");
        BufferedReader br = new BufferedReader(isr);
        String id = br.readLine();

        String response;
        int status = 200;

        if (id == null) {
            response = "No id provided";
            // no 400 because pre-flight request would fail
        } else if (RouteHandler.removeRoute(id)) {
            response = "Route deleted successfully";
        } else {
            WebDash.logger.warning("No route found for id: " + id);
            response = "No route found for id: " + id;
            status = 404;
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