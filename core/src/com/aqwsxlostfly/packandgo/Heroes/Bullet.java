package com.aqwsxlostfly.packandgo.Heroes;


import com.aqwsxlostfly.packandgo.Main;
import com.aqwsxlostfly.packandgo.Tools.Point2D;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Bullet extends Heroes {
    public boolean isOut;


    public Bullet(Texture img, Point2D position, float speed, float radius, Point2D direction) {
        super(img, position, speed, radius);
        this.direction = new Point2D(direction);
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(img, position.getX() - radius, position.getY() - radius, radius * 2, radius * 2);


    }

    @Override
    public void update() {
        isOut = (position.getX() - radius > Main.screenWidth) || (position.getX() + radius < 0)
                || (position.getY() - radius > Main.screenHeight) || (position.getY() + radius < 0);
        position.addCords(direction.getX() * speed, direction.getY() * speed);
        bounds.centerPos.setPoint(position);
    }
}
