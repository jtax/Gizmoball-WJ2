package model.Gizmos;

import model.Component;
import model.Components.Line;
import model.Coordinate;
import model.Gizmo;

import java.util.Arrays;

/**
 * Created by baird on 06/02/2016.
 */
public class Square extends Gizmo {


    public Square(Coordinate origin, String name) {
        super(origin, name);
        calculateComponents();
    }

    public Square(int x, int y, String name) {
		this (new Coordinate(x,y), name);
	}

	@Override
    protected void calculateComponents() {
        Coordinate origin = super.getOrigin();
        double x = origin.getX();
        double y = origin.getY();
        Component top = new Line(x,y, x+1,y);
        Component right = new Line(x+1,y, x+1,y-1);
        Component bottom = new Line(x,y-1, x+1,y-1);
        Component left = new Line(x,y, x,y-1);
        super.setComponents(Arrays.asList(top,right,bottom,left));
    }

    public void rotate(){
        //Pointless for Square: do nothing
    }

    public Coordinate calculateBound(){
        Coordinate bound = super.getOrigin();
        bound.setX(bound.getX()+1);
        bound.setY(bound.getX()-1);
        return bound;
    }
}