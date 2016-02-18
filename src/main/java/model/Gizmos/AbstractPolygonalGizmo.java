package model.Gizmos;

import java.util.ArrayList;
import java.util.List;

import model.Gizmo;
import physics.LineSegment;
import physics.Vect;

public abstract class AbstractPolygonalGizmo extends Gizmo {

	private List<Vect> coordinates;

	public AbstractPolygonalGizmo(Vect origin, Vect size, String name) {
		super(origin, size, name);

		coordinates = calculateCoordinates();
		setCircles(calculateCircles());
		setLines(calculateLines());
	}

	protected abstract List<Vect> calculateCoordinates();

	private List<physics.Circle> calculateCircles() {
		List<physics.Circle> calcCircles = new ArrayList<>();
		for (Vect coord : coordinates) {
			physics.Circle circle = new physics.Circle(coord, 0);
			calcCircles.add(circle);
		}
		return calcCircles;
	}

	private List<LineSegment> calculateLines() {
		List<LineSegment> calcLines = new ArrayList<>();
		for (int i = 0; i < coordinates.size(); i++) {
			Vect a = coordinates.get(i);
			Vect b = coordinates.get((i + 1) % coordinates.size());
			LineSegment line = new LineSegment(a, b);
			calcLines.add(line);
		}
		return calcLines;
	}
}
