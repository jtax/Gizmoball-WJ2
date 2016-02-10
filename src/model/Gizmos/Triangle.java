package model.Gizmos;

import model.Component;
import model.Components.Line;
import model.Coordinate;
import model.Gizmo;

import java.util.Arrays;
import java.util.List;


public class Triangle extends Gizmo {

	public Triangle(Coordinate origin) {
		super(origin);
	}

	@Override
	protected List<Component> calculateComponents() {
		Coordinate origin = super.getOriginCoordinate();
		double x = origin.getX();
		double y = origin.getY();
		Component top = new Line(x,y, x+1,y);
		Component right = new Line(x+1,y, x+1,y-1);
		Component bottom = new Line(x,y-1, x+1,y-1);
		Component left = new Line(x,y, x,y-1);
		return Arrays.asList(top,right,bottom,left);


	}

	@Override
	public void rotate() {
		// TODO Auto-generated method stub

	}

	@Override
	public Coordinate calculateBound() {
		return null;
	}
}
