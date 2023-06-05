package com.simondmc.webdash.websocket.generic;

import com.simondmc.webdash.websocket.handlers.*;
import org.java_websocket.WebSocket;

public class IncomingSocketHandler {
    public static void handle(WebSocket conn, String message) {
        SpecificIncomingSocketHandler handler;
        String handlerName = message.split("§§§")[0];
        String handlerMessage = message.substring(handlerName.length() + 3);

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
            default:
                return;
        }

        handler.handle(conn, handlerMessage);
    }
}
