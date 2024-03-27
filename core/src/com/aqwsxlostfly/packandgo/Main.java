package com.aqwsxlostfly.packandgo;


import com.aqwsxlostfly.packandgo.Screens.WaitingSc;
import com.aqwsxlostfly.packandgo.client.ws.NewWebSocket;
import com.aqwsxlostfly.packandgo.client.ws.WebSocketListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.nio.charset.StandardCharsets;

public class Main extends Game {

	public NewWebSocket webSocketClient;

	public static SpriteBatch batch;
	public static Texture img;
	public static Texture circle;
	public static Texture capibara;
	public static Texture bullet;
	public static Texture enemy;
	public static Texture ghost;
	public static int screenWidth;
	public static int screenHeight;
	public static int record;

	private WebSocketListener getWebsocketListener(){
		WebSocketListener webSocketListener = new WebSocketListener() {
			@Override
			public void onMessageReceived(String message) {
				Gdx.app.log("MESSAGE RECEIVED",message);
			}

			@Override
			public void onConnect(ServerHandshake handshake) {
				Gdx.app.log("CONNECT","OnConnect");

			}

			@Override
			public void onClose(int code, String reason) {
				Gdx.app.log("CLOSE CONNECT","onClose reason: "+ reason);

			}

			@Override
			public void onError(Exception ex) {
				Gdx.app.error("ERROR CONNECT","onError: " + ex.getMessage());
			}
		};

		return webSocketListener;
	}
	
	@Override
	public void create () {
		try {
			Gdx.app.log("INFO","CONNECT PROCESS");
			String wsUri = "ws://192.168.113.145:8867/ws";
			webSocketClient = new NewWebSocket(new URI(wsUri), getWebsocketListener());
			webSocketClient.connect();
			webSocketClient.send("CONNECT WAITING SCREEN");
		} catch (Exception e) {

			Gdx.app.error("ERROR SOCKET CONNECT","ERROR" + e.getMessage());
		}

		batch = new SpriteBatch();
		img = new Texture("packlogo.png");
		circle = new Texture("circle.png");
		capibara = new Texture("capibara.png");
		bullet = new Texture("burgerBullet.png");
		enemy = new Texture("enemy.png");
		ghost = new Texture("ghost.png");
		if (!Gdx.files.local("rec.txt").exists()){
			write("0");
		}
		record=read();
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		setScreen(new WaitingSc(this));

	}


	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
	public static void write(String str){
		FileHandle file = Gdx.files.local("rec.txt");
		file.writeString(str,false);
	}
	public static int read(){
		FileHandle file = Gdx.files.local("rec.txt");
		return Integer.parseInt(file.readString());
	}
}
