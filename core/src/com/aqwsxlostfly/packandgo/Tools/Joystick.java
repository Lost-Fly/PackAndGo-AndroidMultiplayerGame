package com.aqwsxlostfly.packandgo.Tools;

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
        if ((!isDownTouch && pointer == this.pointer) || (isDownTouch && pointer == this.pointer && !CircleBounds.isContains(touch))) returnStick();
    }

    public void atControl(Point2D point){
        StickBounds.centerPos.setPoint(point);
        float dx = CircleBounds.centerPos.getX() - StickBounds.centerPos.getX();
        float dy = CircleBounds.centerPos.getY() - StickBounds.centerPos.getY();
        float distance = (float)Math.sqrt(dx*dx + dy*dy);
        direction.setPoint(-(dx/distance), -(dy/distance));
    }

    public void returnStick(){
        StickBounds.centerPos.setPoint(StickBounds.centerPos);
        direction.setPoint(0, 0);
        pointer = -1;
    }

    public Point2D getDir(){
        return direction;
    }

}
