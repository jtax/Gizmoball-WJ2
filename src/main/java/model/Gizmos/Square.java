package model.Gizmos;

import physics.Vect;

/**
 * Created by baird on 06/02/2016.
 */
public class Square extends AbstractRectangularGizmo {

	private int reflectionCoefficient = 1;
	private final static Vect size = new Vect(1, 1);

	public Square(Vect origin, String name) {
		super(origin, size, name);
	}

	public Square(int x, int y, String name) {
		this(new Vect(x, y), name);
	}

	@Override
	public void rotate() {
		// Pointless for Square: do nothing
	}
}