package model.Gizmos;

import model.Gizmo;
import physics.Angle;
import physics.LineSegment;
import physics.Vect;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Triangle extends Gizmo {

	private List<Vect> coordinates;
	private int rotation;
	private String saveInfo;

	public Triangle(Vect origin, String name) {
		super(origin, name);
		rotation = 3;
		coordinates = calculateCoordinates();
		super.setCircles(calculateCircles());
		super.setLines(calculateLines());
		super.setColor(Color.blue);
		saveInfo = "Triangle" +" " +name + " " + (int)origin.getXCoord() + " " + (int)origin.getyCoord();
	}

	public Triangle(double x, double y, String name) {
		this(new Vect(x, y), name);
	}

	private List<Vect> calculateCoordinates() {
		Vect topLeft = origin;
		Vect topRight = new Vect(bound.x(), origin.y());
		Vect bottomRight = bound;
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


	public void rotate() {
		rotation = (rotation + 1) % 4;
		Vect topLeft = origin;
		Vect topRight = new Vect(bound.x(), origin.y());
		Vect bottomRight = bound;
		Vect bottomLeft = new Vect(origin.x(), bound.y());
		List<Vect> vects = new ArrayList<Vect>(Arrays.asList(topLeft, topRight, bottomRight, bottomLeft));
		vects.remove(rotation);
		coordinates = vects;
		super.setCircles(calculateCircles());
		super.setLines(calculateLines());
	}

	@Override
	public Vect calculateBound() {
		Vect origin = super.getOrigin();
		Vect bound = new Vect(1, 1);
		return origin.plus(bound);
	}

	public String getSaveInfo(){
		return saveInfo;
	}



	@Override
	public List<Vect> getCoordinates() {
		return coordinates;
	}
}