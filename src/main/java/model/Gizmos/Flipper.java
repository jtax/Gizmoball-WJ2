package model.Gizmos;

import model.Direction;
import model.Gizmo;
import model.Triggerable;
import physics.Angle;
import physics.LineSegment;
import physics.Vect;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by baird on 06/02/2016.
 */
public class Flipper extends Gizmo implements Triggerable {
	private List<Vect> coordinates;

	protected Boolean rotated = false;
	private Direction direction = Direction.LEFT;
	private KeyEvent keyPressTrigger;

	public Flipper(Vect origin, String name) {

		super(origin,name);

		rotation = 90;
		coordinates = calculateCoordinates();
		super.setCircles(calculateCircles());
		super.setLines(calculateLines());
	}

	@Override
	public void trigger() {

		flip();
	}

	public Flipper(int x, int y, String name) {
		this(new Vect(x, y), name);
	}

	public void setDirection(Direction direction) {

		this.direction = direction;

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
		bound.rotateBy(new Angle(90));
	}


	/**
	 * Flips a Flipper based on its direction and weather we need to rotate back
	 */
	public void flip() {
		int rotateBackConst;
		int rotateConst;

		if (direction == Direction.LEFT) {
			rotateBackConst = 1;
			rotateConst = -1;
		} else {
			rotateBackConst = -1;
			rotateConst = 1;
		}

		if (rotated) {
			bound = bound.rotateBy(new Angle(rotation * rotateBackConst));

		} else {
			bound = bound.rotateBy(new Angle(rotation * rotateConst));
		}

		rotated = !rotated;
	}

	@Override
	public Vect calculateBound() {
		Vect origin = super.getOrigin();
		Vect bound = new Vect(2, 0.5);
		return origin.plus(bound);
	}

}