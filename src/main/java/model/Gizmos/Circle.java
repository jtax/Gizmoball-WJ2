package model.Gizmos;

import model.Component;
import model.Components.Point;
import model.Coordinate;
import model.Gizmo;

import java.util.Arrays;
import java.util.List;

/**
 * Created by baird on 06/02/2016.
 */
public class Circle extends Gizmo {

	public Circle(Coordinate origin, String name) {
		super(origin, name);
		calculateComponents();
	}
	
	public Circle(int x, int y, String name) {
		this(new Coordinate(x,y),name);
	}

	@Override
	protected void calculateComponents() {
		Coordinate origin = super.getOrigin();
		double x = origin.getX()+0.5;
		double y = origin.getY()-0.5;
		Component component = new Point(x,y,1);
		super.setComponents(Arrays.asList(component));
	}

	@Override
	public void rotate() {
		// TODO Auto-generated method stub

	}

	@Override
	public Coordinate calculateBound() {
		Coordinate origin = super.getOrigin();
		double x = origin.getX()+1;
		double y = origin.getY()-1;
		return new Coordinate(x,y);
	}
}
