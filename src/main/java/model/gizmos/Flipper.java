package model.gizmos;

import model.Board;
import model.Direction;
import model.Gizmo;
import model.Triggerable;
import physics.LineSegment;
import physics.Vect;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by baird on 06/02/2016.
 */
public class Flipper extends Gizmo implements Triggerable {
	private List<Vect> coordinates;

	protected Boolean rotating = false;
	protected Boolean rotatingUp = false;
	protected Boolean finishedRotation = false;
	private double movementRotation = 0;
	private Direction direction = Direction.LEFT;
	private int directionConst = 1;
	private String saveInfo;
	private String name;
	private String saveDirection;
	private Vect pivotPoint;
	private double angularVelocity;
    private int rotation;

    public Flipper(Vect origin, String name) {

		super(origin, name);
		angularVelocity = Board.moveTime * 1080;
		saveDirection = "Left";
		rotation = 0;
		movementRotation = 0;
		coordinates = calculateCoordinates();
		pivotPoint = coordinates.get(0).plus(new Vect(0.25,0.25));

		super.setCircles(calculateCircles());
		super.setLines(calculateLines());
		super.setColor(new Color(0xf1c40f));
		setSaveInfo();
	}

	public Vect getPivotPoint() {
		return pivotPoint;
	}

	public double getAngularVelocity() {
		return angularVelocity;
	}


	public void move(Vect distance) {
		super.origin = super.origin.plus(distance);
		super.bound = super.bound.plus(distance);
		coordinates = calculateCoordinates();
		super.setCircles(calculateCircles());
		super.setLines(calculateLines());
		saveInfo = "Circle" + " " + name + " " + (int) origin.getXCoord() + " " + (int) origin.getyCoord();
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
			saveDirection = "Right";
			directionConst = -1;
			setSaveInfo();
			origin = origin.plus(new Vect(1.5, 0));
			bound = calculateBound();
			coordinates = calculateCoordinates();
			pivotPoint = coordinates.get(0).plus(new Vect(0.25,0.25));
			super.setCircles(calculateCircles());
			super.setLines(calculateLines());
		}

	}

	private List<Vect> calculateCoordinates() {

		Vect topLeft = origin;
		Vect topRight = new Vect(bound.x(), origin.y());
		Vect bottomRight = bound;
		Vect bottomLeft = new Vect(origin.x(), bound.y());

		return Arrays.asList(topLeft,topRight, bottomRight,  bottomLeft);
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
		Vect centerPoint = origin.plus(new Vect(directionConst,directionConst));

		if (Direction.RIGHT == direction) {
			centerPoint = origin.plus(new Vect(-0.5,1));

		}

		rotation = (rotation + 1) % 4;
		List<Vect> newCoords = new ArrayList<Vect>();
		for (int i = 0; i < coordinates.size(); i++) {
			coordinates.set(i, rotationMatrix(coordinates.get(i), centerPoint, 90));
		}
		super.setCircles(calculateCircles());
		super.setLines(calculateLines());


		switch (rotation) {
			case 1:
				pivotPoint = coordinates.get(1).plus(new Vect(-0.25, -0.25));
			break;
			case 2:
				pivotPoint = coordinates.get(1).plus(new Vect(0.25, -0.25));
			break;
			case 3:
				pivotPoint = coordinates.get(0).plus(new Vect(0.25, -0.25));
			break;

			case 0:
				pivotPoint = coordinates.get(1).plus(new Vect(-0.25, 0.25));
			break;
		}
	}

	public Vect rotationMatrix(Vect coordinate, Vect center, double angle) {
		double angleR = Math.toRadians(angle);
		Vect coord = coordinate.minus(center);
		double newX = coord.x() * Math.cos(angleR) - coord.y() * Math.sin(angleR);
		double newY = coord.x() * Math.sin(angleR) + coord.y() * Math.cos(angleR);
		Vect rotatedCoord = new Vect(newX, newY).plus(center);
		return rotatedCoord;
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
				coordinates.set(i, rotationMatrix(coordinates.get(i), pivotPoint, rotate * directionConst));
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
		Vect bound = new Vect(0.5, 2);
		return origin.plus(bound);
	}

	public void setSaveInfo() {
		saveInfo = saveDirection + "Flipper" + " " + super.getName() + " " + (int) origin.getXCoord() + " "
				+ (int) origin.getyCoord();
	}

	public String getSaveInfo() {
		return saveInfo;
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
		//We know that its a flipper
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

		if (!pivotPoint.equals(otherFlipper.getPivotPoint())) {
			return false;
		}
		if (angularVelocity != otherFlipper.getAngularVelocity()) {
			return false;
		}

		return true;
	}
}