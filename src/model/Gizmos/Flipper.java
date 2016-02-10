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
public class Flipper extends Gizmo {

	public Flipper(Coordinate origin) {
		super(origin);
	}

	@Override
	protected List<Component> calculateComponents() {
		Coordinate origin = super.getOriginCoordinate();
		double x = origin.getX();
		double y = origin.getY();
		Component top = new Line(x,y, x+1,y);
		Component right = new Line(x+1,y, x+1,y-2);
		Component bottom = new Line(x,y-2, x+1,y-2);
		Component left = new Line(x,y, x,y-2);
		return Arrays.asList(top,right,bottom,left);
	}

	@Override
	public void rotate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Coordinate calculateBound() {
		Coordinate bound = super.getOriginCoordinate();
		bound.setX(bound.getX()+2);
		bound.setY(bound.getX()-2);
		return bound;
	}
}