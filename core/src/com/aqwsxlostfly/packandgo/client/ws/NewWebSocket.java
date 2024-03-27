package com.aqwsxlostfly.packandgo.client.ws;

import com.badlogic.gdx.Gdx;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import java.net.URI;

public class NewWebSocket extends WebSocketClient {
    private  WebSocketListener webSocketListener;

    public NewWebSocket(URI serverUri, WebSocketListener webSocketListener) {

        super(serverUri);
        this.webSocketListener = webSocketListener;

    }

    @Override
    public void onOpen(ServerHandshake handshake) {
        webSocketListener.onConnect(handshake);
        Gdx.app.log("CONNECTION CREATED", "On connect");
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        webSocketListener.onClose(code,reason);
        Gdx.app.log("CONNECTION CLOSED", " code: " + code + " reason: " + reason);
    }

    @Override
    public void onMessage(String message) {
        webSocketListener.onMessageReceived(message);
        Gdx.app.log("MESSAGE RECEIVED", message);
    }

    @Override
    public void onError(Exception ex) {
        webSocketListener.onError(ex);
    }
}
