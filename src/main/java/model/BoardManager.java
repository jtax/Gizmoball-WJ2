package model;

import java.util.ArrayList;
import java.util.Collection;
import model.gizmos.Flipper;
import model.gizmos.Absorber;
import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

/**
 * Created by baird on 06/02/2016.
 */
public class BoardManager implements IBoardManager {
	private Board board;
	private Collision closestCollision;
	public final static double moveTime = 0.05;

	public BoardManager() {
		this(new Board(new double[] { 0.025, 0.025 }, 25, 20, 20));
	}

	public BoardManager(Board board) {
		if (board == null)
			throw new IllegalArgumentException();

		this.board = board;
	}

	@Override
	public Board getBoard() {
		return board;
	}

	@Override
	public void setBoard(Board board) {
		this.board = board;
	}

	/* ----- Physics Loop ---- */

	@Override
	public void tick() {
		Collection<Ball> newBalls = new ArrayList<>();
		for (Ball ball : board.getBalls()) {
			newBalls.add(moveBall(ball));
		}
		board.setBalls(newBalls);
	}

	private Ball moveBall(Ball ball) {
		ball.applyForces(moveTime, board.getGravityConst(), board.getFrictionConst());
		Collision collision = getTimeTillCollision(ball);

		if (collision.getTime() >= moveTime) // No Collision
			ball.moveForTime(moveTime);
		else // Collision
			collision.getHandler().handle(collision);

		return ball;

	}

	private Collision getTimeTillCollision(Ball ball) {
		closestCollision = new Collision(0, 0, Double.MAX_VALUE);
		for (IElement element : board.getElements()) {
			if (element instanceof Absorber && ball.inside(element))
				continue;

			for (Circle circle : element.getCircles()) {
				detectCircleCollision(circle, ball, element);
			}
			for (LineSegment line : element.getLines()) {
				detectLineCollision(line, ball, element);
			}

			if (element instanceof Flipper) {
				((Flipper) element).flip();
			}

		}
		for (Ball otherBall : board.getBalls()) {
			detectBallCollision(otherBall, ball);
		}
		return closestCollision;
	}

	private void detectCircleCollision(Circle circle, Ball ball, IElement element) {
		double time = Geometry.timeUntilCircleCollision(circle, ball.getCircle(), ball.getVelocity());
		if (time < closestCollision.getTime()) {
			Vect newV = Geometry.reflectCircle(circle.getCenter(), ball.getCenter(), ball.getVelocity());
			closestCollision = new Collision(newV, time, element, ball);
		}
	}

	private void detectLineCollision(LineSegment line, Ball ball, IElement element) {
		double time = Geometry.timeUntilWallCollision(line, ball.getCircle(), ball.getVelocity());
		if (time < closestCollision.getTime()) {
			Vect newV = Geometry.reflectWall(line, ball.getVelocity());
			closestCollision = new Collision(newV, time, element, ball);
		}
	}

	private void detectBallCollision(Ball otherBall, Ball ball) {
		Circle ballC = ball.getCircle(), oBallC = otherBall.getCircle();
		Vect ballV = ball.getVelocity(), oBallV = otherBall.getVelocity();
		double time = Geometry.timeUntilBallBallCollision(ballC, ballV, oBallC, oBallV);
		if (time < closestCollision.getTime()) {
			Vect newV = Geometry.reflectCircle(otherBall.getCenter(), ball.getCenter(), ballV);
			closestCollision = new Collision(newV, time, otherBall, ball);
		}
	}

	public Ball applyForces(Ball ball, double time) {
		Vect newVelocityG = applyGravity(ball.getVelocity(), time);
		Vect newVelocityF = applyFriction(newVelocityG, time);
		ball.setVelocity(newVelocityF);
		return ball;
	}

	public Vect applyFriction(Vect velocity, double time) {
		double mu = board.getFrictionConst()[0];
		double mu2 = board.getFrictionConst()[1];
		double changeAmount = 1 - mu * time - mu2 * velocity.length() * time;
		return velocity.times(changeAmount);
	}

	public Vect applyGravity(Vect velocity, double time) {
		double changeAmount = board.getGravityConst() * time;
		Vect change = new Vect(0, changeAmount);
		return velocity.plus(change);
	}

}