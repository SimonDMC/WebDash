package com.simondmc.webdash.websocket.generic;

import com.simondmc.webdash.websocket.WSSHandler;
import com.simondmc.webdash.websocket.handlers.*;
import org.java_websocket.WebSocket;

public class IncomingSocketHandler {
    public static void handle(WebSocket conn, String message) {
        SpecificIncomingSocketHandler handler;
        String handlerName = message.split("§§§")[0];
        String handlerMessage = message.substring(handlerName.length() + 3);

        // if connection is not authenticated only allow auth request
        if (!WSSHandler.isAuthed(conn) && !handlerName.equals("auth")) return;

        switch (handlerName) {
            case "add":
                handler = new AddHandler();
                break;
            case "edit":
                handler = new EditHandler();
                break;
            case "delete":
                handler = new DeleteHandler();
                break;
            case "drag":
                handler = new DragHandler();
                break;
            case "press":
                handler = new PressHandler();
                break;
            case "auth":
                handler = new AuthHandler();
                break;
            default:
                return;
        }

        handler.handle(conn, handlerMessage);
    }
}
