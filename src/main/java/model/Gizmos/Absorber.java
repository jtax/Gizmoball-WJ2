package model.Gizmos;

import model.Gizmo;
import physics.Vect;
import physics.Circle;
import physics.LineSegment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by baird on 06/02/2016.
 */
public class Absorber  extends Gizmo {

	private Vect bound;
	private List<Vect> coordinates;

	public Absorber(Vect origin, Vect bound, String name) {
		super(origin,name);
		this.bound = bound;
		coordinates = calculateCoordinates();
		super.setCircles(calculateCircles());
		super.setLines(calculateLines());
	}

	private List<Vect> calculateCoordinates() {
		Vect topLeft = origin;
		Vect topRight = new Vect(bound.x(), origin.y());
		Vect bottomRight = bound;
		Vect bottomLeft = new Vect(origin.x(), bound.y());
		return Arrays.asList(topLeft, topRight, bottomRight, bottomLeft);
	}

	private List<Circle> calculateCircles() {
		List<Circle> calcCircles = new ArrayList<>();
		for (Vect coord : coordinates) {
			Circle circle = new Circle(coord, 0);
			calcCircles.add(circle);
		}
		return calcCircles;
	}

	private List<LineSegment> calculateLines() {
		List<LineSegment> calcLines = new ArrayList<>();
		for (int i = 0; i < coordinates.size() - 1; i++) {
			Vect a = coordinates.get(i);
			Vect b = coordinates.get(i + 1 % coordinates.size() - 1);
			LineSegment line = new LineSegment(a, b);
			calcLines.add(line);
		}
		return calcLines;
	}

	@Override
	public void trigger() {

	}

	@Override
	public void rotate() {
		// TODO Auto-generated method stub

	}

	@Override
	public Vect calculateBound() {
		return bound;
	}

	@Override
	public List<Vect> getCoordinates() {
		return coordinates;
	}
}
