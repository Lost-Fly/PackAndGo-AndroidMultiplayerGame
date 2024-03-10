package com.aqwsxlostfly.packandgo.Heroes;

import com.aqwsxlostfly.packandgo.Main;
import com.aqwsxlostfly.packandgo.Tools.Point2D;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player extends Heroes {

    private int Score;
    private float health;

    public Player(Texture img, Point2D position, float speed, float radius, float health) {
        super(img, position, speed, radius);
        this.health = health;
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(img, position.getX() - radius, position.getY() - radius);
    }



    @Override
    public void update() {
        if(position.getX() + radius > Main.screenWidth) position.setX(Main.screenWidth - radius);
        if(position.getX() - radius < 0) position.setX(radius);
        if(position.getY() + radius > Main.screenHeight) position.setY(Main.screenHeight -radius);
        if(position.getY() - radius < 0) position.setY(radius);

        position.addCords(direction.getX()*speed, direction.getY()*speed);

    }
}
