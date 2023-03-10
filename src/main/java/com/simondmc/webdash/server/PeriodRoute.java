package com.simondmc.webdash.server;

import com.simondmc.webdash.WebDash;
import com.simondmc.webdash.config.Configs;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class PeriodRoute implements HttpHandler {

    @Override
    public void handle(HttpExchange he) throws IOException {

        int period = 0;
        boolean invalid = false;
        try {
            period = Configs.reloadAndGet("config.yml").getConfig().getInt("refresh-period");
        } catch (Exception e) {
            invalid = true;
        }
        if (period <= 0) invalid = true;
        if (invalid) {
            WebDash.logger.warning("Invalid refresh period in config.yml! Using default period 500ms");
            period = 500;
        }

        String response = "{\"period\":" + period + "}";

        // send response
        if (!WebServer.CORS) he.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        he.sendResponseHeaders(200, response.length());
        OutputStream os = he.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
