package model;

import physics.Vect;

/**
 * Gizmoball - Collision
 * Created by Group WJ2 on 14/02/2016.
 * Authors: J Baird, C Bean, N Stannage, U Akhtar, L Sakalauskas
 */
public class Collision {
	private final double time;
	private final Vect velocity;
	private CollisionHandler handler;
	private Ball ball;

	/**
	 * Make a new collision with all the things.
	 * 
	 * @param velocity
	 * @param time
	 * @param handler
	 * @param ball
	 */
	public Collision(Vect velocity, double time, CollisionHandler handler, Ball ball) {
		this.velocity = velocity;
		this.time = time;
		this.handler = handler;
		this.ball = ball;
	}

	/**
	 * Make a new collision without handler or ball.
	 * 
	 * @param velocity
	 * @param time
	 */
	public Collision(Vect velocity, double time) {
		this.velocity = velocity;
		this.time = time;
	}

	/**
	 * Make a new collision with all the things.
	 * 
	 * @param x
	 * @param y
	 * @param time
	 * @param handler
	 * @param ball
	 */
	public Collision(double x, double y, double time, CollisionHandler handler, Ball ball) {
		this(new Vect(x, y), time, handler, ball);
	}

	/**
	 * Make a new collision without handler or ball.
	 * 
	 * @param x
	 * @param y
	 * @param time
	 */
	public Collision(double x, double y, double time) {
		this(new Vect(x, y), time);
	}

	/**
	 * 
	 * @return the new velocity of the ball
	 */
	public Vect getVelocity() {
		return velocity;
	}

	/**
	 * @return the time until the collision
	 */
	public double getTime() {
		return time;
	}

	/**
	 * @return the collision's handler
	 */
	public CollisionHandler getHandler() {
		return handler;
	}

	/**
	 * @return the ball that's going to collide
	 */
	public Ball getBall() {
		return ball;
	}

}
