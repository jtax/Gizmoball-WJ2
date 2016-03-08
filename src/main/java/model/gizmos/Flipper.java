package model.gizmos;

import model.*;
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
	private Direction direction = Direction.LEFT;
	private int directionConst = 1;
	private KeyEvent keyPressTrigger;
	private String saveInfo;
	private String name;
	private String saveDirection;
	private Vect centerCoordinateTop, centerCoordinateBottom;
	private double angularVelocity;
	public Flipper(Vect origin, String name) {

		super(origin, name);
		angularVelocity = BoardManager.moveTime * 1080;
		saveDirection = "Left";
		rotation = 0;
		coordinates = calculateCoordinates();
		super.setCircles(calculateCircles());
		super.setLines(calculateLines());
		super.setColor(Color.yellow);
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
		topRight = origin.plus(new Vect(directionConst * 0.5, 0));
		bottomRight = bound.plus(new Vect(0, 0.5));
		bottomLeft = bound.plus(new Vect(directionConst * 0.5, 0));

		centerCoordinateTop = new Vect((topLeft.getXCoord() + topRight.getXCoord()) / 2, (topLeft.getyCoord() + topRight.getyCoord()) / 2);
		centerCoordinateBottom = new Vect((bottomLeft.getXCoord() + bottomRight.getXCoord()) / 2, (bottomLeft.getyCoord() + bottomRight.getyCoord()) / 2);

		if (direction == Direction.RIGHT) {
			return Arrays.asList(topRight,centerCoordinateTop.plus(new Vect(0.25, -0.25)), topLeft, bottomRight, centerCoordinateBottom.plus(new Vect(-0.25,0.25)), bottomLeft);
		} else {
			return Arrays.asList(topRight,centerCoordinateTop.plus(new Vect(-0.25,-0.25)), topLeft, bottomRight, centerCoordinateBottom.plus(new Vect(0.25,0.25)), bottomLeft);
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
				if (angularVelocity + rotation > 90) {
					rotate = 90 - rotation;
					finishedRotation = true;
					rotating = false;
				}
				rotation += rotate;

				// Sets direction up
				rotate = rotate * -1;
			} else {
				// rotate down
				if (angularVelocity % rotation != angularVelocity) {
					rotate = rotation;
					rotating = false;
					finishedRotation = false;
				}

				rotation -= rotate;
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