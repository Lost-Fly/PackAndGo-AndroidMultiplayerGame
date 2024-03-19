package com.aqwsxlostfly.packandgo.packandgo.ws;


import org.springframework.web.socket.WebSocketSession;
public interface DisconnectListener {
    void handle(WebSocketSession session);
}
