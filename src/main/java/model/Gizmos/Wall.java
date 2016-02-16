package model.Gizmos;

import model.Gizmo;
import physics.Vect;

import java.awt.*;
import java.util.Arrays;

/**
 * Created by baird on 06/02/2016.
 */
public class Wall extends Gizmo {

	Vect bound;

	public Wall(Vect origin, Vect bound, String name) {
		super(origin, name);
		this.bound = bound;
		calculateComponents();

		}

	@Override
	protected void calculateComponents() {
		Component Line = new Line(origin.x(), origin.y(), bound.x(), bound.y());
		super.setComponents(Arrays.asList(Line));
		super.setColor(Color.blue);
	}

	@Override
	public void rotate() {
		// TODO Auto-generated method stub

	}

	@Override
	public Vect calculateBound() {
		return bound;
	}

}
