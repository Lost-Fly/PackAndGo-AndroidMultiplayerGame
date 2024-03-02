package com.aqwsxlostfly.packandgo;

import com.aqwsxlostfly.packandgo.Screens.GameSc;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Main extends Game {
	public static SpriteBatch batch;
	public static Texture img;
	public static Texture circle;
	public static Texture capibara;
	public static int screenWidth;
	public static int screenHeight;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("packlogo.png");
		circle = new Texture("circle.png");
		capibara = new Texture("capibara.png");
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		setScreen(new GameSc(this));
	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
