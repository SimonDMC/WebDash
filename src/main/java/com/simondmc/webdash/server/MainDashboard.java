package com.simondmc.webdash.server;

import com.simondmc.webdash.dashboard.KeyHandler;
import com.simondmc.webdash.dashboard.StatusHandler;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class MainDashboard implements HttpHandler {
    @Override
    public void handle(HttpExchange he) throws IOException {
        String query = he.getRequestURI().getQuery();
        List<String> queryBits = List.of();
        if (query != null) {
            queryBits = List.of(query.split("[&=]"));
        }
        String defaultPath;
        if (!StatusHandler.isEnabled()) {
            // WebDash is disabled, serve off.html
            defaultPath = "/off.html";
        } else if (KeyHandler.isEnabled() && !queryBits.contains(KeyHandler.getKey())) {
            // Key protection is enabled and key is wrong/missing, serve no.html
            defaultPath = "/no.html";
        } else {
            // WebDash is enabled and key is correct/disabled, serve index.html
            defaultPath = "/index.html";
        }

        String path = he.getRequestURI().getPath();

        // try to get file from resources/dash, if not found, use default path
        if (path.equals("/")) {
            path = defaultPath;
        }
        InputStream is = getClass().getClassLoader().getResourceAsStream("dash" + path);
        if (is == null) {
            is = getClass().getClassLoader().getResourceAsStream("dash" + defaultPath);
        }

        String fileData = new String(is.readAllBytes());
        he.sendResponseHeaders(200, fileData.getBytes().length);
        OutputStream os = he.getResponseBody();
        os.write(fileData.getBytes());
        os.close();
    }
}