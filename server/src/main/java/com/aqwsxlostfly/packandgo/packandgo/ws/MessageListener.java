package com.aqwsxlostfly.packandgo.packandgo.ws;


import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.sockjs.transport.session.WebSocketServerSockJsSession;

public interface MessageListener {
    void handle(WebSocketSession session, String message);
}

