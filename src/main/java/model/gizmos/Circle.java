package model.gizmos;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Gizmo;
import model.IElement;
import model.Triggerable;
import physics.Vect;

/**
 * Gizmoball - Circle
 * Created by Group WJ2 on 06/02/2016.
 * Authors: J Baird, C Bean, N Stannage, U Akhtar, L Sakalauskas
 */
public class Circle extends Gizmo {
	private List<String> connections = new ArrayList<>();
	private List<String> keyConnects = new ArrayList<>();
	private Vect center;
	private final String name;
	private String saveInfo;
	private int rotation;

	/**
	 * Circle Constructor, initialises the origin, name, rotation, circles color save info
	 *
	 * @param origin Coordinates of top left most position of the bounding box
	 * @param name   the unique name of the gizmo
	 */
	public Circle(Vect origin, String name) {
		super(origin, name);
		rotation = 0;
		super.setCircles(Collections.singletonList(calculateCircle()));
		super.setColor(new Color(0x27ae60));
		saveInfo = "Circle" + " " + name + " " + (int) origin.getXCoord() + " " + (int) origin.getyCoord();
		this.name = name;
	}

	/**
	 * Same a circle but takes in origin by x y rather than coord
	 * @param x left most position
	 * @param y top most position
	 * @param name the unqiue names of the gizmo
	 */
	public Circle(int x, int y, String name) {
		this(new Vect(x, y), name);
	}

	/**
	 * Intialises the circle creating a circle for every coordinate
	 * @return List of Circle
	 */
	private physics.Circle calculateCircle() {
		return new physics.Circle(super.getOrigin().plus(new Vect(0.5, 0.5)), 0.5);
	}

	/**
	 * Increment Rotation but does nothing (Circle has rotational symmetry)
	 */
	@Override
	public void rotate() {
		rotation = (rotation + 1) % 4;
	}

	/**
	 * Calculates the bottom left most coordinates of the bounding box
	 * @return bound
	 */
	@Override
	public Vect calculateBound() {
		Vect origin = super.getOrigin();
		Vect bound = new Vect(1, 1);
		return origin.plus(bound);
	}

	/**
	 * Gets the string representation of the object
	 * @return saveinfo string
	 */
	@Override
	public String getSaveInfo() {
		return saveInfo;
	}

	/**
	 * Gets the rotation value
	 * @return rotation
	 */
	@Override
	public int getRotation() {
		return rotation;
	}

	/**
	 * Gets the coordinates of the shape (In this case just the center)
	 * @return List of Coords
	 */
	@Override
	public List<Vect> getCoordinates() {
		return Collections.singletonList(center);
	}

	/**
	 * Moves a gizmo by a distance, updating origin bound and circles
	 * @param distance distance the gizmo should move
	 */
	@Override
	public void move(Vect distance) {
		super.origin = super.origin.plus(distance);
		super.bound = super.bound.plus(distance);
		super.setCircles(Collections.singletonList(calculateCircle()));
		saveInfo = "Circle" + " " + name + " " + (int) origin.getXCoord() + " " + (int) origin.getyCoord();
	}

	@Override
	public boolean equals(Object other) {
		if (other.getClass() != Circle.class) {
			return false;
		}
		//We know that its a circle
		Circle otherCircle = (Circle) other;

		if (!origin.equals(otherCircle.getOrigin())) {
			return false;
		}
		if (!bound.equals(otherCircle.getBound())) {
			return false;
		}
		if (rotation != otherCircle.getRotation()) {
			return false;
		}
		return getCoordinates().equals(otherCircle.getCoordinates());

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
	public List<String> returnKeyConnects(){
		return keyConnects;
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
		for (int i = 0; i < connections.size(); i++) {
			if (connections.get(i).contains(element.getName())) {
				connections.remove(i);
			}
		}
	}
}
