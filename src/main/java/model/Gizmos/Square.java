package model.Gizmos;

import model.Gizmo;
import physics.LineSegment;
import physics.Vect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by baird on 06/02/2016.
 */
public class Square extends Gizmo {

    private int reflectionCoefficient = 1;
    private List<Vect> coordinates;

    public Square(Vect origin, String name) {
        super(origin, name);
        coordinates = calculateCoordinates();
        super.setCircles(calculateCircles());
        super.setLines(calculateLines());
    }

    public Square(int x, int y, String name) {
        this(new Vect(x, y), name);
    }

    private List<Vect> calculateCoordinates() {
        Vect topLeft = origin;
        Vect topRight = origin.plus(new Vect(bound.x(), 0));
        Vect bottomRight = bound;
        Vect bottomLeft = origin.plus(new Vect(0, bound.y()));
        return Arrays.asList(topLeft, topRight, bottomLeft, bottomRight);
    }

    private List<physics.Circle> calculateCircles() {
        List<physics.Circle> calcCircles = new ArrayList<>();
        for (Vect coord : coordinates) {
            physics.Circle circle = new physics.Circle(coord, 0);
            calcCircles.add(circle);
        }
        return calcCircles;
    }

    private List<LineSegment> calculateLines() {
        List<LineSegment> calcLines = new ArrayList<>();
        for (int i = 0; i < coordinates.size() - 1; i++) {
            Vect a = coordinates.get(i);
            Vect b = coordinates.get(i + 1 % coordinates.size() - 1);
            LineSegment line = new LineSegment(a, b);
            calcLines.add(line);
        }
        return calcLines;
    }

    public void rotate(){
        //Pointless for Square: do nothing
    }

    public Vect calculateBound() {
        Vect origin = super.getOrigin();
        Vect bound = new Vect(1, -1);
        return origin.plus(bound);
    }
}