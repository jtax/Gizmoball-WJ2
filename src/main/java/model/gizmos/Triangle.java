package model.gizmos;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Gizmo;
import physics.LineSegment;
import physics.Vect;

public class Triangle extends Gizmo {

	private List<Vect> coordinates;
	private int rotation;
	private String saveInfo;

	public Triangle(Vect origin, String name) {
		super(origin, name);
		rotation = 2;
		coordinates = calculateCoordinates();
		super.setCircles(calculateCircles());
		super.setLines(calculateLines());
		super.setColor(Color.blue);
		setSaveInfo();
	}

	public Triangle(double x, double y, String name) {
		this(new Vect(x, y), name);
	}

	private List<Vect> calculateCoordinates() {
		Vect topLeft = origin;
		Vect topRight = new Vect(bound.x(), origin.y());
		Vect bottomLeft = new Vect(origin.x(), bound.y());
		return Arrays.asList(topLeft, topRight, bottomLeft);
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

	public void setSaveInfo() {
		saveInfo = "Triangle" + " " + super.getName() + " " + (int) origin.getXCoord() + " " + (int) origin.getyCoord();
	}

	public void rotate() {
		Vect centerPoint = getCenterPoint();
		rotation = (rotation + 1) % 4;
		setSaveInfo();
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



	@Override
	public Vect calculateBound() {
		Vect origin = super.getOrigin();
		Vect bound = new Vect(1, 1);
		return origin.plus(bound);
	}

	public String getSaveInfo() {
		return saveInfo;
	}

	public int getRotation() {
		return rotation;
	}

	@Override
	public List<Vect> getCoordinates() {
		return coordinates;
	}
}