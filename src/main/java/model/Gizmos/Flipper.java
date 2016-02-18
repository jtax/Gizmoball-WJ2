package model.Gizmos;

import model.Gizmo;
import physics.Angle;
import physics.Vect;

/**
 * Created by baird on 06/02/2016.
 */
public class Flipper extends Gizmo {
	
	private final static Vect size = new Vect(2, -2);

	public Flipper(Vect origin, String name) {
		super(origin, size, name);
	}

	@Override
	public void rotate() {
		getBound().rotateBy(new Angle(90));
	}

	public void rotateBack() {
		getBound().rotateBy(new Angle(-90));
	}
}