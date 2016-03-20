package model.gizmos;

import model.*;
import physics.LineSegment;
import physics.Vect;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Gizmoball - Flipper Created by Group WJ2 on 06/02/2016. Authors: J Baird, C
 * Bean, N Stannage, U Akhtar, L Sakalauskas
 */
public class Flipper extends Gizmo implements Triggerable {
	private final static Vect FLIPPER_SIZE = new Vect(2, 2);

	private List<Vect> coordinates;
	private List<String> connections = new ArrayList<>();
	private List<String> keyConnects = new ArrayList<>();
	private Boolean rotating = false;
	private Boolean rotatingUp = false;
	private Boolean finishedRotation = false;
	private double movementRotation = 0;
	private Direction direction = Direction.LEFT;
	private int directionConst = 1;
	private double angularVelocity;
	private int rotation;

	public Flipper(Vect origin, String name) {

		super(origin, name);
		angularVelocity = Board.moveTime * 1080;
		rotation = 0;
		movementRotation = 0;
		coordinates = calculateCoordinates(origin, bound.minus(new Vect(1.5, 0)));

		super.setCircles(calculateCircles());
		super.setLines(calculateLines());
		super.setColor(new Color(0xf1c40f));
	}

	public double getAngularVelocity() {
		return angularVelocity;
	}

	public void move(Vect distance) {
		super.origin = super.origin.plus(distance);
		super.bound = super.bound.plus(distance);
		if (direction == Direction.LEFT) {
			coordinates = calculateCoordinates(origin, bound.minus(new Vect(1.5, 0)));
		} else {
			coordinates = calculateCoordinates(origin.plus(new Vect(1.5, 0)), bound);
		}

		super.setCircles(calculateCircles());
		super.setLines(calculateLines());
	}

	@Override
	public void trigger() {

		rotating = true;
		rotatingUp = !rotatingUp;
		flip();
		super.trigger();
	}

	public Flipper(int x, int y, String name) {
		this(new Vect(x, y), name);
	}

	public void setDirection(Direction direction) {

		this.direction = direction;
		if (direction == Direction.RIGHT) {
			directionConst = -1;
			// origin = origin.plus(new Vect(1.5, 0));
			bound = calculateBound();
			coordinates = calculateCoordinates(origin.plus(new Vect(1.5, 0)), bound);
			super.setCircles(calculateCircles());
			super.setLines(calculateLines());
		}

	}

	private List<Vect> calculateCoordinates(Vect topLeft, Vect bottomRight) {
		Vect topRight = new Vect(bottomRight.x(), topLeft.y());
		Vect bottomLeft = new Vect(topLeft.x(), bottomRight.y());

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
		/*
		 * Vect centerPoint = origin.plus(new
		 * Vect(directionConst,directionConst));
		 * 
		 * if (Direction.RIGHT == direction) { centerPoint = origin.plus(new
		 * Vect(-0.5,1));
		 * 
		 * }
		 */
		Vect centerPoint = origin.plus(new Vect(1, 1));
		rotation = (rotation + 1) % 4;

		for (int i = 0; i < coordinates.size(); i++) {
			coordinates.set(i, rotationMatrix(coordinates.get(i), centerPoint, 90));
		}

		Vect last = coordinates.get(coordinates.size() - 1);
		for (int i = coordinates.size() - 1; i > 0; i--) {
			coordinates.set(i, coordinates.get(i - 1));
		}
		coordinates.set(0, last);

		super.setCircles(calculateCircles());
		super.setLines(calculateLines());
	}

	public Vect getPivotPoint() {
		Vect topLeftPivotPoint = origin.plus(FLIPPER_SIZE.times(1 / 8.0)),
				bottomRightPivotPoint = origin.plus(FLIPPER_SIZE.times(7 / 8.0)),
				topRightPivotPoint = new Vect(bottomRightPivotPoint.x(), topLeftPivotPoint.y()),
				bottomLeftPivotPoint = new Vect(topLeftPivotPoint.x(), bottomRightPivotPoint.y()); // :-)

		int rotation = this.rotation;
		if (direction == Direction.RIGHT)
			rotation = (rotation + 1) % 4;

		switch (rotation) {
		case 0:
			return topLeftPivotPoint;
		case 1:
			return topRightPivotPoint;
		case 2:
			return bottomRightPivotPoint;
		case 3:
			return bottomLeftPivotPoint;
		default:
			System.err.printf("Error: %s's rotation is <0 or >3.%n", toString());
			return null;
		}
	}

