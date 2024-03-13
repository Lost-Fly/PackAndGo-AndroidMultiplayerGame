package com.aqwsxlostfly.packandgo.packandgo.ws;


import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.sockjs.transport.session.WebSocketServerSockJsSession;

public interface DisconnectListener {
    void handle(WebSocketSession session);
}
