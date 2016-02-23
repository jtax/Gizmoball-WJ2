package model;

import java.awt.Color;
import java.util.List;

import physics.Circle;
import physics.LineSegment;
import physics.Vect;

/**
 * Created by baird on 06/02/2016.
 */
public interface IElement {

    List<LineSegment> getLines();

    List<Circle> getCircles();

    List<Vect> getCoordinates();

    Vect getOrigin();

    Vect getBound();

    Color getColor();

    void setColor(Color color);

    String getName();

    void rotate();
}
