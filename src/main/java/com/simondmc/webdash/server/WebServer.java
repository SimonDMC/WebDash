package com.simondmc.webdash.server;

import com.simondmc.webdash.WebDash;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class WebServer {
    public HttpServer create() throws IOException {
        int port = 26666;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        WebDash.logger.info("Creating server at port " + port);
        server.createContext("/", new MainDashboard(port));
        server.createContext("/send", new SendRoute());
        server.setExecutor(null);
        return server;
    }
}
