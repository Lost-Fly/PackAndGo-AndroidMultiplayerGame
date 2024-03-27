package com.aqwsxlostfly.packandgo.Screens;

import com.aqwsxlostfly.packandgo.Heroes.Bullet;
import com.aqwsxlostfly.packandgo.Heroes.Enemy;
import com.aqwsxlostfly.packandgo.Heroes.Player;
import com.aqwsxlostfly.packandgo.Main;
import com.aqwsxlostfly.packandgo.Tools.BulletGenerator;
import com.aqwsxlostfly.packandgo.Tools.GameHud;
import com.aqwsxlostfly.packandgo.Tools.Joystick;
import com.aqwsxlostfly.packandgo.Tools.Point2D;
import com.aqwsxlostfly.packandgo.Tools.Wave;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import java.util.ArrayList;

public class WaitingSc implements Screen {


    Main main;
    Joystick joy;
    Joystick joyBullet;
    public static Player player;
    public static ArrayList<Bullet> bullets;

    public static ArrayList<Player> players;

    BitmapFont font;
    GlyphLayout textWaiting, textJoin;

    public static BulletGenerator bulletGenerator;

    public WaitingSc(Main main){
        this.main = main;
        FreeTypeFontGenerator gen = new FreeTypeFontGenerator((Gdx.files.internal(("font.ttf"))));
        FreeTypeFontGenerator.FreeTypeFontParameter p = new FreeTypeFontGenerator.FreeTypeFontParameter();
        p.size = Main.screenWidth / 30;
        p.color = new Color(Color.RED);
        font = gen.generateFont(p);
        textWaiting = new GlyphLayout();
        textWaiting.setText(font, "WAITING OTHER PLAYER");
        textJoin = new GlyphLayout();
        textJoin.setText(font, "PLAYER HAS JOINED");
    }
    @Override
    public void show() {


        Gdx.input.setInputProcessor(new InputProcessor() {
            @Override
            public boolean keyDown(int keycode) {
                return false;
            }

            @Override
            public boolean keyUp(int keycode) {
                return false;
            }

            @Override
            public boolean keyTyped(char character) {
                return false;
            }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                screenY = Main.screenHeight - screenY;
                multitouch((int)screenX, (int)screenY, true, pointer);
                return false;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                screenY = Main.screenHeight - screenY;
                multitouch((int)screenX, (int)screenY, false, pointer);
                return false;
            }

            @Override
            public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
                screenY = Main.screenHeight - screenY;
                multitouch((int)screenX, (int)screenY, false, pointer);
                return false;
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                screenY = Main.screenHeight - screenY;
                multitouch((int)screenX, (int)screenY, true, pointer);
                return false;
            }

            @Override
            public boolean mouseMoved(int screenX, int screenY) {
                return false;
            }

            @Override
            public boolean scrolled(float amountX, float amountY) {
                return false;
            }
        });

        loadHeroes();
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gameUpdate();
        Main.batch.begin();

        gameRender(Main.batch);
        Main.batch.end();


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

    public void loadHeroes() {
        joy = new Joystick(Main.circle, Main.capibara, new Point2D(((Main.screenHeight / 3) / 2 + (Main.screenHeight / 3) / 4), (Main.screenHeight / 3) / 2 + (Main.screenHeight / 3) / 4), Main.screenHeight / 3);
        joyBullet = new Joystick(Main.circle, Main.capibara, new Point2D(Main.screenWidth - ((Main.screenHeight / 3) / 2 + (Main.screenHeight / 3) / 4), (Main.screenHeight / 3) / 2 + (Main.screenHeight / 3) / 4), Main.screenHeight / 3);
        player = new Player(Main.capibara, new Point2D(Main.screenWidth / 2, Main.screenHeight / 2), 10F, (float) (Main.screenHeight / 5), 5);
        players = new ArrayList<Player>();
        players.add(player);
        bullets = new ArrayList<Bullet>();
        bulletGenerator = new BulletGenerator();
    }

    public void multitouch(float x, float y, boolean isDownTouch, int pointer) {
        for (int i = 0; i < 3; i++) {
            joy.update(x, y, isDownTouch, pointer);
            joyBullet.update(x, y, isDownTouch, pointer);
        }
    }

    public void gameUpdate() {

        player.setDirection(joy.getDir());
        for (int i = 0; i < players.size(); i++) {
            players.get(i).update();
        }

        

        bulletGenerator.update(joyBullet);
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).update();
            if (bullets.get(i).isOut) {
                bullets.remove(i--);
            }
        }
    }

    public void gameRender(SpriteBatch batch) {
        if (players.size() < 2) {
            font.draw(batch, textWaiting, Main.screenWidth / 2 - textWaiting.width / 2, Main.screenHeight -Main.screenHeight/10);
        } else {
            font.draw(batch, textJoin, Main.screenWidth / 2 - textWaiting.width / 2, Main.screenHeight -Main.screenHeight/10 );
        }
        for (int i = 0; i < players.size(); i++) {
            players.get(i).draw(batch);
        }
        joy.draw(batch);
        joyBullet.draw(batch);
        for (int i = bullets.size() - 1; i >= 0; i--) {
            bullets.get(i).draw(batch);
        }
    }
}
