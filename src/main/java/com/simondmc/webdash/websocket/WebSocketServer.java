package com.simondmc.webdash.websocket;

import com.simondmc.webdash.route.RouteHandler;
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
        conn.send(RouteHandler.getJSON());
        //System.out.println("new connection to " + conn.getRemoteSocketAddress());
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        //System.out.println("closed " + conn.getRemoteSocketAddress() + " with exit code " + code + " additional info: " + reason);
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        IncomingSocketHandler.handle(conn, message);
    }

    /*@Override
    public void onMessage( WebSocket conn, ByteBuffer message ) {
        System.out.println("received ByteBuffer from "	+ conn.getRemoteSocketAddress());
    }*/

    @Override
    public void onError(WebSocket conn, Exception ex) {
        //System.err.println("an error occurred on connection " + conn.getRemoteSocketAddress()  + ":" + ex);
    }

    @Override
    public void onStart() {
        //System.out.println("server started successfully");
    }
}
