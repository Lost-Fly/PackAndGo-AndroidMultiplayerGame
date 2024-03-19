package com.aqwsxlostfly.packandgo.packandgo.ws;

import com.badlogic.gdx.utils.Array;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

@Slf4j
@Component
public class WebSocketHandler extends AbstractWebSocketHandler {

    private Array<WebSocketSession> sessions = new Array<>();

    private ConnectListener connectListener;
    private DisconnectListener disconnectListener;
    private MessageListener messageListener;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("Client connected: " + session.getId());
        System.out.println("Client connected: " + session.getId());
        sessions.add(session);
        connectListener.handle(session);

    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("Client " + session.getId() + " sent message: " + message.getPayload().toString());
        log.info("Client " + session.getId() + " sent message: " + message.getPayload().toString());
        messageListener.handle(session, message.getPayload());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("MESSAGE" + session.getId() + " " );
        System.out.println(session.getId() + " DISconnected ");
        sessions.removeValue(session, true);
        disconnectListener.handle(session);
    }

    public ConnectListener getConnectListener() {
        return connectListener;
    }

    public void setConnectListener(ConnectListener connectListener) {
        this.connectListener = connectListener;
    }

    public DisconnectListener getDisconnectListener() {
        return disconnectListener;
    }

    public void setDisconnectListener(DisconnectListener disconnectListener) {
        this.disconnectListener = disconnectListener;
    }

    public MessageListener getMessageListener() {
        return messageListener;
    }

    public void setMessageListener(MessageListener messageListener) {
        this.messageListener = messageListener;
    }

    public Array<WebSocketSession> getSessions() {
        return sessions;
    }

    public void setSessions(Array<WebSocketSession> sessions) {
        this.sessions = sessions;
    }
}
