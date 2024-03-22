package com.aqwsxlostfly.packandgo.packandgo;

import com.aqwsxlostfly.packandgo.packandgo.ws.MyWebSocketHandler;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;


import java.io.IOException;


@Component
public class GameLoop extends ApplicationAdapter {
    private final MyWebSocketHandler socketHandler;
    private final Array<String> events = new Array<>();

    private final String JOIN_MSG = "Just joined";
    private final String LEFT_MSG = "Just left";
    private final String SAID_MSG = " said ";

    public GameLoop(MyWebSocketHandler socketHandler) {
        this.socketHandler = socketHandler;
    }

    @Override
    public void create() {

        socketHandler.setConnectListener(session -> {
            events.add(session.getId() + JOIN_MSG);
        });
        socketHandler.setDisconnectListener(session -> {
            events.add(session.getId() + LEFT_MSG);
        });
        socketHandler.setMessageListener((((session, message) -> {
            events.add(session.getId() + SAID_MSG + message);
        })));

    }


    @Override
    public void render() {

        for (WebSocketSession session : socketHandler.getSessions()) {
            try {
                for (String event : events) {
                    session.sendMessage(new TextMessage(event));
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        events.clear();

    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
