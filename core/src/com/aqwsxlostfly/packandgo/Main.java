package com.aqwsxlostfly.packandgo;

import com.aqwsxlostfly.packandgo.Screens.GameSc;
import com.aqwsxlostfly.packandgo.client.ws.NewWebSocket;
import com.aqwsxlostfly.packandgo.client.ws.WebSocketClient;
import com.aqwsxlostfly.packandgo.client.ws.WebSocketListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.net.URI;

public class Main extends Game {

	public static WebSocketClient webSocketClient;

	public static NewWebSocket newWebSocket;

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

	private void getWebsocket(){
		WebSocketClient webSocketClient = new WebSocketClient("192.168.170.252", 8867);

		webSocketClient.addWebSocketListener(new WebSocketListener() {
			@Override
			public void onMessageReceived(String message) {
				Gdx.app.log("MESSAGE RECEIVED", message);
			}

			@Override
			public void onConnect(String message) {
				Gdx.app.log("CONNECTION CREATED", message);
			}

			@Override
			public void onClose(String message) {
				Gdx.app.log("CONNECTION CLOSED", message);
			}
		});

		Main.webSocketClient = webSocketClient;
	}
	
	@Override
	public void create () {
//		try {
//			getWebsocket();
//
//			webSocketClient.connect();
//
//			webSocketClient.sendMessage("HELLO SERVER");
//
//		} catch (Exception e){
//			Gdx.app.error("CONNECTION FAILED", e.getMessage());
//		}

		try {
			String wsUri = "ws://192.168.170.252:8867/ws";
			NewWebSocket webSocketClient = new NewWebSocket(new URI(wsUri));
			webSocketClient.connect();
		} catch (Exception e) {
			e.printStackTrace();
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
		setScreen(new GameSc(this));

	}


	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		if (webSocketClient != null) {
			webSocketClient.close();
		}
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
