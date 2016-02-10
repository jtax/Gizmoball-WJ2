package model.Gizmos;

import model.Gizmo;

/**
 * Created by baird on 06/02/2016.
 */
public class Flipper extends Gizmo {
	public Flipper() {
		super();

		rotation = 90;
	}

	@Override
	public void rotate() {
		bound.rotate(rotation);
	}

	public void rotateBack() {
		bound.rotate(rotation * -1);
	}
}