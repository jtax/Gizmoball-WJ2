package model.Gizmos;

import java.awt.Color;

import physics.Vect;

/**
 * Created by baird on 06/02/2016.
 */
public class Wall extends AbstractRectangularGizmo {

	public Wall(Vect origin, Vect bound, String name) {
		super(origin, bound, name);
		super.setColor(Color.MAGENTA);
	}

	@Override
	public void rotate() {
		// TODO Auto-generated method stub

	}
}
