package com.aqwsxlostfly.packandgo.client.ws;

public interface WebSocketListener {

    void onMessageReceived(String message);

    void onConnect(String message);

    void onClose(String message);

}

