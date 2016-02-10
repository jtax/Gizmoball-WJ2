package model.Gizmos;

import model.Component;
import model.Components.Line;
import model.Coordinate;
import model.Gizmo;

import java.util.Arrays;
import java.util.List;

/**
 * Created by baird on 06/02/2016.
 */
public class Square extends Gizmo {

    private int reflectionCoefficient = 1;

    public Square(Coordinate origin) {
        super(origin);
    }

    @Override
    protected List<Component> calculateComponents() {
        Coordinate origin = super.getOriginCoordinate();
        int x = origin.getX();
        int y = origin.getY();
        Component top = new Line(x,y, x+1,y);
        Component right = new Line(x+1,y, x+1,y-1);
        Component bottom = new Line(x,y-1, x+1,y-1);
        Component left = new Line(x,y, x,y-1);
        return Arrays.asList(new Component[]{top,right,bottom,left});
    }

    public void rotate(){
        //Pointless for Square: do nothing
    }

    public Coordinate calculateBound(){
        Coordinate bound = super.getOriginCoordinate();
        bound.setX(bound.getX()+1);
        bound.setY(bound.getX()-1);
        return bound;
    }
}
