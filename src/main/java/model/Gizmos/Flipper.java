package model.Gizmos;

import model.Component;
import model.Components.Line;
import model.Coordinate;
import model.Direction;
import model.Gizmo;

import java.util.Arrays;

/**
 * Created by baird on 06/02/2016.
 */
public class Flipper extends Gizmo {

	protected Boolean rotated = false;
	private Direction direction = Direction.LEFT;

	public Flipper(Coordinate origin, String name) {
		super(origin,name);
		calculateComponents();

		this.reflection = 0.95;
		rotation = 90;
	}

	public void setDirection(Direction direction) {

		this.direction = direction;
	}

	@Override
	protected void calculateComponents() {
		Coordinate origin = super.getOrigin();
		double x = origin.getX();
		double y = origin.getY();
		Component top = new Line(x,y, x+1,y);
		Component right = new Line(x+1,y, x+1,y-2);
		Component bottom = new Line(x,y-2, x+1,y-2);
		Component left = new Line(x,y, x,y-2);
		super.setComponents( Arrays.asList(top,right,bottom,left));
	}

	@Override
	public void rotate() {

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
			bound.rotate(origin, rotation * rotateBackConst);

		} else {
			bound.rotate(origin, rotation * rotateConst);
		}

		rotated = !rotated;
	}


	@Override
	public Coordinate calculateBound() {
		Coordinate bound = super.getOrigin();
		bound.setX(bound.getX() + 2);
		bound.setY(bound.getX() - 2);
		return bound;
	}
}