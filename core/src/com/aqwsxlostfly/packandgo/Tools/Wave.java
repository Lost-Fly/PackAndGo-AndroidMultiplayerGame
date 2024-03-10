package com.aqwsxlostfly.packandgo.Tools;

import com.aqwsxlostfly.packandgo.Heroes.Enemy;
import com.aqwsxlostfly.packandgo.Main;
import com.aqwsxlostfly.packandgo.Screens.GameSc;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class Wave {
    private int delay;
    private int minEnemy;

    private long startTimer;

    private String info = "wave - ";
    BitmapFont font;

    private int waveNumber;


    public Wave(int delay, int waveNumber,int minEnemy) {
        FreeTypeFontGenerator gen = new FreeTypeFontGenerator((Gdx.files.internal(("font.ttf"))));
        FreeTypeFontGenerator.FreeTypeFontParameter p =new FreeTypeFontGenerator.FreeTypeFontParameter();
        p.size=Main.screenWidth/10;
        p.color = new Color(Color.RED);
        font = gen.generateFont(p);
        this.delay = delay;
        this.minEnemy = minEnemy;
        this.waveNumber = waveNumber;
    }

    public void update(){
        if (GameSc.enemies.isEmpty() && startTimer==0) startTimer=System.currentTimeMillis();
        int seconds = 0;
        if (startTimer>0){
            seconds = (int) ((System.currentTimeMillis()-startTimer)/1000);
        }
        if (seconds>delay) {
            setWave();
            waveNumber++;
            startTimer =0;
            seconds=0;
        }
    }

    public void setWave() {
        int enemies = minEnemy + waveNumber * 2;
        int maxRank = 1;
        if (waveNumber > 5) maxRank = 2;
        if (waveNumber > 10) maxRank = 3;
        for (int i = 0; i < enemies; i++) {
            GameSc.enemies.add(new Enemy(Main.enemy, new Point2D((float) Main.screenWidth / 2, (float) Main.screenHeight / 4), (int) (Math.random() * maxRank+1)));
        }
    }
    public void draw(SpriteBatch batch){
        GlyphLayout gl = new GlyphLayout();
        gl.setText(font,info+waveNumber);
        font.draw(batch,gl,Main.screenWidth/2-gl.width/2,Main.screenHeight/2);
    }
    public boolean isDraw(){
        return startTimer >0;
    }
}
