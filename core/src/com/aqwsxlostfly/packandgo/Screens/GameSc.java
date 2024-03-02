package com.aqwsxlostfly.packandgo.Screens;

import com.aqwsxlostfly.packandgo.Heroes.Player;
import com.aqwsxlostfly.packandgo.Main;
import com.aqwsxlostfly.packandgo.Tools.Joystick;
import com.aqwsxlostfly.packandgo.Tools.Point2D;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameSc implements Screen {
    Main main;

    Joystick joy;
    Player player;

    public GameSc(Main main){
        this.main = main;
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
        Gdx.gl.glClearColor(0,1,0,1);
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

    public void loadHeroes(){
        joy = new Joystick(Main.circle, Main.capibara, new Point2D(((Main.screenHeight/3)/2+(Main.screenHeight/3)/4), (Main.screenHeight/3)/2+(Main.screenHeight/3)/4), Main.screenHeight/3);
        player = new Player(Main.capibara, new Point2D(Main.screenWidth/2, Main.screenHeight/2), 10, Main.screenHeight/20, 20);

    }

    public void multitouch(float x, float y, boolean isDownTouch, int pointer){
        for(int i =0; i < 5; i++){
            joy.update(x,y,isDownTouch, pointer);
        }
    }

    public void gameUpdate(){
        player.setDirection(joy.getDir());
        player.update();
    }

    public void gameRender(SpriteBatch batch){
        player.draw(batch);
        joy.draw(batch);
    }

}
