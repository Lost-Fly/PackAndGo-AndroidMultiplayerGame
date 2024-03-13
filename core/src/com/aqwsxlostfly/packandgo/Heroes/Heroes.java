package com.aqwsxlostfly.packandgo.Heroes;

import com.aqwsxlostfly.packandgo.GraphicsObj.GraphicsObj;
import com.aqwsxlostfly.packandgo.Tools.Circle;
import com.aqwsxlostfly.packandgo.Tools.Point2D;
import com.badlogic.gdx.graphics.Texture;

public abstract class Heroes extends GraphicsObj {

    public Point2D position;

    public float speed, radius;

    public Point2D direction;

    public Circle bounds;


    public Heroes(Texture img, Point2D position, float speed, float radius) {
        super(img);
        this.position =new Point2D(position);
        this.radius = radius;
        this.speed = speed;
        bounds = new Circle(position, radius);
        direction = new Point2D(0, 0);
    }
    public Heroes(Texture img, Point2D position) {
        super(img);
        this.position =new Point2D(position);

        direction = new Point2D(0, 0);
    }

    public void setDirection(Point2D p){
        direction = p;
    }


}
