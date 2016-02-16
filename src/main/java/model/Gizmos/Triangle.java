package model.Gizmos;

import model.Gizmo;
import physics.Vect;

import java.util.Arrays;


public class Triangle extends Gizmo {

	public Triangle(Vect origin, String name) {
		super(origin, name);
		calculateComponents();
	}

	@Override
	protected void calculateComponents() {
		Vect origin = super.getOrigin();
		double x = origin.x();
		double y = origin.y();
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
	public Vect calculateBound() {
		Vect origin = super.getOrigin();
		Vect bound = new Vect(1, -1);
		return origin.plus(bound);
	}

}