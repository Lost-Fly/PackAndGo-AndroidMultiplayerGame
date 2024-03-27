package com.aqwsxlostfly.packandgo.client.ws;

import org.java_websocket.handshake.ServerHandshake;

public interface WebSocketListener {

    void onMessageReceived(String message);

    void onConnect(ServerHandshake handshake );

    void onClose(int code, String reason );
    void onError(Exception ex);

}

