package model.Gizmos;

import java.util.Arrays;
import java.util.List;

import physics.Vect;

public class Triangle extends AbstractPolygonalGizmo {

	private final static Vect size = new Vect(1, -1);

	public Triangle(Vect origin, String name) {
		super(origin, size, name);
	}

	public Triangle(double x, double y, String name) {
		this(new Vect(x, y), name);
	}

	protected List<Vect> calculateCoordinates() {
		Vect a = getOrigin();
		Vect b = getBound();
		Vect c = a.plus(new Vect(0, b.y()));
		return Arrays.asList(a, b, c);
	}

	@Override
	public void rotate() {
		// TODO Auto-generated method stub

	}

}