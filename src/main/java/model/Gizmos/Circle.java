package model.Gizmos;

import model.Gizmo;
import physics.Vect;

import java.util.Arrays;

/**
 * Created by baird on 06/02/2016.
 */
public class Circle extends Gizmo {

	private final static Vect size = new Vect (1, 1);
	
	public Circle(Vect origin, String name) {
		super(origin, size, name);
		super.setCircles(Arrays.asList(calculateCircle()));
	}
	
	public Circle(int x, int y, String name) {
		this(new Vect(x, y), name);
	}

	private physics.Circle calculateCircle() {
		return new physics.Circle(super.getOrigin().plus(new Vect(0.5, 0.5)), 0.5);
	}

	@Override
	public void rotate() {
		// TODO Auto-generated method stub

	}
}
