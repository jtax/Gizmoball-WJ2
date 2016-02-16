package model;

import physics.Circle;
import physics.LineSegment;
import physics.Vect;

import java.awt.*;
import java.util.List;

/**
 * Created by baird on 06/02/2016.
 */
public interface IElement {

    List<LineSegment> getLines();

    List<Circle> getCircles();

    Vect getOrigin();

    Vect getBound();

    Color getColor();

    void setColor(Color color);
}
