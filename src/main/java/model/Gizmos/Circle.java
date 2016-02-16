package model.Gizmos;

import model.Gizmo;
import physics.Vect;

import java.util.Arrays;

/**
 * Created by baird on 06/02/2016.
 */
public class Circle extends Gizmo {

	public Circle(Vect origin, String name) {
		super(origin, name);
		super.setCircles(Arrays.asList(calculateCircle()));
	}
	
	public Circle(int x, int y, String name) {
		this(new Vect(x, y), name);
	}

	private physics.Circle calculateCircle() {
		return new physics.Circle(super.getOrigin(), 0.5);
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
