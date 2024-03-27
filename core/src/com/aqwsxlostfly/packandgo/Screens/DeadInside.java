package com.aqwsxlostfly.packandgo.Screens;

import com.aqwsxlostfly.packandgo.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class DeadInside implements Screen {
    Main main;
    BitmapFont font;
    GlyphLayout gl;
    GlyphLayout gl2;
    GlyphLayout gl3;

    public DeadInside(Main main, String score) {
        this.main = main;
        FreeTypeFontGenerator gen = new FreeTypeFontGenerator((Gdx.files.internal(("font.ttf"))));
        FreeTypeFontGenerator.FreeTypeFontParameter p = new FreeTypeFontGenerator.FreeTypeFontParameter();
        p.size = Main.screenWidth / 10;
        p.color = new Color(Color.RED);
        font = gen.generateFont(p);
        gl = new GlyphLayout();
        gl.setText(font, score);
        gl2 = new GlyphLayout();
        gl3 = new GlyphLayout();
        gl3.setText(font, "Tap to begin");
        String info;
        if (Integer.parseInt(score) > Main.record) {
            info = "New record";
            Main.write(score);
            Main.record = Integer.parseInt(score);

        } else {
            info = "Record: " + Main.record;
        }
        gl2.setText(font, info);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update();
        main.batch.begin();
        font.draw(main.batch, gl, Main.screenWidth / 2 - gl.width / 2, Main.screenHeight / 2);
        font.draw(main.batch, gl2, Main.screenWidth / 2 - gl2.width / 2, (Main.screenHeight / 2 - gl.height));
        font.draw(main.batch, gl3, Main.screenWidth / 2 - gl3.width / 2, Main.screenHeight);
        main.batch.end();


    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public void update() {
        if (Gdx.input.justTouched()) {
            main.setScreen(new GameSc(main));
        }
    }
}
