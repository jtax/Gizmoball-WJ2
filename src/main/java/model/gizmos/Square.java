package model.gizmos;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Gizmo;
import physics.LineSegment;
import physics.Vect;

/**
 * Created by baird on 06/02/2016.
 */
public class Square extends Gizmo {

	private int reflectionCoefficient = 1;
	private List<Vect> coordinates;
	private String saveInfo;
	private String name;
	private int rotation;

	public Square(Vect origin, String name) {
		super(origin, name);
		this.name = name;
		rotation = 2;
		coordinates = calculateCoordinates();
		super.setCircles(calculateCircles());
		super.setLines(calculateLines());
		super.setColor(new Color(0xc0392b));
		saveInfo = "Square" + " " + name + " " + (int) origin.getXCoord() + " " + (int) origin.getyCoord();
	}

	@Override
	public void trigger() {

	}

	public Square(int x, int y, String name) {
		this(new Vect(x, y), name);
	}

	private List<Vect> calculateCoordinates() {
		Vect topLeft = origin;
		Vect topRight = new Vect(bound.x(), origin.y());
		Vect bottomRight = bound;
		Vect bottomLeft = new Vect(origin.x(), bound.y());
		return Arrays.asList(topLeft, topRight, bottomRight, bottomLeft);
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

	public void rotate() {
		Vect centerPoint = getCenterPoint();
		rotation = (rotation + 1) % 4;
		//setSaveInfo();
		List<Vect> newCoords = new ArrayList<Vect>();
		for (int i = 0; i < coordinates.size(); i++) {
			coordinates.set(i, rotationMatrix(coordinates.get(i), centerPoint, 90));
		}
		super.setCircles(calculateCircles());
		super.setLines(calculateLines());
	}

	public Vect rotationMatrix(Vect coordinate, Vect center, double angle) {
		double angleR = Math.toRadians(angle);
		Vect coord = coordinate.minus(center);
		double newX = coord.x() * Math.cos(angleR) - coord.y() * Math.sin(angleR);
		double newY = coord.x() * Math.sin(angleR) + coord.y() * Math.cos(angleR);
		Vect rotatedCoord = new Vect(newX, newY).plus(center);
		return rotatedCoord;
	}

	public Vect getCenterPoint() {
		double width = bound.x() - origin.x();
		double height = bound.y() - origin.y();
		return origin.plus(new Vect(width / 2, height / 2));
	}

	public Vect calculateBound() {
		Vect origin = super.getOrigin();
		Vect bound = new Vect(1, 1);
		return origin.plus(bound);
	}

	@Override
	public List<Vect> getCoordinates() {
		return coordinates;
	}

	@Override
	public String getSaveInfo() {
		return saveInfo;
	}

	@Override
	public int getRotation() {
		return rotation;
	}

	public void move(Vect distance) {
		super.origin = super.origin.plus(distance);
		super.bound = super.bound.plus(distance);
		coordinates = calculateCoordinates();
		super.setCircles(calculateCircles());
		super.setLines(calculateLines());
		saveInfo = "Square" + " " + name + " " + (int) origin.getXCoord() + " " + (int) origin.getyCoord();
	}


	@Override
	public boolean equals(Object other) {
		if (other.getClass() != Square.class) {
			return false;
		}
		//We know that its a square
		Square otherSquare = (Square) other;

		if (!origin.equals(otherSquare.getOrigin())) {
			return false;
		}
		if (!bound.equals(otherSquare.getBound())) {
			return false;
		}
		if (rotation != otherSquare.rotation) {
			return false;
		}
		if (!name.equals(otherSquare.name)) {
			return false;
		}
		return true;
	}
}