package model.Gizmos;

import model.Component;
import model.Components.Line;
import model.Coordinate;
import model.Gizmo;

import java.awt.*;
import java.util.Arrays;
import java.util.List;


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
		Component hypotenuse = new Line(x,y, x + 1,y+1);
		Component sideB = new Line(x+1,y, x+1,y+1);
		Component sideA = new Line(x,y, x+1,y);

		super.setComponents(Arrays.asList(sideA, sideB, hypotenuse));

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