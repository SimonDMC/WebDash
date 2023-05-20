package com.simondmc.webdash.server;

import com.simondmc.webdash.key.KeyHandler;
import com.simondmc.webdash.route.Route;
import com.simondmc.webdash.route.RouteHandler;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

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
        String query = he.getRequestURI().getQuery();
        List<String> queryBits = List.of();
        if (query != null) {
            queryBits = List.of(query.split("[&=]"));
        }
        boolean unauthorized = KeyHandler.isEnabled() && !queryBits.contains(KeyHandler.getKey());

        String path = he.getRequestURI().getPath();
        // try to get file from resources/dash, if not found, use resources/dash/index.html
        if (path.equals("/")) {
            path = "/index.html";
        }
        // if key protection is enabled, serve no.html instead of dashboard if key is wrong/missing
        if (unauthorized && path.equals("/index.html")) {
            path = "/no.html";
        }
        InputStream is = getClass().getClassLoader().getResourceAsStream("dash" + path);
        if (is == null) {
            is = getClass().getClassLoader().getResourceAsStream(unauthorized ? "dash/no.html" : "dash/index.html");
        }
        String fileData = new String(is.readAllBytes());
        fileData = fileData.replace("%buttons%", getButtons());
        he.sendResponseHeaders(200, fileData.getBytes().length);
        OutputStream os = he.getResponseBody();
        os.write(fileData.getBytes());
        os.close();
    }
}