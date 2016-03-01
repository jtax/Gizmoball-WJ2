package model;

import physics.Vect;

/**
 * Created by baird on 14/02/2016.
 */
public class Collision {
    private double time;
    private Vect velocity;
    private CollisionHandler handler;
    private Ball ball;

    public Collision(Vect velocity, double time, CollisionHandler handler,  Ball ball) {
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
