package com.aqwsxlostfly.packandgo.Tools;

import com.aqwsxlostfly.packandgo.Main;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Joystick {



    private int pointer = -1;
    Texture CircleImg, StickImg;
    Circle CircleBounds, StickBounds;

    Point2D direction;
    public float Rcircle, Rstick;

    public Joystick(Texture circleImg, Texture stickImg, Point2D point, float joySize){
        CircleImg = circleImg;
        StickImg = stickImg;
        Rcircle = joySize/2;
        Rstick = Rcircle/2;
        CircleBounds = new Circle(point, Rcircle);
        StickBounds = new Circle(point, Rstick);
        direction = new Point2D(0, 0);
    }

    public void draw(SpriteBatch batch){
        batch.draw(CircleImg, CircleBounds.centerPos.getX() - Rcircle, CircleBounds.centerPos.getY() - Rcircle, Rcircle*2, Rcircle*2);
        batch.draw(StickImg, StickBounds.centerPos.getX() - Rstick, StickBounds.centerPos.getY() - Rstick, Rstick*2, Rstick*2);
    }

    public void update(float x, float y, boolean isDownTouch, int pointer){
        Point2D touch = new Point2D(x, y);
        if (CircleBounds.isContains(touch) && isDownTouch && this.pointer == -1) this.pointer = pointer;
        if (CircleBounds.overLaps(StickBounds) && isDownTouch && pointer == this.pointer) atControl(new Point2D(x, y));
        if ((isDownTouch && pointer == this.pointer && !CircleBounds.isContains(touch))) atControl(new Point2D(x, y));
        if ((!isDownTouch && pointer == this.pointer)) returnStick();
    }

    public void atControl(Point2D point){
        float actualX = point.getX();
        float actualY = point.getY();
        float actualDX = CircleBounds.centerPos.getX() - actualX;
        float actualDY = CircleBounds.centerPos.getY() - actualY;
        float distance = (float)Math.sqrt(actualDX * actualDX + actualDY * actualDY);

        if (distance > CircleBounds.radius) {
            actualX = CircleBounds.centerPos.getX() - (actualDX * CircleBounds.radius / distance);
            actualY = CircleBounds.centerPos.getY() - (actualDY * CircleBounds.radius / distance);
        }

        StickBounds.centerPos.setPoint(actualX, actualY);
        direction.setPoint(-(actualDX / distance), -(actualDY / distance));

    }

    public void returnStick(){
        StickBounds.centerPos.setPoint(CircleBounds.centerPos);
        direction.setPoint(0, 0);
        pointer = -1;
    }

    public Point2D getDir(){
        return direction;
    }

}
