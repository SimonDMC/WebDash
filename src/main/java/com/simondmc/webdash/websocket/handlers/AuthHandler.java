package com.simondmc.webdash.websocket.handlers;

import com.simondmc.webdash.data.KeyHandler;
import com.simondmc.webdash.data.StatusHandler;
import com.simondmc.webdash.websocket.WSSHandler;
import com.simondmc.webdash.websocket.generic.SpecificIncomingSocketHandler;
import org.java_websocket.WebSocket;

public class AuthHandler implements SpecificIncomingSocketHandler {
    @Override
    public void handle(WebSocket conn, String message) {
        // if key is correct and WebDash is on, allow connection
        if (message.equals(KeyHandler.getKey()) && StatusHandler.isEnabled()) {
            WSSHandler.authConnection(conn);
        }
    }
}
