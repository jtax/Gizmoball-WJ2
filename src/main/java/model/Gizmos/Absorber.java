package model.Gizmos;

import java.awt.Color;

import model.Ball;
import physics.Vect;

/**
 * Created by baird on 06/02/2016.
 */
public class Absorber extends AbstractRectangularGizmo {

	private Ball ourBall;

	public Absorber(Vect origin, Vect bound, String name) {
		super(origin, calculateSize(origin, bound), name);
		super.setColor(Color.MAGENTA);
	}

	public Absorber(int originX, int originY, int boundX, int boundY, String name) {
		this(new Vect(originX, originY), new Vect(boundX, boundY), name);
	}

	public void absorb(Ball ball) {
		ourBall = ball;
		positionOurBall();
		// TODO: release on trigger, not automatically
		release();
	}
	
	public void release() {
		releaseOurBall();
	}
	
	/**
	 * Does the absorber have your ball?
	 * 
	 * @param yourBall
	 *            your ball
	 * @return true if the absorber has your ball, otherwise false
	 */
	public boolean hasBall(Ball yourBall) {
		return yourBall == ourBall;
	}

	@Override
	public void rotate() {
		// TODO Auto-generated method stub
	}

	private void positionOurBall() {
		if (weHaveABall()) {
			Vect bound = getBound();
			double ballX = bound.x() - .25, ballY = bound.y() - .25;
			ourBall.setCenter(ballX, ballY);
		}
	}

	private void releaseOurBall() {
		if (weHaveABall()) {
			double xVelocity = 0, yVelocity = -50;
			Vect velocity = new Vect(xVelocity, yVelocity);
			ourBall.setVelocity(velocity);
			
			ourBall = null;
		}
	}
	
	private boolean weHaveABall() {
		return ourBall != null;
	}

	private static Vect calculateSize(Vect origin, Vect bound) {
		return bound.minus(origin);
	}
}
