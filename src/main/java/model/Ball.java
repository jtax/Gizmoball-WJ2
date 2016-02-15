package model;

import model.Components.Point;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

/**
 * Created by baird on 06/02/2016.
 */
public class Ball implements IElement {
    Component point;
    Coordinate origin, velocity, center;
    Color color = Color.GREEN;

    //TODO do balls need names?
    public Ball(String name,double x, double y, double velocityX, double velocityY){
        center = new Coordinate(x, y);
        point = new Point(x,y,0.5);
        origin = new Coordinate(x - .25, y - .25);
        velocity = new Coordinate(velocityX,velocityY);
    }

    public Coordinate getCenter() {
        return center;
    }

    public void setCenter(Coordinate center) {
        this.center = center;
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

    @Override
    public List<Component> getComponents() {
        return Arrays.asList(point);
    }

    @Override
    public Coordinate getOrigin() {
        return origin;
    }

    @Override
    public Coordinate getBound() {
        double x = origin.getX() + 0.5;
        double y = origin.getY() + 0.5;
        return new Coordinate(x, y);
    }

    @Override
    public Color getColor() {
        return color;
    }
}
