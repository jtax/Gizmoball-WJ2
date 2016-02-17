package model.Gizmos;

import model.Gizmo;
import physics.LineSegment;
import physics.Vect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Triangle extends Gizmo {

	private List<Vect> coordinates;
	public Triangle(Vect origin, String name) {
		super(origin, name);
		coordinates = calculateCoordinates();
		super.setCircles(calculateCircles());
		super.setLines(calculateLines());
	}

	public Triangle(double x, double y, String name) {
		this(new Vect(x, y), name);
	}

	private List<Vect> calculateCoordinates() {
		Vect a = origin;
		Vect b = bound;
		Vect c = new Vect(origin.x(), bound.y());
		return Arrays.asList(a, b, c);
	}

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

}