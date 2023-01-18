package com.simondmc.webdash.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class MainDashboard implements HttpHandler {

    private final int port;

    public MainDashboard(int port) {
        this.port = port;
    }

    private String getButtons() {
        StringBuilder sb = new StringBuilder();
        for (String key : SendRoute.getRoutes().keySet()) {
            sb.append("<button onclick=\"send('").append(key).append("')\">").append(key).append("</button>");
        }
        return sb.toString();
    }

    @Override
    public void handle(HttpExchange he) throws IOException {
        // send html
        String response = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "\n" +
                "    <head>\n" +
                "        <title>Web Console</title>\n" +
                "    </head>\n" +
                "\n" +
                "    <body>\n" +
                "        <h1>Web Console</h1>\n" +
                "        <p>Web Console is a web-based dashboard for your Minecraft server.</p>\n" +
                "        <p>It is currently in development, and is not yet ready for use.</p>\n" +
                "\n" +
                "        %1" +
                "    </body>\n" +
                "\n" +
                "    <script>\n" +
                "        function send(data) {\n" +
                "            // get current url\n" +
                "            const  url = window.location.href;\n" +
                "            fetch(`${url}send?${data}`);" +
                "        }\n" +
                "    </script>\n" +
                "\n" +
                "</html>";
        response = response.replace("%1", getButtons());
        he.sendResponseHeaders(200, response.length());
        OutputStream os = he.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}