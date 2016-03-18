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
	private final static Vect FLIPPER_SIZE = new Vect(2,2);
	private final static double REFLECTION_COEFFICIENT = 0.95;
	
	protected Boolean flipping = false;
	protected Boolean flippingUp = false;
	protected Boolean finishedFlipping = false;
	private double flipRotation = 0;
	private Direction direction;
	private double angularVelocity;
    private int rotation;
    private List<Vect> coordinates;
    private double flipRotationThisTick;

    public Flipper(Vect origin, String name, Direction direction) {
		super(origin, name, REFLECTION_COEFFICIENT);
		angularVelocity = Board.moveTime * 1080;
		rotation = 0;
		flipRotation = 0;
		this.direction = direction;
		
		coordinates = calculateCoordinates();
		super.setCircles(calculateCircles());
		super.setLines(calculateLines());
		super.setColor(new Color(0xf1c40f));
	}
    
    /**
     * Default to LEFT direction for backwards-compatibility.
     * 
     * @param origin
     * @param name
     */
    public Flipper(Vect origin, String name) {
    	this(origin, name, Direction.LEFT);
    }
    
    public Flipper(int x, int y, String name, Direction direction) {
		this(new Vect(x, y), name, direction);
	}
    
    /**
     * Default to LEFT direction for backwards-compatibility.
     * 
     * @param x
     * @param y
     * @param name
     */
    public Flipper(int x, int y, String name) {
    	this(x, y, name, Direction.LEFT);
    }

	public Vect getPivotPoint() {
		Vect topLeftPivotPoint = origin.plus(FLIPPER_SIZE.times(1 / 8.0)),
				bottomRightPivotPoint = origin.plus(FLIPPER_SIZE.times(7 / 8.0)),
				topRightPivotPoint = new Vect(bottomRightPivotPoint.x(), topLeftPivotPoint.y()),
				bottomLeftPivotPoint = new Vect(topLeftPivotPoint.x(), bottomRightPivotPoint.y()); // :-)
		
		switch (getRotationAsLeftFlipper()) {
		case 0:
			return (direction == Direction.LEFT) ? topLeftPivotPoint : bottomLeftPivotPoint;
		case 1:
			return (direction == Direction.LEFT) ? topRightPivotPoint : topLeftPivotPoint;
		case 2:
			return (direction == Direction.LEFT) ? bottomRightPivotPoint : topRightPivotPoint;
		case 3:
			return (direction == Direction.LEFT) ? bottomLeftPivotPoint : bottomRightPivotPoint;
		default:
			System.err.printf("Error: %s's rotation is <0 or >3.%n", toString());
			return null;
		}
	}

	public double getAngularVelocity() {
		return angularVelocity;
	}


	public void move(Vect distance) {
		origin = origin.plus(distance);
		coordinates = calculateCoordinates();
		super.setCircles(calculateCircles());
		super.setLines(calculateLines());
	}

	@Override
	public void trigger() {
		flipping = true;
		flippingUp = !flippingUp;
		flip();
		super.trigger();
	}

	private List<Vect> calculateCoordinates() {
		List<Vect> coordinates;
		
		switch (getRotationAsLeftFlipper()) {
		case 0:
			coordinates = coordinatesOfFlipperAtLeftOfBoundingBox();
			break;
		case 1:
			coordinates = coordinatesOfFlipperAtTopOfBoundingBox();
			break;
		case 2:
			coordinates = coordinatesOfFlipperAtRightOfBoundingBox();
			break;
		case 3:
			coordinates = coordinatesOfFlipperAtBottomOfBoundingBox();
			break;
		default:
			System.err.println("Can't get the coordinates of Flipper because its rotation is <0 or >3.");
			coordinates = Arrays.asList();
		}
		
		return coordinates;
	}
	
	private List<Vect> coordinatesOfFlipperAtLeftOfBoundingBox() {
		Vect topLeft, topRight, bottomRight, bottomLeft;
		
		topLeft = origin;
		topRight = origin.plus(new Vect(0.5, 0));
		bottomRight = origin.plus(new Vect(0.5, 2));
		bottomLeft = origin.plus(new Vect(0, 2));
		
		return Arrays.asList(topLeft,topRight, bottomRight,  bottomLeft);
	}

	private List<Vect> coordinatesOfFlipperAtTopOfBoundingBox() {
		Vect topLeft, topRight, bottomRight, bottomLeft;
		
		topLeft = origin;
		topRight = origin.plus(new Vect(2, 0));
		bottomRight = origin.plus(new Vect(2, 0.5));
		bottomLeft = origin.plus(new Vect(0, 0.5));
		
		return Arrays.asList(topLeft,topRight, bottomRight,  bottomLeft);
	}

	private List<Vect> coordinatesOfFlipperAtRightOfBoundingBox() {
		Vect topLeft, topRight, bottomRight, bottomLeft;
		
		topLeft = origin.plus(new Vect(1.5, 0));
		topRight = origin.plus(new Vect(2, 0));
		bottomRight = origin.plus(new Vect(2, 2));
		bottomLeft = origin.plus(new Vect(1.5, 2));
		
		return Arrays.asList(topLeft,topRight, bottomRight,  bottomLeft);
	}

	private List<Vect> coordinatesOfFlipperAtBottomOfBoundingBox() {
		Vect topLeft, topRight, bottomRight, bottomLeft;
		
		topLeft = origin.plus(new Vect(0, 1.5));
		topRight = origin.plus(new Vect(2, 1.5));
		bottomRight = origin.plus(new Vect(2, 2));
		bottomLeft = origin.plus(new Vect(0, 2));
		
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
		rotation = (rotation + 1) % 4;
		coordinates = calculateCoordinates();
		super.setCircles(calculateCircles());
		super.setLines(calculateLines());
	}

	private Vect rotationMatrix(Vect coordinate, Vect center, double angle) {
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
		flip(true);
	}
	
	// Chris hacked this
	public double flip(boolean sideEffects) {
		double rotate = 0;

		// Execute only if Flipper is in rotating stage
		if (flipping) {
			rotate = angularVelocity;
			if (!finishedFlipping && flippingUp) {

				// rotate up
				if (angularVelocity + flipRotation > 90) {
					rotate = 90 - flipRotation;
					if (sideEffects) {
						finishedFlipping = true;
						flipping = false;
					}
				}
				if (sideEffects) {
					flipRotation += rotate;
				}

				// Sets direction up
				rotate = rotate * -1;
			} else {

				// rotate down
				if (angularVelocity % flipRotation != angularVelocity) {
					rotate = flipRotation;
					if (sideEffects) {
						flipping = false;
						finishedFlipping = false;
					}
				}

				if (sideEffects)
					flipRotation -= rotate;
			}

			if (direction == Direction.RIGHT)
				rotate *= -1;

			if (sideEffects) {
				for (int i = 0; i < coordinates.size(); i++) {
					coordinates.set(i, rotationMatrix(coordinates.get(i), getPivotPoint(), rotate));
				}
				super.setCircles(calculateCircles());
				super.setLines(calculateLines());
			}
		}

		return rotate;
	}

	/**	
	 * For tests
	 */
	public Direction getDirection() {

		return direction;
	}
	
	@Override
	public Vect getBound() {
		return calculateBound();
	}

	public String getSaveInfo() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("\"");
		sb.append((direction == Direction.LEFT) ? "Left" : "Right");
		sb.append("Flipper\" ");
		sb.append(getName());
		sb.append(" ");
		sb.append((int) origin.getXCoord());
		sb.append(" ");
		sb.append((int) origin.getyCoord());
		
		return sb.toString();
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

		if (!getPivotPoint().equals(otherFlipper.getPivotPoint())) {
			return false;
		}
		if (angularVelocity != otherFlipper.getAngularVelocity()) {
			return false;
		}

		return true;
	}

	@Override
	public Vect calculateBound() {
		return origin.plus(FLIPPER_SIZE);
	}
	
	/**
	 * 
	 * @return the rotation of flipper as it would be if its direction was Left.
	 */
	private int getRotationAsLeftFlipper() {
		int normalisedRotation = rotation;
		
		if (direction == Direction.RIGHT)
			normalisedRotation = (normalisedRotation + 2) % 4;
		
		return normalisedRotation;
	}
	
	public double getFlipRotationThisTick() {
		return flipRotationThisTick;
	}
}