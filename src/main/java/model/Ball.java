package model;

import physics.Circle;
import physics.LineSegment;
import physics.Vect;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

/**
 * Created by baird on 06/02/2016.
 */
public class Ball implements IElement {
    private Circle point;
    private Vect origin;
    private Vect velocity;
    private Vect center;
    private Color color = Color.GREEN;
    private String name;

    //TODO do balls need names?
    public Ball(String name,double x, double y, double velocityX, double velocityY){
        center = new Vect(x, y);
        point = new Circle(center, 0.25);
        origin = new Vect(x - .25, y - .25);
        velocity = new Vect(velocityX, velocityY);
        this.name = name;
    }

    public Ball(String name, Vect center, Vect velocity) {
        this(name, center.x(), center.y(), velocity.x(), velocity.y());
    }

    public Vect getCenter() {
        return center;
    }

    public void setCenter(Vect center) {
        this.center = center;
        update();
    }

    public Vect getVelocity() {
        return velocity;
    }

    public void setVelocity(Vect velocity) {
        this.velocity = velocity;
    }

    public Circle getCircle() {
        return point;
    }

    @Override
    public List<LineSegment> getLines() {
        return null;
    }

    @Override
    public List<Circle> getCircles() {
        return Arrays.asList(point);
    }

    @Override
    public Vect getOrigin() {
        return origin;
    }

    @Override
    public Vect getBound() {
        double x = origin.x() + 0.5;
        double y = origin.y() + 0.5;
        return new Vect(x, y);
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color color) {
        return;
    }

    public void update() {
        point = new Circle(center, 0.25);
        origin = new Vect(center.x() - .25, center.y() - .25);
    }

}
