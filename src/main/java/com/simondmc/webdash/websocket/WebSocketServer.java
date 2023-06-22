package com.simondmc.webdash.websocket;

import com.simondmc.webdash.WebDash;
import com.simondmc.webdash.dashboard.KeyHandler;
import com.simondmc.webdash.dashboard.StatusHandler;
import com.simondmc.webdash.websocket.generic.IncomingSocketHandler;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;

import java.net.InetSocketAddress;

public class WebSocketServer extends org.java_websocket.server.WebSocketServer {

    public WebSocketServer(InetSocketAddress address) {
        super(address);
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        // if key is disabled and WebDash isn't off, allow connection
        if (!KeyHandler.isEnabled() && StatusHandler.isEnabled()) {
            WSSHandler.authConnection(conn);
        }
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        WSSHandler.removeConnection(conn);
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        IncomingSocketHandler.handle(conn, message);
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        // guide in console
        if (ex.getMessage().contains("Address already in use")) {
            WebDash.logger.warning("Socket server failed to start! Either there is another process using the port or you reloaded the plugin and the socket server didn't shut down correctly. In most cases, stopping the server and starting it again will fix the issue. If it persists, try changing the port in config.yml.");
        } else {
            WebDash.logger.warning("Socket server encountered an unexpected error! Additional info: " + ex.getMessage() + ". If this error persists, please report it at https://github.com/SimonDMC/WebDash/issues");
        }
        WSSHandler.markAsNotRunning();
    }

    @Override
    public void onStart() {
    }
}
