package model.gizmos;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Ball;
import model.Collision;
import model.Gizmo;
import model.Triggerable;
import physics.LineSegment;
import physics.Vect;

/**
 * Created by baird on 06/02/2016.
 */
public class Absorber extends Gizmo implements Triggerable {

	private Vect bound;
	private List<Vect> coordinates;
	private Ball ourBall;
	private String saveInfo;

	public Absorber(Vect origin, Vect bound, String name) {
		super(origin, name);
		this.bound = bound;
		setBound(bound);
		coordinates = calculateCoordinates();
		super.setCircles(calculateCircles());
		super.setLines(calculateLines());
		super.setColor(Color.MAGENTA);
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

	@Override
	public Vect calculateBound() {
		return bound;
	}

	public void absorb(Ball ball) {
		ourBall = ball;
		ourBall.setAbsorbed();
		positionBall();
	}

	private void releaseOurBall() {
		if (weHaveABall()) {
			double xVelocity = 0, yVelocity = -50;
			Vect velocity = new Vect(xVelocity, yVelocity);
			ourBall.setVelocity(velocity);
			ourBall.clearAbsorbed();

			ourBall = null;
		}
	}

	private void positionBall() {
		if (weHaveABall()) {
			Vect ourBound = getBound();
			double ballRadius = ourBall.getRadius();

			double ballX = ourBound.x() - ballRadius - .25;
			double ballY = ourBound.y() - ballRadius - .25;

			ourBall.setCenter(new Vect(ballX, ballY));
		}
	}

	public String getSaveInfo() {
		return saveInfo;
	}

	@Override
	public int getRotation() {
		return 0;
	}

	private boolean weHaveABall() {
		return ourBall != null;
	}

	@Override
	public List<Vect> getCoordinates() {
		return coordinates;
	}

	@Override
	public void handle(Collision c) {
		Ball ball = c.getBall();
		absorb(ball);
	}
}