	private Vect rotationMatrix(Vect coordinate, Vect center, double angle) {
		double angleR = Math.toRadians(angle);
		Vect coord = coordinate.minus(center);
		double newX = coord.x() * Math.cos(angleR) - coord.y() * Math.sin(angleR);
		double newY = coord.x() * Math.sin(angleR) + coord.y() * Math.cos(angleR);
		Vect unTransposeCoord = roundRotationCoord(new Vect(newX, newY));
		return unTransposeCoord.plus(center);
	}

	private Vect roundRotationCoord(Vect coord) {
		double x = Math.round(coord.x() * 100000);
		double y = Math.round(coord.y() * 100000);
		x = x / 100000;
		y = y / 100000;
		return new Vect(x, y);
	}

	/**
	 * Flips a Flipper based on its direction and weather we need to rotate back
	 */
	public void flip() {
		double rotate = angularVelocity;
		// Execute only if Flipper is in rotating stage
		if (rotating) {

			if (!finishedRotation && rotatingUp) {

				// rotate up
				if (angularVelocity + movementRotation > 90) {
					rotate = 90 - movementRotation;
					finishedRotation = true;
					rotating = false;
				}
				movementRotation += rotate;

				// Sets direction up
				rotate = rotate * -1;
			} else {

				// rotate down
				if (angularVelocity % movementRotation != angularVelocity) {
					rotate = movementRotation;
					rotating = false;
					finishedRotation = false;
				}
				movementRotation -= rotate;
			}

			for (int i = 0; i < coordinates.size(); i++) {
				coordinates.set(i, rotationMatrix(coordinates.get(i), getPivotPoint(), rotate * directionConst));
			}
			super.setCircles(calculateCircles());
			super.setLines(calculateLines());
		}
	}

	/**
	 * For tests
	 */
	public Direction getDirection() {

		return direction;
	}

	@Override
	public Vect calculateBound() {
		Vect origin = super.getOrigin();
		Vect bound = new Vect(2, 2);
		return origin.plus(bound);
	}

	public String getSaveInfo() {
		String saveDirection;
		if (direction == Direction.LEFT)
			saveDirection = "Left";
		else
			saveDirection = "Right";

		return saveDirection + "Flipper" + " " + super.getName() + " " + (int) origin.getXCoord() + " "
				+ (int) origin.getyCoord();
	}

	@Override
	public int getRotation() {
		return rotation;
	}

	@Override
	public List<Vect> getCoordinates() {
		return coordinates;
	}

	@Override
	public boolean equals(Object other) {
		if (other.getClass() != Flipper.class) {
			return false;
		}
		// We know that its a flipper
		Flipper otherFlipper = (Flipper) other;

		if (!origin.equals(otherFlipper.getOrigin())) {
			return false;
		}
		if (!bound.equals(otherFlipper.getBound())) {
			return false;
		}
		if (rotation != otherFlipper.getRotation()) {
			return false;
		}
		if (!coordinates.equals(otherFlipper.getCoordinates())) {
			return false;
		}

		if (!direction.equals(otherFlipper.getDirection())) {
			return false;
		}

		if (!getPivotPoint().equals(otherFlipper.getPivotPoint())) {
			return false;
		}
		return angularVelocity == otherFlipper.getAngularVelocity();

	}

	public void gizmoConnect(IElement secondElement) {
		this.addTriggerable((Triggerable) secondElement);
		connections.add("Connect " + this.getName() + " " + secondElement.getName());
	}

	public List<String> getConnections() {
		return connections;
	}

	public void addKeyConnect(int keycode) {
		this.addKeyPressTrigger(keycode);
		keyConnects.add("KeyConnect Key " + keycode + " change " + this.getName());
	}

	public List<String> returnKeyConnects() {
		return keyConnects;
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

	@Override
	public void removeConnection(IElement element) {
		for (String connect : connections) {
			if (connect.contains(element.getName())) {
				connections.remove(connect);
			}
		}
	}

	public void printAllTheFlippingThings() {
		Vect topLeft = coordinates.get(0), topRight = coordinates.get(1), bottomRight = coordinates.get(2),
				bottomLeft = coordinates.get(3);

		System.out.print("tlx " + topLeft.x() + " tly " + topLeft.y() + " trx " + topRight.x() + " try " + topRight.y()
				+ " brx " + bottomRight.x() + " bry " + bottomRight.y() + " blx " + bottomLeft.x() + " bly "
				+ bottomLeft.y() + "\n");
	}
}