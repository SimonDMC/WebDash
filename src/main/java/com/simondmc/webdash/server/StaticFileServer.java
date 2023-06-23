package com.simondmc.webdash.server;

import com.simondmc.webdash.WebDash;
import com.simondmc.webdash.data.KeyHandler;
import com.simondmc.webdash.data.StatusHandler;
import com.simondmc.webdash.websocket.WSSHandler;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.List;

public class StaticFileServer implements HttpHandler {
    @Override
    public void handle(HttpExchange he) throws IOException {
        String query = he.getRequestURI().getQuery();
        List<String> queryBits = List.of();
        if (query != null) {
            queryBits = List.of(query.split("[&=]"));
        }
        // figure out default path to serve if requested file doesn't exist
        String defaultPath = getDefaultPath(queryBits);

        String path = he.getRequestURI().getPath();

        if (path.endsWith(".png") || path.endsWith(".ico")) {
            serveImage(he, path);
            return;
        }

        serveTextFile(he, path, defaultPath);
    }

    String getDefaultPath(List<String> queryBits) {
        if (!StatusHandler.isEnabled()) {
            // WebDash is disabled, serve off.html
            return "/off.html";
        } else if (KeyHandler.isEnabled() && !queryBits.contains(KeyHandler.getKey())) {
            // Key protection is enabled and key is wrong/missing, serve no.html
            return "/no.html";
        } else {
            // WebDash is enabled and key is correct/disabled, serve index.html
            return "/index.html";
        }
    }

    void serveImage(HttpExchange he, String path) throws IOException {
        // serve images from plugin folder
        File file = new File(WebDash.plugin.getDataFolder() + "/dash" + path);

        he.sendResponseHeaders(200, file.length());
        OutputStream os = he.getResponseBody();
        Files.copy(file.toPath(), os);
        os.close();
    }

    void serveTextFile(HttpExchange he, String path, String defaultPath) throws IOException {
        // if requesting root, serve the default file
        if (path.equals("/")) {
            path = defaultPath;
        }

        // attempt to get requested file from resources
        InputStream is = getClass().getClassLoader().getResourceAsStream("dash" + path);

        // if requested file doesn't exist, serve the default file
        if (is == null) {
            is = getClass().getClassLoader().getResourceAsStream("dash" + defaultPath);
        }

        String fileData = new String(is.readAllBytes());

        // replace placeholders in file with actual values
        fileData = fileData.replace("%WEBSOCKET_PORT%", String.valueOf(WSSHandler.getPort()));
        fileData = fileData.replace("%START_URL%", WebServer.getBaseLink());

        he.sendResponseHeaders(200, fileData.getBytes().length);
        OutputStream os = he.getResponseBody();
        os.write(fileData.getBytes());
        os.close();
    }
}