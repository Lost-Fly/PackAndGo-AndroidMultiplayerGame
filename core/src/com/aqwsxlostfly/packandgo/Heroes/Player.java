package com.aqwsxlostfly.packandgo.Heroes;

import com.aqwsxlostfly.packandgo.Main;
import com.aqwsxlostfly.packandgo.Tools.Point2D;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player extends Heroes {

    private int score = 0;
    private float health;
    private boolean ghost;
    private long startTimer=0;

    public Player(Texture img, Point2D position, float speed, float radius, float health) {
        super(img, position, speed, radius);
        this.health = health;
    }

    @Override
    public void draw(SpriteBatch batch) {
        if (ghost) {
            this.img = Main.ghost;
        }else {
            this.img = Main.capibara;
        }

        batch.draw(img, position.getX() - radius, position.getY() - radius);
    }


    @Override
    public void update() {
        if (position.getX() + radius > Main.screenWidth) position.setX(Main.screenWidth - radius);
        if (position.getX() - radius < 0) position.setX(radius);
        if (position.getY() + radius > Main.screenHeight) position.setY(Main.screenHeight - radius);
        if (position.getY() - radius < 0) position.setY(radius);
        if (startTimer==0 && ghost) startTimer = System.currentTimeMillis();
        int seconds=0;
        if (startTimer>0) seconds = (int)(System.currentTimeMillis()-startTimer)/1000;
        if (seconds>3){
            ghost=false;
            startTimer=0;
        }

        position.addCords(direction.getX() * speed, direction.getY() * speed);
        bounds.centerPos.setPoint(position);


    }

    public void hit() {
        if (!ghost) {
            ghost = true;
            health--;
        }
    }
    public boolean isGhost(){ return ghost;}
    public float getHealth(){return health;}
    public void setScore(){score++;}
    public int getScore(){return score;}
}
