package model.gizmos;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import model.Gizmo;
import physics.Vect;

/**
 * Created by baird on 06/02/2016.
 */
public class Circle extends Gizmo {

	Vect center;

	public Circle(Vect origin, String name) {
		super(origin, name);
		super.setCircles(Arrays.asList(calculateCircle()));
		super.setColor(Color.GREEN);
	}

	public Circle(int x, int y, String name) {
		this(new Vect(x, y), name);
	}

	private physics.Circle calculateCircle() {
		return new physics.Circle(super.getOrigin().plus(new Vect(0.5, 0.5)), 0.5);
	}

	private void setCenter() {
		center = super.getOrigin().plus(new Vect(0.5, 0.5));
	}

	@Override
	public void rotate() {
		// TODO Auto-generated method stub

	}

	@Override
	public Vect calculateBound() {
		Vect origin = super.getOrigin();
		Vect bound = new Vect(1, 1);
		return origin.plus(bound);
	}

	@Override
	public List<Vect> getCoordinates() {
		return Arrays.asList(center);
	}
}
