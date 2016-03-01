package model.Gizmos;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Direction;
import model.Gizmo;
import model.Triggerable;
import physics.LineSegment;
import physics.Vect;

/**
 * Created by baird on 06/02/2016.
 */
public class Flipper extends Gizmo implements Triggerable {
	private List<Vect> coordinates;

	protected Boolean rotated = false;
	private Direction direction = Direction.LEFT;
	private KeyEvent keyPressTrigger;
	private String saveInfo;
	private String name;
	private String saveDirection;

	public Flipper(Vect origin, String name) {

		super(origin,name);
		saveDirection = "Left";
		rotation = 90;
		coordinates = calculateCoordinates();
		super.setCircles(calculateCircles());
		super.setLines(calculateLines());
		super.setColor(Color.yellow);
		setSaveInfo();
	}


	@Override
	public void trigger() {

		flip();
		coordinates = calculateCoordinates();
		super.setCircles(calculateCircles());
		super.setLines(calculateLines());
		super.trigger();
	}

	public Flipper(int x, int y, String name) {
		this(new Vect(x, y), name);
	}

	public void setDirection(Direction direction) {

		this.direction = direction;
		if(direction == Direction.RIGHT) {
			saveDirection = "Right";
			this.origin = origin.plus(new Vect(1.5, 0));
			this.bound = calculateBound();
			this.coordinates = calculateCoordinates();
		}
		setSaveInfo();
	}


	private List<Vect> calculateCoordinates() {
		Vect topLeft = origin;
		Vect topRight = new Vect(bound.x(),origin.y());
		Vect bottomRight = bound;
		Vect bottomLeft = new Vect(origin.x(),bound.y());
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
	public void rotate() {
		//
	}


	/**
	 * Flips a Flipper based on its direction and weather we need to rotate back
	 */
	public void flip() {

		if (direction == Direction.RIGHT) {
			if (rotated) {
				this.origin = new Vect(bound.x(), bound.y()).minus(new Vect(0.5,0.5));
				bound = calculateBound();
			} else {
				this.bound  = origin.plus(new Vect(0.5,0.5));
				this.origin = origin.minus(new Vect(1.5, 0));
			}
		} else {
			if (rotated) {
				this.bound = origin.plus(new Vect(0.5, 2));
			} else {
				this.bound = origin.plus(new Vect(2, 0.5));
			}
		}

		rotated = !rotated;
	}

	/**
	 * For tests
	 */
	public Boolean isFlipped()
	{
		return  rotated;
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

	public void setSaveInfo(){
		saveInfo = "Flipper" + saveDirection+" " +super.getName() + " " + origin.getXCoord() + " " + origin.getyCoord()  +  "/n";
	}

	@Override
	public List<Vect> getCoordinates() {
		return coordinates;
	}
}