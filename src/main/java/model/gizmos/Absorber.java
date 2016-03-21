package model.gizmos;

import model.*;
import physics.LineSegment;
import physics.Vect;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Gizmoball - Absorber
 * Created by Group WJ2 on 06/02/2016.
 * Authors: J Baird, C Bean, N Stannage, U Akhtar, L Sakalauskas
 */
public class Absorber extends Gizmo implements Triggerable {

	private Vect bound;
	private List<Vect> coordinates;
	private List<String> connections = new ArrayList<>();
	private List<String> keyConnects = new ArrayList<>();
	private Ball ourBall;
	private String saveInfo;
	private final String name;
	private int rotation;

	/**
	 * Make a new Absorber.
	 * 
	 * @param origin
	 * @param bound
	 * @param name
	 */
	public Absorber(Vect origin, Vect bound, String name) {
		super(origin, name);
		rotation = 0;
		this.bound = bound;
		this.name = name;
		setBound(bound);
		coordinates = calculateCoordinates();
		super.setCircles(calculateCircles());
		super.setLines(calculateLines());
		super.setColor(new Color(0x8e44ad));
		saveInfo = "Absorber" + " " + name + " " + (int) origin.getXCoord() + " " + (int) origin.getyCoord() + " "
				+ (int) bound.getXCoord() + " " + (int) bound.getyCoord();
	}

	/**
	 * Make a new Absorber.
	 * 
	 * @param originX
	 * @param originY
	 * @param boundX
	 * @param boundY
	 * @param name
	 */
	public Absorber(int originX, int originY, int boundX, int boundY, String name) {
		this(new Vect(originX, originY), new Vect(boundX, boundY), name);
	}

	/**
	 * Calculate this Element's coordinates.
	 * 
	 * @return this Element's coordinates.
	 */
	private List<Vect> calculateCoordinates() {
		Vect topLeft = origin;
		Vect topRight = new Vect(bound.x(), origin.y());
		Vect bottomRight = bound;
		Vect bottomLeft = new Vect(origin.x(), bound.y());
		return Arrays.asList(topLeft, topRight, bottomRight, bottomLeft);
	}

	/**
	 * Calculate this Element's circles.
	 * 
	 * @return this Element's circles.
	 */
	private List<physics.Circle> calculateCircles() {
		List<physics.Circle> calcCircles = new ArrayList<>();
		for (Vect coord : coordinates) {
			physics.Circle circle = new physics.Circle(coord, 0);
			calcCircles.add(circle);
		}
		return calcCircles;
	}

	/**
	 * Calculate this Element's lines.
	 * 
	 * @return this Element's lines.
	 */
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
		Vect centerPoint = getCenterPoint();
		rotation = (rotation + 1) % 4;
		//setSaveInfo();
		List<Vect> oldCoordinates = new LinkedList<>();

		for (int i = 0; i < coordinates.size(); i++) {
			oldCoordinates.add(coordinates.get(i));
			coordinates.set(i, rotationMatrix(coordinates.get(i), centerPoint, 90));
		}

		if(outOfBoard()){
			coordinates = oldCoordinates;
		}

		super.setCircles(calculateCircles());
		super.setLines(calculateLines());
	}

	
	/**
	 * Test whether this is out of the board.
	 * 
	 * @return true if out of board, else false
	 */
	private boolean outOfBoard(){

		for(int i = 0; i < coordinates.size(); i++){
			double x = coordinates.get(i).x();
			double y = coordinates.get(i).y();

			if((x < 0 || x >= 20) || (y < 0 || y >= 20)){
				return true;
			}
		}
		return false;
	}

	/**
	 * this Gizmo's center point.
	 * @return
	 */
	public Vect getCenterPoint() {
		double width = bound.x() - origin.x();
		double height = bound.y() - origin.y();
		return origin.plus(new Vect(width / 2, height / 2));
	}

	@Override
	public Vect calculateBound() {
		return bound;
	}

	/**
	 * Absorb the ball.
	 * 
	 * @param ball
	 */
	public void absorb(Ball ball) {
		ourBall = ball;
		ourBall.absorb();
		positionBall();
	}

	/**
	 * Release our ball.
	 */
	public void releaseOurBall() {
		if (weHaveABall()) {
			ourBall.release();
			ourBall = null;
		}
	}

	/**
	 * Position the ball in the bottom-right corner of the Absorber.
	 */
	public void positionBall() {
		if (weHaveABall()) {
			Vect ourBound = getBound();
			double ballRadius = ourBall.getRadius();

			double ballX = ourBound.x() - ballRadius;
			double ballY = ourBound.y() - ballRadius;

			ourBall.setCenter(new Vect(ballX, ballY));
		}
	}

	/**
	 * Get this gizmo's save info.
	 */
	public String getSaveInfo() {
		return saveInfo;
	}

	@Override
	public int getRotation() {
		return rotation;
	}

	/**
	 * 
	 * @return true if we have absorbed a ball, otherwise false.
	 */
	public boolean weHaveABall() {
		return ourBall != null;
	}

	@Override
	public List<Vect> getCoordinates() {
		return coordinates;
	}

	@Override
	public void subHandle(Collision c) {
		Ball ball = c.getBall();
		absorb(ball);
	}

	@Override
	public void move(Vect distance) {
		super.origin = super.origin.plus(distance);
		this.bound = bound.plus(distance);
		super.bound = bound;
		coordinates = calculateCoordinates();
		super.setCircles(calculateCircles());
		super.setLines(calculateLines());
		saveInfo = "Absorber" + " " + name + " " + (int) origin.getXCoord() + " " + (int) origin.getyCoord() + " "
				+ (int) bound.getXCoord() + " " + (int) bound.getyCoord();
		positionBall();
	}

	@Override
	public boolean equals(Object other) {
		if (other.getClass() != Absorber.class) {
			return false;
		}
		//We know that its a absorber
		Absorber otherAbsorber = (Absorber) other;

		if (!origin.equals(otherAbsorber.getOrigin())) {
			return false;
		}
		if (!bound.equals(otherAbsorber.getBound())) {
			return false;
		}
		if (rotation != otherAbsorber.rotation) {
			return false;
		}
		return coordinates.equals(otherAbsorber.coordinates);
	}

	@Override
	public void gizmoConnect(IElement secondElement){
		this.addTriggerable((Triggerable) secondElement);
		connections.add("Connect " +this.getName()+ " "+ secondElement.getName());
	}

	@Override
	public List<String> getConnections() {
		return connections;
	}

	@Override
	public void addKeyConnect(int keycode){
		this.addKeyPressTrigger(keycode);
		keyConnects.add("KeyConnect Key "+ keycode+ " change "+ this.getName());
	}

	@Override
	public List<String> returnKeyConnects() {
		return keyConnects;
	}

	/**
	 * Remove key connect from this gizmo.
	 */
	public void removeKeyConnects(){
		keyConnects.clear();
		this.clearKeyTriggers();
	}

	@Override
	public void clearConnections() {
		connections.clear();
		this.clearTriggerable();
	}

	@Override
	public void clearKeyConnections() {
		keyConnects.clear();
		this.clearKeyPressTrigger();
	}

	@Override
	public void removeConnection(IElement element) {
		for (String connect : connections) {
			if (connect.contains(element.getName())) {
				connections.remove(connect);
			}
		}
	}
}
