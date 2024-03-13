package com.aqwsxlostfly.packandgo.Heroes;

import com.aqwsxlostfly.packandgo.Main;
import com.aqwsxlostfly.packandgo.Tools.Circle;
import com.aqwsxlostfly.packandgo.Tools.Point2D;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Enemy extends Heroes {
    private int health;
    private int score;
    private int rank;

    public Enemy(Texture img, Point2D position, int rank) {
        super(img, position);
        switch (rank) {
            case 1:
                radius = (float) Main.screenWidth / 25;
                speed = 10;
                score = health = 10;
                break;
            case 2:
                radius = (float) Main.screenWidth / 15;
                speed = 4;
                score = health = 20;
                break;
            case 3:
                radius = (float) Main.screenWidth / 10;
                speed = 2;
                score = health = 30;
                break;
//            default:
//                radius = (float) Main.screenWidth / 30;
//                speed = 1;
//                score = health = 10;
//                break;
        }
        bounds = new Circle(position, radius);
        direction.setX((float) Math.sin(Math.toRadians(Math.random()*360)));
        direction.setY((float) Math.cos(Math.toRadians(Math.random()*360)));



    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(img,position.getX()-radius,position.getY()-radius,radius*2,radius*2);
    }

    @Override
    public void update() {
        if(position.getX() + radius > Main.screenWidth) direction.setX(-direction.getX());
        if(position.getX() - radius < 0) direction.setX(-direction.getX());
        if(position.getY() + radius > Main.screenHeight) direction.setY(-direction.getY());;
        if(position.getY() - radius < 0) direction.setY(-direction.getY());;

        position.addCords(direction.getX()*speed,direction.getY()*speed);
        bounds.centerPos.setPoint(position);

    }
    public void hit(){
        health--;
    }

    public int getHealth(){
        return health;
    }
}
