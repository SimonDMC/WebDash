package com.simondmc.webdash.server;

import com.simondmc.webdash.WebDash;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MainDashboard implements HttpHandler {

    private String getButtons() {
        StringBuilder sb = new StringBuilder();
        for (String id : RouteHandler.getRoutes().stream().map(Route::getId).toList()) {
            sb.append("<button onclick=\"send('").append(id).append("')\">").append(id).append("</button>");
        }
        return sb.toString();
    }

    @Override
    public void handle(HttpExchange he) throws IOException {
        // send html file
        String path = WebDash.plugin.getDataFolder().getAbsolutePath() + "/index.html";
        String html;
        try {
            html = new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            // file not found
            e.printStackTrace();
            WebDash.logger.severe("Could not read file: " + path);
            String response = "<h1>The dashboard file doesn't exist! Please check plugins/WebDash/index.html</h1>";
            he.sendResponseHeaders(200, response.length());
            OutputStream os = he.getResponseBody();
            os.write(response.getBytes());
            os.close();
            return;
        }
        html = html.replace("%buttons%", getButtons());
        he.sendResponseHeaders(200, html.length());
        OutputStream os = he.getResponseBody();
        os.write(html.getBytes());
        os.close();
    }
}