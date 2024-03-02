package com.aqwsxlostfly.packandgo.GraphicsObj;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class GraphicsObj {
    public Texture img;

    public GraphicsObj(Texture img){
        this.img = img;
    }

    public abstract void draw(SpriteBatch batch);

    public abstract void update();

}
