package model.Gizmos;

import model.Gizmo;
import physics.LineSegment;
import physics.Vect;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by baird on 06/02/2016.
 */
public class Wall extends Gizmo {

	private Vect bound;
	private java.util.List<Vect> coordinates;

	public Wall(Vect origin, Vect bnd, String name) {
		super(origin, name);
		this.bound = bnd;
		super.setBound(bnd);
		coordinates = calculateCoordinates();
		super.setCircles(calculateCircles());
		super.setLines(calculateLines());
		super.setColor(Color.MAGENTA);
		}

	private List<Vect> calculateCoordinates() {
		Vect topLeft = origin;
		Vect topRight = origin.plus(new Vect(bound.x(), 0));
		Vect bottomRight = bound;
		Vect bottomLeft = origin.plus(new Vect(0, bound.y()));
		return Arrays.asList(topLeft, topRight, bottomLeft, bottomRight);
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
		for (int i = 0; i < coordinates.size() - 1; i++) {
			Vect a = coordinates.get(i);
			Vect b = coordinates.get(i + 1 % coordinates.size() - 1);
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
		return bound;
	}

}
