package com.aqwsxlostfly.packandgo.packandgo.ws;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

@Component
public class MyWebSocketHandler extends AbstractWebSocketHandler {

    private Array<WebSocketSession> sessions = new Array<>();

    private ConnectListener connectListener;
    private DisconnectListener disconnectListener;
    private MessageListener messageListener;


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Gdx.app.log("CONNECTION", " NEW CONNECTION: " + " sessionID " + session.getId() + " headers " +
                session.getHandshakeHeaders() + " protocols " + session.getAcceptedProtocol() + " sizeBynaryLimit " +
                session.getBinaryMessageSizeLimit() + " clientIP " + session.getRemoteAddress());
        sessions.add(session);
        connectListener.handle(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        Gdx.app.log("MESSAGE", " NEW MESSAGE: " + message + "\n\n" + " sessionID " + session.getId()
                + " headers " + session.getHandshakeHeaders() + " protocols " + session.getAcceptedProtocol()
                + " sizeTextLimit " + session.getTextMessageSizeLimit() + " clientIP " + session.getRemoteAddress());
        messageListener.handle(session, message.getPayload());
    }



    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        Gdx.app.log("CLOSED CONNECTION", " CLOSED CONNECTION: " + " sessionID " + session.getId() + " headers " +
                session.getHandshakeHeaders() + " protocols " + session.getAcceptedProtocol() + " sizeBynaryLimit " +
                session.getBinaryMessageSizeLimit() + " clientIP " + session.getRemoteAddress());
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
