package model.gizmos;

import model.*;
import physics.LineSegment;
import physics.Vect;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Gizmoball - Absorber
 * Created by Group WJ2 on 06/02/2016.
 * Authors: J Baird, C Bean, N Stannage, U Akhtar, L Sakalauskas
 */
public class Absorber extends Gizmo implements Triggerable {

	private Vect bound;
	private List<Vect> coordinates;
	private List<String> connections = new ArrayList<>();
	private List<String> keyConnects = new ArrayList<>();
	private Ball ourBall;
	private String saveInfo;
	private final String name;
	private int rotation;

	public Absorber(Vect origin, Vect bound, String name) {
		super(origin, name);
		rotation = 0;
		this.bound = bound;
		this.name = name;
		setBound(bound);
		coordinates = calculateCoordinates();
		super.setCircles(calculateCircles());
		super.setLines(calculateLines());
		super.setColor(new Color(0x8e44ad));
		saveInfo = "Absorber" + " " + name + " " + (int) origin.getXCoord() + " " + (int) origin.getyCoord() + " "
				+ (int) bound.getXCoord() + " " + (int) bound.getyCoord();
	}

	public Absorber(int originX, int originY, int boundX, int boundY, String name) {
		this(new Vect(originX, originY), new Vect(boundX, boundY), name);
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

	@Override
	public void trigger() {
		releaseOurBall();
	}

	public void rotate() {
		Vect centerPoint = getCenterPoint();
		rotation = (rotation + 1) % 4;
		//setSaveInfo();
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
		return new Vect(newX, newY).plus(center);
	}

	public Vect getCenterPoint() {
		double width = bound.x() - origin.x();
		double height = bound.y() - origin.y();
		return origin.plus(new Vect(width / 2, height / 2));
	}

	@Override
	public Vect calculateBound() {
		return bound;
	}

	public void absorb(Ball ball) {
		ourBall = ball;
		ourBall.setAbsorbed();
		positionBall();
	}

	public void releaseOurBall() {
		if (weHaveABall()) {
			double xVelocity = 0, yVelocity = -50;
			Vect velocity = new Vect(xVelocity, yVelocity);
			ourBall.setVelocity(velocity);
			ourBall.clearAbsorbed();

			ourBall = null;
		}
	}

	public void positionBall() {
		if (weHaveABall()) {
			Vect ourBound = getBound();
			double ballRadius = ourBall.getRadius();

			double ballX = ourBound.x() - ballRadius;
			double ballY = ourBound.y() - ballRadius;

			ourBall.setCenter(new Vect(ballX, ballY));
		}
	}

	public String getSaveInfo() {
		return saveInfo;
	}

	@Override
	public int getRotation() {
		return rotation;
	}

	public boolean weHaveABall() {
		return ourBall != null;
	}

	@Override
	public List<Vect> getCoordinates() {
		return coordinates;
	}

	@Override
	public void subHandle(Collision c) {
		Ball ball = c.getBall();
		absorb(ball);
	}

	public void move(Vect distance) {
		super.origin = super.origin.plus(distance);
		this.bound = bound.plus(distance);
		super.bound = bound;
		coordinates = calculateCoordinates();
		super.setCircles(calculateCircles());
		super.setLines(calculateLines());
		saveInfo = "Absorber" + " " + name + " " + (int) origin.getXCoord() + " " + (int) origin.getyCoord() + " "
				+ (int) bound.getXCoord() + " " + (int) bound.getyCoord();
		positionBall();
	}

	@Override
	public boolean equals(Object other) {
		if (other.getClass() != Absorber.class) {
			return false;
		}
		//We know that its a absorber
		Absorber otherAbsorber = (Absorber) other;

		if (!origin.equals(otherAbsorber.getOrigin())) {
			return false;
		}
		if (!bound.equals(otherAbsorber.getBound())) {
			return false;
		}
		if (rotation != otherAbsorber.rotation) {
			return false;
		}
		return coordinates.equals(otherAbsorber.coordinates);
	}


	public void gizmoConnect(IElement secondElement){
		this.addTriggerable((Triggerable) secondElement);
		connections.add("Connect " +this.getName()+ " "+ secondElement.getName());
	}

	@Override
	public List<String> getConnections() {
		return connections;
	}


	public void addKeyConnect(int keycode){
		this.addKeyPressTrigger(keycode);
		keyConnects.add("KeyConnect Key "+ keycode+ " change "+ this.getName());
	}

	public List<String> returnKeyConnects(){return keyConnects;	}

	public void removeKeyConnects(){
		keyConnects.clear();
		this.clearKeyTriggers();
	}

	@Override
	public void clearConnections() {
		connections.clear();
		this.clearTriggerable();
	}

	@Override
	public void clearKeyConnections() {
		keyConnects.clear();
		this.clearKeyPressTrigger();
	}
}
