package model.gizmos;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Ball;
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

	public Absorber(Vect origin, Vect bound, String name) {
		super(origin,name);
		this.bound = bound;
		setBound(bound);
		coordinates = calculateCoordinates();
		super.setCircles(calculateCircles());
		super.setLines(calculateLines());
		super.setColor(Color.MAGENTA);
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

	@Override
	public void rotate() {
		// TODO Auto-generated method stub
	}

	@Override
	public Vect calculateBound() {
		return bound;
	}

	public void absorb(Ball ball) {
		ourBall = ball;
		positionBall();
	}

	private void releaseOurBall() {
		if (weHaveABall()) {
			double xVelocity = 0, yVelocity = -50;
			Vect velocity = new Vect(xVelocity, yVelocity);
			ourBall.setVelocity(velocity);

			ourBall = null;
		}
	}

	/**
	 * Does the absorber have your ball?
	 *
	 * @param yourBall your ball
	 * @return true if the absorber has your ball, otherwise false
	 */
	public boolean hasBall(Ball yourBall) {
		return yourBall == ourBall;
	}

	private void positionBall() {
		if (weHaveABall()) {
			Vect bound = getBound();
			double ballX = bound.x() - .25, ballY = bound.y() - .25;
			ourBall.setCenter(new Vect(ballX, ballY));
		}
	}

	private boolean weHaveABall() {
		return ourBall != null;
	}

	@Override
	public List<Vect> getCoordinates() {
		return coordinates;
	}
}
