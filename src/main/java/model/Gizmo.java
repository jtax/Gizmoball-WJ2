package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import physics.Circle;
import physics.LineSegment;
import physics.Vect;

/**
 * Gizmoball - Gizmo
 * Created by Group WJ2 on 06/02/2016.
 * Authors: J Baird, C Bean, N Stannage, U Akhtar, L Sakalauskas
 */
abstract public class Gizmo implements IElement, Triggerable {

	protected Vect origin, bound;
	private Collection<Triggerable> triggerables;
	private Color color;
	private Color backupColor;
	private List<LineSegment> lines;
	private List<Circle> circles;
	private final String name;
	private int keyPressTrigger;

	protected Gizmo(Vect origin, String name) {
		lines = new ArrayList<>();
		circles = new ArrayList<>();
		this.origin = origin;
		color = Color.RED;
		this.name = name;
		bound = calculateBound();
		
		triggerables = new HashSet<>();
	}

	/**
	 * Moves a gizmo by a given distance
	 *
	 * @param distance amount to move gizmo by
	 */
	public abstract void move(Vect distance);


	/**
	 * Gets the top left most coordinate of the bound
	 * @return Coordinate of Origin Point
	 */
	@Override
	public Vect getOrigin() {
		return origin;
	}

	/**
	 * Gets the bottom right most coordinate of the bound
	 * @return Coordinate of the Bound point
	 */
	@Override
	public Vect getBound() {
		return bound;
	}

	/**
	 * Gets a list of the other Gizmo's this triggers
	 * @return List of Trigerable Gizmos
	 */
	public Collection<Triggerable> getTriggerables() {
		return triggerables;
	}

	/**
	 * Gets the name of the gizmo
	 * @return the name in the format of a string
	 */
	public String getName() {
		return name;
	}

	/**
	 * Adds a Gizmo to the Triggerables list
	 * @param t a Triaggerable Gizmo
	 */
	protected void addTriggerable(Triggerable t) {
		triggerables.add(t);
	}

	/**
	 * Empties the Triggerables List
	 */
	protected void clearTriggerable() {
		triggerables.clear();
	}

	/**
	 * Sets the keyPressTrigger to the param
	 * @param keyCode int representation of a key
	 */
	protected void addKeyPressTrigger(int keyCode) {
		keyPressTrigger = keyCode;
	}

	/**
	 * Clears keyPress by setting it to -1
	 */
	protected void clearKeyPressTrigger() {
		keyPressTrigger = -1;
	}

	/**
	 * Gets the keypress value
	 * @return key represented as int
	 */
	public int getKeyPressTrigger() {
		return keyPressTrigger;
	}



	public void trigger() {
		colourChange();
	}

	public void colourChange() {
		Random rand = new Random();
		float r = rand.nextFloat();
		float g = rand.nextFloat();
		float b = rand.nextFloat();
		Color randomColor = new Color(r, g, b);

		color = randomColor;
	}

	/**
	 * trigger the attached tirggerables
	 *
	 */
	private void onCollision() {
		for (Triggerable t : triggerables)
			t.trigger();
	}

	@Override
	public Color getColor() {
		return color;
	}

	/**
	 * Sets the intial color of the gizmo.
	 * @param color
	 */
	public void setColor(Color color) {
		this.color = color;
		this.backupColor = color;
	}

	/**
	 * Rotates a gizmo
	 */
	public abstract void rotate();

	/**
	 * Gets the bound
	 * @return
	 */
	public abstract Vect calculateBound();


	@Override
	public List<LineSegment> getLines() {
		return lines;
	}

	@Override
	public List<Circle> getCircles() {
		return circles;
	}

	/**
	 * Sets the lines
	 * @param lines
	 */
	protected void setLines(List<LineSegment> lines) {
		this.lines = lines;
	}

	/**
	 * Sets the circles
	 * @param circles
	 */
	protected void setCircles(List<Circle> circles) {
		this.circles = circles;
	}

	/**
	 * Sets the bound
	 * @param bound
	 */
	protected void setBound(Vect bound) {
		this.bound = bound;
	}

	/**
	 * Determines what to do on collision
	 * @param c
	 */
	public final void handle(Collision c) {
		subHandle(c);
		onCollision();
	}

	/**
	 * Determines what to do to a ball on collision. Subclasses can override this
	 * @param c collision
	 */
	protected void subHandle(Collision c) {
		Ball ball = c.getBall();
		ball.moveForTime(c.getTime());
		ball.setVelocity(c.getVelocity());

		setColor(Color.GREEN);
	}

	@Override
	public void highlight(boolean toggle) {
		if (toggle) {
			color = Color.cyan;
		} else {
			color = backupColor;
		}
	}

	/**
	 * Resets Trigger Value
	 */
	public void clearKeyTriggers(){
		keyPressTrigger = 0;
	}
	
	/**
	 * Rotate a coordinate around the center point by angle.
	 * 
	 * @param coordinate
	 * @param center
	 * @param angle
	 * @return
	 */
	public Vect rotationMatrix(Vect coordinate, Vect center, double angle) {
		double angleR = Math.toRadians(angle);
		Vect coord = coordinate.minus(center);
		double newX = coord.x() * Math.cos(angleR) - coord.y() * Math.sin(angleR);
		double newY = coord.x() * Math.sin(angleR) + coord.y() * Math.cos(angleR);

		return new Vect(newX, newY).plus(center);
	}
	
	/**
	 * Rotate a coordinate around the center point by 90°.
	 * 
	 * @param coordinate
	 * @param center
	 * @return
	 */
	protected Vect rotationMatrix(Vect coordinate, Vect center) {
		double angleR = Math.toRadians((double) 90);
		Vect coord = coordinate.minus(center);
		double newX = coord.x() * Math.cos(angleR) - coord.y() * Math.sin(angleR);
		double newY = coord.x() * Math.sin(angleR) + coord.y() * Math.cos(angleR);
		return new Vect(newX, newY).plus(center);
	}
}
