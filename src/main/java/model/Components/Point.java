package model.Components;

import model.Component;
import model.Coordinate;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Created by baird on 06/02/2016.
 */
public class Point  implements Component {
    Coordinate origin;
    double height,width;

    public Point(double x, double y, double diameter){
        origin = new Coordinate(x,y);
        height = diameter;
        width = diameter;
    }

    public Coordinate getOrigin() {
        return origin;
    }

    public void setOrigin(Coordinate origin) {
        this.origin = origin;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Shape getGraphic(){
        return new Ellipse2D.Double(origin.getX(),origin.getY(),height,width);
    }
}
