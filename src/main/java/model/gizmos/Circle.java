package model.gizmos;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
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

	public Circle(Vect origin, String name) {
		super(origin, name);
		rotation = 0;
		super.setCircles(Collections.singletonList(calculateCircle()));
		super.setColor(new Color(0x27ae60));
		saveInfo = "Circle" + " " + name + " " + (int) origin.getXCoord() + " " + (int) origin.getyCoord();
		this.name = name;
	}

	public Circle(int x, int y, String name) {
		this(new Vect(x, y), name);
	}

	private physics.Circle calculateCircle() {
		return new physics.Circle(super.getOrigin().plus(new Vect(0.5, 0.5)), 0.5);
	}

	private void setCenter() {
		center = super.getOrigin().plus(new Vect(0.5, 0.5));
	}

	@Override
	public void rotate() {
		rotation = (rotation + 1) % 4;
		// TODO Auto-generated method stub

	}

	@Override
	public Vect calculateBound() {
		Vect origin = super.getOrigin();
		Vect bound = new Vect(1, 1);
		return origin.plus(bound);
	}

	public String getSaveInfo() {
		return saveInfo;
	}

	@Override
	public int getRotation() {
		return rotation;
	}

	@Override
	public List<Vect> getCoordinates() {
		return Collections.singletonList(center);
	}

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


	public void gizmoConnect(IElement secondElement){
		this.addTriggerable((Triggerable) secondElement);
		connections.add("Connect " +this.getName()+ " "+ secondElement.getName());
	}

	public List<String> getConnections() {
		return connections;
	}

	public void addKeyConnect(int keycode){
		this.addKeyPressTrigger(keycode);
		keyConnects.add("KeyConnect Key "+ keycode+ " change "+ this.getName());
	}

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
}
