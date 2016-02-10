package model;

import model.Components.Point;

/**
 * Created by baird on 06/02/2016.
 */
public class Ball {
    Component point;
    Coordinate velocity;

    public Ball(String name,double x, double y, double velocityX, double velocityY){
        point = new Point(x,y,0.5);
        velocity = new Coordinate(velocityX,velocityY);
    }

    public Coordinate getVelocity() {
        return velocity;
    }

    public void setVelocity(Coordinate velocity) {
        this.velocity = velocity;
    }

    public Component getPoint() {
        return point;
    }

    public void setPoint(Component point) {
        this.point = point;
    }
}
