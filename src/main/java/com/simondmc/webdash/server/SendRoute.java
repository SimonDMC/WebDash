package com.simondmc.webdash.server;

import com.simondmc.webdash.WebDash;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.bukkit.Bukkit;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class SendRoute implements HttpHandler {

    @Override
    public void handle(HttpExchange he) throws IOException {
        // parse request
        URI requestedUri = he.getRequestURI();
        String query = requestedUri.getRawQuery();
        // parse query
        List<String> params = new ArrayList<>(List.of(query.split("&")));

        String response = "Send successful!";

        for (String id : params) {
            if (RouteHandler.getRoute(id) != null) {
                // run sync
                WebDash.plugin.getServer().getScheduler().scheduleSyncDelayedTask(WebDash.plugin, () -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), RouteHandler.getRoute(id).getCommand()));
            } else {
                WebDash.logger.warning("No route found for id: " + id);
                response = "No route found for id: " + id;
            }
        }

        // send response
        he.sendResponseHeaders(200, response.length());
        OutputStream os = he.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}