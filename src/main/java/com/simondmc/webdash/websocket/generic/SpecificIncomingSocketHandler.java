package com.simondmc.webdash.websocket.generic;

import org.java_websocket.WebSocket;

public interface SpecificIncomingSocketHandler {
    void handle(WebSocket conn, String message);
}
