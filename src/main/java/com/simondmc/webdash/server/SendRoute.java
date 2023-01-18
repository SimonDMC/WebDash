package com.simondmc.webdash.server;

import com.simondmc.webdash.WebDash;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.bukkit.Bukkit;

import java.io.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SendRoute implements HttpHandler {

    private static final Map<String, String> routes = new HashMap<>();

    public static void addRoute(String key, String command) {
        routes.put(key, command);
    }

    public static Map<String, String> getRoutes() {
        return routes;
    }

    @Override
    public void handle(HttpExchange he) throws IOException {
        // parse request
        URI requestedUri = he.getRequestURI();
        String query = requestedUri.getRawQuery();
        // parse query
        List<String> params = new ArrayList<String>(List.of(query.split("&")));
        WebDash.logger.info("Query: " + query);

        for (String key : params) {
            if (routes.containsKey(key)) {
                // run sync
                WebDash.plugin.getServer().getScheduler().scheduleSyncDelayedTask(WebDash.plugin, () -> {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), routes.get(key));
                });
            } else {
                WebDash.logger.warning("No route found for key: " + key);
            }
        }

        // send response
        String response = "success";
        he.sendResponseHeaders(200, response.length());
        OutputStream os = he.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}