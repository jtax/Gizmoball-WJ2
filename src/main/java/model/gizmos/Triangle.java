package model.gizmos;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Gizmo;
import model.IElement;
import model.Triggerable;
import physics.LineSegment;
import physics.Vect;

public class Triangle extends Gizmo {

	private List<Vect> coordinates;
	private List<String> connections = new ArrayList<>();
	private List<String> keyConnects = new ArrayList<>();
	private int rotation;
	private String saveInfo;

	public Triangle(Vect origin, String name) {
		super(origin, name);
		rotation = 0;
		coordinates = calculateCoordinates();
		super.setCircles(calculateCircles());
		super.setLines(calculateLines());
		super.setColor(new Color(0x2980b9));
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

	private void setSaveInfo() {
		saveInfo = "Triangle" + " " + super.getName() + " " + (int) origin.getXCoord() + " " + (int) origin.getyCoord();
	}

	public void rotate() {
		Vect centerPoint = getCenterPoint();
		rotation =( (rotation + 1) % 4);
		setSaveInfo();
		for (int i = 0; i < coordinates.size(); i++) {
			coordinates.set(i, rotationMatrix(coordinates.get(i), centerPoint));
		}
		super.setCircles(calculateCircles());
		super.setLines(calculateLines());
	}

	private Vect rotationMatrix(Vect coordinate, Vect center) {
		double angleR = Math.toRadians((double) 90);
		Vect coord = coordinate.minus(center);
		double newX = coord.x() * Math.cos(angleR) - coord.y() * Math.sin(angleR);
		double newY = coord.x() * Math.sin(angleR) + coord.y() * Math.cos(angleR);
		return new Vect(newX, newY).plus(center);
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


	public void move(Vect distance) {
		super.origin = super.origin.plus(distance);
		super.bound = super.bound.plus(distance);
		coordinates = calculateCoordinates();
		super.setCircles(calculateCircles());
		super.setLines(calculateLines());
		setSaveInfo();
	}

	public boolean equals(Object other) {
		if (other.getClass() != Triangle.class) {
			return false;
		}
		//We know that its a triangle
		Triangle otherTriangle = (Triangle) other;

		if (!origin.equals(otherTriangle.getOrigin())) {
			return false;
		}
		if (!bound.equals(otherTriangle.getBound())) {
			return false;
		}
		if (rotation != otherTriangle.rotation) {
			return false;
		}
		if(!coordinates.equals(otherTriangle.getCoordinates())){
			return false;
		}
		return getCenterPoint().equals(otherTriangle.getCenterPoint());
	}


	public void gizmoConnect(IElement secondElement){
		this.addTriggerable((Triggerable) secondElement);
		connections.add("Connect " +this.getName()+ " "+ secondElement.getName());
	}

	public List<String> getConnections() {
		return connections;
	}

	public void addKeyConnect(int keycode){
		this.addKeyPressTrigger(keycode);
		keyConnects.add("KeyConnect Key "+ keycode+ " change "+ this.getName());
	}

	public List<String> returnKeyConnects(){
		return keyConnects;
	}

	public void removeKeyConnects(){
		keyConnects.clear();
		this.clearKeyTriggers();
	}
}