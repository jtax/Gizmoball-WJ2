package model.Gizmos;

import model.Component;
import model.Components.Line;
import model.Coordinate;
import model.Gizmo;

import java.util.Arrays;


public class Triangle extends Gizmo {

	public Triangle(Coordinate origin, String name) {
		super(origin, name);
		calculateComponents();
	}

	@Override
	protected void calculateComponents() {
		Coordinate origin = super.getOrigin();
		double x = origin.getX();
		double y = origin.getY();
		Component sideA = new Line(x,y, x,y-1);
		Component sideB = new Line(x,y-1, x+1,y-1);
		Component hypotenuse = new Line(x,y, x+1,y-1);
		super.setComponents(Arrays.asList(sideA,sideB,hypotenuse));


	}

	@Override
	public void rotate() {
		// TODO Auto-generated method stub

	}

	@Override
	public Coordinate calculateBound() {

		Coordinate bound = super.getOrigin();
		bound.setX(bound.getX()+1);
		bound.setY(bound.getX()-1);
		return bound;
	}

}