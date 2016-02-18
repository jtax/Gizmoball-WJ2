package model.Gizmos;

import java.util.Arrays;
import java.util.List;

import physics.Vect;

public abstract class AbstractRectangularGizmo extends AbstractPolygonalGizmo {

	protected AbstractRectangularGizmo(Vect origin, Vect size, String name) {
		super(origin, size, name);
	}

	protected List<Vect> calculateCoordinates() {
		Vect topLeft = getOrigin();
		Vect bottomRight = getBound();
		Vect topRight = new Vect(bottomRight.x(), topLeft.y());
		Vect bottomLeft = new Vect(topLeft.x(), bottomRight.y());
		return Arrays.asList(topLeft, topRight, bottomRight, bottomLeft);
	}

}
