package model;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import physics.Circle;
import physics.LineSegment;
import physics.Vect;

/**
 * Created by baird on 06/02/2016.
 */
public class Ball implements IElement, Absorbable {
	private final static double MAX_VELOCITY = 200, MIN_VELOCITY = 0.01; 
	
	private Circle point;
	private Vect origin;
	private Vect velocity;
	private Vect center;
	private Color color = new Color(0xecf0f1);
	private String name;
	private boolean absorbed;
	private final float diameter = 0.5f;
	private String saveInfo;

	// TODO do balls need names?
	public Ball(String name, double x, double y, double velocityX, double velocityY) {
		center = new Vect(x, y);
		point = new Circle(center, 0.25);
		origin = new Vect(x - .25, y - .25);
		velocity = new Vect(velocityX, velocityY);
		this.name = name;
		absorbed = false;
		saveInfo = "Ball" + " " + name + " " + x + " " + y + " " + velocityX + " " + velocityY;
	}

	public Ball(String name, Vect center, Vect velocity) {
		this(name, center.x(), center.y(), velocity.x(), velocity.y());
	}

	public Vect getCenter() {
		return center;
	}

	public void setCenter(Vect center) {
		this.center = center;
		update();
	}

	public Vect getVelocity() {
		return velocity;
	}

	public void setVelocity(Vect newVelocity) {
		double newX = validateVelocity(velocity.x(), newVelocity.x()),
				newY = validateVelocity(velocity.y(), newVelocity.y());
		
		this.velocity = new Vect(newX, newY);
	}
	
	private double validateVelocity(double currentVelocity, double newVelocity) {
		if (newVelocity > -MIN_VELOCITY || newVelocity < MIN_VELOCITY) {
			if (newVelocity == 0) {
				if (currentVelocity > 0) {
					newVelocity = -MIN_VELOCITY;
				} else {
					newVelocity = MIN_VELOCITY;
				}
			} else if (newVelocity > 0) {
				if (newVelocity < MIN_VELOCITY) {
					newVelocity = MIN_VELOCITY;
				}
			} else if (newVelocity < 0) {
				if (newVelocity > -MIN_VELOCITY)
					newVelocity = -MIN_VELOCITY;
			}
		}

		else if (newVelocity > MAX_VELOCITY)
			newVelocity = MAX_VELOCITY;
		else if (newVelocity < -MAX_VELOCITY)
			newVelocity = -MAX_VELOCITY;

		return newVelocity;
	}

	public Circle getCircle() {
		return point;
	}

	@Override
	public List<LineSegment> getLines() {
		return null;
	}

	@Override
	public List<Circle> getCircles() {
		return Arrays.asList(point);
	}

	@Override
	public List<Vect> getCoordinates() {
		return Arrays.asList(center);
	}

	@Override
	public Vect getOrigin() {
		return origin;
	}

	@Override
	public Vect getBound() {
		double x = origin.x() + diameter;
		double y = origin.y() + diameter;
		return new Vect(x, y);
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public void setColor(Color color) {
		return;
	}

	@Override
	public String getName() {
		return name;
	}

	public void update() {
		point = new Circle(center, 0.25);
		origin = new Vect(center.x() - .25, center.y() - .25);
	}

	/**
	 * Is the ball inside the other element?
	 *
	 * @param otherElement
	 * @return true if the other element is within the absorber's bounds,
	 *         otherwise false
	 */
	public boolean inside(IElement otherElement) {
		Vect ourOrigin = origin, ourBound = getBound(), theirOrigin = otherElement.getOrigin(),
				theirBound = otherElement.getBound();

		boolean topIn = ourOrigin.y() < theirBound.y() && ourOrigin.y() > theirOrigin.y();
		boolean bottomIn = ourBound.y() > theirOrigin.y() && ourBound.y() < theirBound.y();
		boolean leftIn = ourOrigin.x() < theirBound.x() && ourOrigin.x() > theirOrigin.x();
		boolean rightIn = ourBound.x() > theirOrigin.x() && ourBound.x() < theirBound.x();

		// still with me?

		boolean verticallyIn = leftIn && rightIn;
		boolean horizontallyIn = topIn && bottomIn;

		return (verticallyIn && (topIn || bottomIn)) || (horizontallyIn && (leftIn || rightIn));
	}

	public String getSaveInfo() {
		return saveInfo;
	}

	public void moveForTime(double time) {
		if (!absorbed) {
			Vect changeAmount = velocity.times(time);
			setCenter(center.plus(changeAmount));
		}
	}

	@Override
	public int getRotation() {
		return 0;
	}

	public void rotate() {

	}

	@Override
	public void highlight() {

	}

	@Override
	public void move(Vect distance) {

	}

	@Override
	public void handle(Collision collision) {
		Ball ball = collision.getBall();
		ball.moveForTime(collision.getTime());
		ball.setVelocity(collision.getVelocity());
	}

	public void applyForces(double moveTime, double gravity, double[] friction) {
		if (!absorbed) {
			applyGravity(moveTime, gravity);
			applyFriction(moveTime, friction);
		}
	}

	private void applyGravity(double moveTime, double gravity) {
		double changeAmount = gravity * moveTime;
		Vect change = new Vect(0, changeAmount);
		setVelocity(velocity.plus(change));
	}

	private void applyFriction(double moveTime, double[] friction) {
		double mu = friction[0];
		double mu2 = friction[1];
		double changeAmount = 1 - mu * moveTime - mu2 * velocity.length() * moveTime;
		setVelocity(velocity.times(changeAmount));
	}

	@Override
	public boolean isAbsorbed() {
		return absorbed;
	}

	@Override
	public void setAbsorbed() {
		absorbed = true;
	}

	@Override
	public void clearAbsorbed() {
		absorbed = false;
	}

	@Override
	public void release() {
		Vect escapeVelocity = new Vect(0, -50);
		setVelocity(escapeVelocity);
		clearAbsorbed();
	}

	public double getRadius() {
		return diameter / 2;
	}

	public boolean equals(Object other) {
		if (other.getClass() != Ball.class) {
			return false;
		}
		//We know that its a ball
		Ball otherBall = (Ball) other;

		if (!origin.equals(otherBall.getOrigin())) {
			return false;
		}
		if (!velocity.equals(otherBall.getVelocity())) {
			return false;
		}
		if (point != otherBall.getCircle()) {
			return false;
		}
		if(!center.equals(otherBall.getCenter())){
			return false;
		}
		if(!color.equals(otherBall.getColor())){
			return false;
		}
		if(!getCoordinates().equals(otherBall.getCoordinates())){
			return false;
		}
		return true;
	}
}
