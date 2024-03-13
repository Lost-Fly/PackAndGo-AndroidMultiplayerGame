package com.aqwsxlostfly.packandgo.Tools;

public class Circle {

    public float radius;
    public Point2D centerPos;

    public Circle(Point2D pos, float radius){
        this.centerPos = new Point2D(pos);
        this.radius = radius;
    }

    public boolean isContains(Point2D point){
        float dx = centerPos.getX()- point.getX();
        float dy = centerPos.getY() - point.getY();
        return radius*radius >= dx*dx + dy*dy;
    }

    public boolean overLaps(Circle circle){
        float dx = centerPos.getX()- circle.centerPos.getX();
        float dy = centerPos.getY() - circle.centerPos.getY();
        float dist = dx*dx + dy*dy;
        float radSum = circle.radius + radius;
        return dist < radSum*radSum;
    }

}
