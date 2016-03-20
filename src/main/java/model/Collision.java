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

	public Collision(Vect velocity, double time, CollisionHandler handler, Ball ball) {
		this.velocity = velocity;
		this.time = time;
		this.handler = handler;
		this.ball = ball;
	}

	public Collision(Vect velocity, double time) {
		this.velocity = velocity;
		this.time = time;
	}

	public Collision(double x, double y, double time, CollisionHandler handler, Ball ball) {
		this(new Vect(x, y), time, handler, ball);
	}

	public Collision(double x, double y, double time) {
		this(new Vect(x, y), time);
	}

	public Vect getVelocity() {
		return velocity;
	}

	public double getTime() {
		return time;
	}

	public CollisionHandler getHandler() {
		return handler;
	}

	public Ball getBall() {
		return ball;
	}

}
