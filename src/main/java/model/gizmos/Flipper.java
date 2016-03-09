package model.gizmos;

import model.Direction;
import model.Gizmo;
import model.Triggerable;
import physics.LineSegment;
import physics.Vect;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
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
	private int rotationConst = 1;
	private KeyEvent keyPressTrigger;
	private String saveInfo;
	private String name;
	private String saveDirection;
	private Vect centerCoordinateTop, centerCoordinateBottom;
	private double angularVelocity;
	public Flipper(Vect origin, String name) {

		super(origin, name);
		angularVelocity = 15; // BoardManager.moveTime * 1080;
		saveDirection = "Left";
		rotation = 0;
		movementRotation = 0;
		coordinates = calculateCoordinates();
		super.setCircles(calculateCircles());
		super.setLines(calculateLines());
		super.setColor(new Color(0xf1c40f));
		setSaveInfo();
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
			origin = origin.plus(new Vect(2, 0));
			bound = calculateBound();
			coordinates = calculateCoordinates();
			super.setCircles(calculateCircles());
			super.setLines(calculateLines());
		}

	}

	private List<Vect> calculateCoordinates() {
		Vect topLeft,topRight, bottomRight, bottomLeft;

		topLeft = origin.plus(new Vect(0, 0.5));
		topRight = origin.plus(new Vect(rotationConst * 0.5, 0));
		bottomRight = bound.plus(new Vect(0, 0.5));
		bottomLeft = bound.plus(new Vect(rotationConst *  0.5, 0));



		centerCoordinateTop = new Vect((topLeft.getXCoord() + topRight.getXCoord()) / 2, (topLeft.getyCoord() + topRight.getyCoord()) / 2);
		centerCoordinateBottom = new Vect((bottomLeft.getXCoord() + bottomRight.getXCoord()) / 2, (bottomLeft.getyCoord() + bottomRight.getyCoord()) / 2);

		//return Arrays.asList(topRight,centerCoordinateTop.plus(new Vect(0.25, -0.25)), topLeft, bottomRight, centerCoordinateBottom.plus(new Vect(-0.25,0.25)), bottomLeft);

		//return Arrays.asList(topRight, topLeft, bottomRight,  bottomLeft);

		if (direction == Direction.RIGHT) {
			return Arrays.asList(topRight,centerCoordinateTop.plus(new Vect(0.25 * rotationConst, -0.25)), topLeft, bottomRight, centerCoordinateBottom.plus(new Vect(-0.25* rotationConst,0.25)), bottomLeft);
		} else {
			return Arrays.asList(topRight,centerCoordinateTop.plus(new Vect(-0.25 * rotationConst,-0.25)), topLeft, bottomRight, centerCoordinateBottom.plus(new Vect(0.25 * rotationConst,0.25)), bottomLeft);
		}
	}

	private List<physics.Circle> calculateCircles() {
		List<physics.Circle> calcCircles = new ArrayList<>();

		calcCircles.add(new physics.Circle(centerCoordinateTop, 0.25));
		calcCircles.add(new physics.Circle(centerCoordinateBottom, 0.25));

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
		setSaveInfo();

		rotationConst = -1;
		calculateCoordinates();

		bound = rotationMatrix(bound, centerPoint, 90);
		origin = rotationMatrix(origin, centerPoint, 90);


		switch (rotation) {
			case 1:
				if (direction == Direction.RIGHT) {
					bound = bound.plus(new Vect(-0.5,1.5));
					origin = origin.plus(new Vect(-0.5,1.5));
				}

			break;
			case 2:
				if (direction == Direction.RIGHT) {

					bound = bound.plus(new Vect(-1.5,0));
					origin = origin.plus(new Vect(-1.5,0));
				} else {
					bound = bound.minus(new Vect(-2,-1.5));
					origin = origin.minus(new Vect(-2,-1.5));
				}



			break;
			case 3:
				if (direction == Direction.RIGHT) {

					bound = bound.minus(new Vect(0,1.5));
					origin = origin.minus(new Vect(0,1.5));
				} else {
					bound = bound.minus(new Vect(-0.5,0));
					origin = origin.minus(new Vect(-0.5,0));
				}


			break;
			case 0:
				if (direction == Direction.RIGHT) {
					bound = bound.plus(new Vect(1.5,0));
					origin = origin.plus(new Vect(1.5,0));
				} else {
					bound = bound.plus(new Vect(2 * directionConst,-1.5));
					origin = origin.plus(new Vect(2 * directionConst,-1.5));
				}

			break;
		}



		coordinates = calculateCoordinates();
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

		if (direction == Direction.LEFT) {
			return origin.plus(new Vect(rotationConst,rotationConst));

		} else {
			return origin.plus(new Vect(0,0));
		}
	}

	/**
	 * Flips a Flipper based on its direction and weather we need to rotate back
	 */
	public void flip() {
		double rotate = angularVelocity;

		Point2D result = new Point2D.Double();

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


			// Do the rotation
			AffineTransform
					.getRotateInstance(Math.toRadians(rotate * directionConst), origin.getXCoord(), origin.getyCoord())
					.transform(bound.toPoint2D(), result);
			bound = new Vect(result.getX(), result.getY());
			coordinates = calculateCoordinates();
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
		Vect bound = new Vect(0, 1.5);
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
		return 0;
	}

	@Override
	public List<Vect> getCoordinates() {
		return coordinates;
	}

}