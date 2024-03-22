package com.aqwsxlostfly.packandgo.client.ws;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WebSocketClient {
    private Socket socket;
    private final String serverUrl;

    private final Integer serverPort;

    private  WebSocketListener webSocketListener;

    public WebSocketClient(String serverUrl, Integer serverPort) {
        this.serverPort = serverPort;
        this.serverUrl = serverUrl;
    }

    public void addWebSocketListener(WebSocketListener webSocketListener){
        this.webSocketListener = webSocketListener;
    }


    public void connect() {

        SocketHints hints = new SocketHints();
        hints.connectTimeout = 15000;
        hints.socketTimeout = 15000;

//        hints.tcpNoDelay = true;

        socket = Gdx.net.newClientSocket(Net.Protocol.TCP, serverUrl, serverPort, null);

        if (socket != null) {
            webSocketListener.onConnect("Connection opened");
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                if (socket != null) {
                    try {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        String message;
                        while ((message = reader.readLine()) != null) {
                            webSocketListener.onMessageReceived(message);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        close();
                    }
                }
            }
        }).start();
    }

    public void sendMessage(String message) {
        if (socket != null && socket.isConnected()) {
            try {
                Gdx.app.log("CLIENT SEND", " send: " + message);

                byte[] messageBytes = message.getBytes("UTF-8");
                socket.getOutputStream().write(messageBytes);
                socket.getOutputStream().flush();

            } catch (IOException e) {
                Gdx.app.error("WebSocket Error", "Failed to send message: " + message, e);
            }
        } else {
            Gdx.app.error("WebSocket Error", "Socket is null or not connected");
        }
    }

    public void close() {
        if (socket != null) {
            webSocketListener.onClose("Connection closed");
            socket.dispose();
        }
    }
}
