package com.aqwsxlostfly.packandgo.packandgo;

import com.aqwsxlostfly.packandgo.packandgo.ws.WebSocketHandler;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.utils.Array;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

@Slf4j
@Component
public class GameLoop extends ApplicationAdapter {
    private final WebSocketHandler socketHandler;
    private final Array<String> events = new Array<>();

    private final String JOIN_MSG = "Just joined";
    private final String LEFT_MSG = "Just left";
    private final String SAID_MSG = " said ";

    public GameLoop(WebSocketHandler socketHandler) {

        this.socketHandler = socketHandler;
    }

    @Override
    public void create() {
        socketHandler.setConnectListener(session -> {
            System.out.println(session.getId() + " said " + "CONNECT");
            log.info("CONNECTED" + session.getId());
            events.add(session.getId() + JOIN_MSG);
        });
        socketHandler.setDisconnectListener(session -> {
            System.out.println(session.getId() + " said " + "DISCONNECT");
            log.info("DISCONNECTED" + session.getId());
            events.add(session.getId() + LEFT_MSG);
        });
        socketHandler.setMessageListener((((session, message) -> {
            System.out.println(session.getId() + " said " + "MSG");
            log.info("MESSAGE" + session.getId());
            events.add(session.getId() + SAID_MSG + message);
        })));
    }



    @Override
    public void render() {
        for (WebSocketSession session : socketHandler.getSessions()) {
            System.out.println(session.getId() + " said " + "FOR SESSIONS");
            try {
                for (String event : events) {
                    System.out.println(event);
                    session.sendMessage(new TextMessage(event));
                    log.info("MESSAGE" + session.getId() + " " + event);
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
