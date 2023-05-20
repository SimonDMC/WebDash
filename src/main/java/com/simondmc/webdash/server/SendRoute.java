package com.simondmc.webdash.server;

import com.simondmc.webdash.WebDash;
import com.simondmc.webdash.key.AuthChecker;
import com.simondmc.webdash.route.RouteHandler;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.bukkit.Bukkit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class SendRoute implements HttpHandler {

    @Override
    public void handle(HttpExchange he) throws IOException {
        // auth check
        if (AuthChecker.isUnauthorized(he)) return;

        // parse POST request
        InputStreamReader isr = new InputStreamReader(he.getRequestBody(), "utf-8");
        BufferedReader br = new BufferedReader(isr);
        String query = br.readLine();
        // parse query
        List<String> params = new ArrayList<>(List.of(query.split("&")));

        String response = "Send successful!";
        int status = 200;

        for (String id : params) {
            if (RouteHandler.getRoute(id) != null) {
                // run sync
                WebDash.plugin.getServer().getScheduler().scheduleSyncDelayedTask(WebDash.plugin, () -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), RouteHandler.getRoute(id).getCommand()));
            } else {
                WebDash.logger.warning("No route found for id: " + id);
                response = "No route found for id: " + id;
                status = 404;
            }
        }

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