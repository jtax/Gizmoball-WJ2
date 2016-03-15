package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import physics.Circle;
import physics.LineSegment;
import physics.Vect;

/**
 * Created by baird on 06/02/2016.
 */
abstract public class Gizmo implements IElement, Triggerable {

	protected Vect origin, bound;
	private Collection<Triggerable> triggerables;
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
		
		triggerables = new HashSet<>();
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

	public Collection<Triggerable> getTriggerables() {
		return triggerables;
	}

	public String getName() {
		return name;
	}

	public void addTriggerable(Triggerable t) {
		triggerables.add(t);
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

	/** trigger the attached tirggerables */
	protected void onCollision() {
		for (Triggerable t : triggerables)
			t.trigger();
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

	/**
	 * Subclasses override subHandle() to change behaviour.
	 */
	/*
	 * This is to guarantee that onCollision() is called on every collision.
	 */
	public final void handle(Collision c) {
		onCollision();
		subHandle(c);
	}
	
	protected void subHandle(Collision c) {
		Ball ball = c.getBall();
		ball.moveForTime(c.getTime());
		ball.setVelocity(c.getVelocity());

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
