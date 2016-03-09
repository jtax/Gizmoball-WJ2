package model;

import physics.Circle;
import physics.LineSegment;
import physics.Vect;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by baird on 06/02/2016.
 */
abstract public class Gizmo implements IElement {

	protected Vect origin, bound;
	private Gizmo trigger;
	private Color color;
	private Color backupColor;
	private List<LineSegment> lines;
	private List<Circle> circles;
	private int reflection;
	private String name;
	private int keyPressTrigger;

	protected Gizmo(Vect origin, String name) {
		lines = new ArrayList<>();
		circles = new ArrayList<>();
		this.origin = origin;
		color = Color.RED;
		this.name = name;

		// TODO: set the bounds correctly according to which gizmo it is
		// bound = new Vect(origin.x() + 1, origin.y() + 1);
		bound = calculateBound();
	}

	public abstract void move(Vect distance);


	@Override
	public Vect getOrigin() {
		return origin;
	}

	@Override
	public Vect getBound() {
		return bound;
	}

	public Gizmo getGizmoTrigger() {
		return trigger;
	}

	public String getName() {
		return name;
	}

	public void addGizmoTrigger(Gizmo trigger) {
		this.trigger = trigger;
	}

	public void addKeyPressTrigger(int keyCode) {

		keyPressTrigger = keyCode;
	}

	public int getKeyPressTrigger() {
		return keyPressTrigger;
	}

	public void trigger() {

		color = Color.red;
	}

	public void onCollision() {
		if (trigger instanceof Gizmo)
			trigger.trigger();
	}

	@Override
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
		this.backupColor = color;
	}

	public abstract void rotate();

	public int getReflectionCoefficient() {
		return reflection;
	}

	public abstract Vect calculateBound();

	@Override
	public List<LineSegment> getLines() {
		return lines;
	}

	@Override
	public List<Circle> getCircles() {
		return circles;
	}

	protected void setLines(List<LineSegment> lines) {
		this.lines = lines;
	}

	protected void setCircles(List<Circle> circles) {
		this.circles = circles;
	}

	protected void setBound(Vect bound) {
		this.bound = bound;
	}

	public void handle(Collision collision) {
		// trigger the attached gizmo
		onCollision();

		Ball ball = collision.getBall();
		ball.moveForTime(collision.getTime());
		ball.setVelocity(collision.getVelocity());

		setColor(Color.GREEN);
	}

	public void highlight() {
		if (color != backupColor) {
			color = backupColor;
		} else {
			color = Color.CYAN;
		}
	}



}
