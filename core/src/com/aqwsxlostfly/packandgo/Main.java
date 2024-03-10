package com.aqwsxlostfly.packandgo;

import com.aqwsxlostfly.packandgo.Screens.GameSc;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.logging.FileHandler;

public class Main extends Game {
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
	
	@Override
	public void create () {
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
