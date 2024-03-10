package com.aqwsxlostfly.packandgo.Tools;

import com.aqwsxlostfly.packandgo.Main;
import com.aqwsxlostfly.packandgo.Screens.GameSc;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class GameHud {
    BitmapFont font;
    public GameHud(){
        FreeTypeFontGenerator gen = new FreeTypeFontGenerator((Gdx.files.internal(("font.ttf"))));
        FreeTypeFontGenerator.FreeTypeFontParameter p =new FreeTypeFontGenerator.FreeTypeFontParameter();
        p.size= Main.screenWidth/10;
        p.color = new Color(Color.RED);
        font = gen.generateFont(p);
    }
    public void draw(SpriteBatch batch){
        GlyphLayout gl = new GlyphLayout();
        gl.setText(font, GameSc.player.getHealth()+"");
        font.draw(batch,gl,0,Main.screenHeight);
        gl.setText(font,GameSc.player.getScore()+"");
        font.draw(batch,gl,Main.screenWidth- gl.width,Main.screenHeight);
    }
}
