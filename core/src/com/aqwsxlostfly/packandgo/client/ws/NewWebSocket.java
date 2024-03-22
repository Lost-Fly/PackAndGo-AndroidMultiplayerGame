package com.aqwsxlostfly.packandgo.client.ws;

import com.badlogic.gdx.Gdx;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import java.net.URI;

public class NewWebSocket extends WebSocketClient {

    public NewWebSocket(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake handshake) {
        Gdx.app.log("CONNECTION CREATED", "kdsnjv");
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        Gdx.app.log("CONNECTION CLOSED", " code: " + code + " reason: " + reason);
    }

    @Override
    public void onMessage(String message) {
        Gdx.app.log("MESSAGE RECEIVED", message);
    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
    }
}
