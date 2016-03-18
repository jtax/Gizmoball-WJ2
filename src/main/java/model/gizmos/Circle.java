package model.gizmos;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import model.Gizmo;
import model.IElement;
import model.Triggerable;
import physics.Vect;

/**
 * Created by baird on 06/02/2016.
 */
public class Circle extends Gizmo {

	Vect center;
	String name;
	private String saveInfo;
	private int rotation;

	public Circle(Vect origin, String name) {
		super(origin, name);
		rotation = 0;
		super.setCircles(Arrays.asList(calculateCircle()));
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
		return Arrays.asList(center);
	}

	public void move(Vect distance) {
		super.origin = super.origin.plus(distance);
		super.bound = super.bound.plus(distance);
		super.setCircles(Arrays.asList(calculateCircle()));
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
		if(!getCoordinates().equals(otherCircle.getCoordinates())){
			return false;
		}

		return true;
	}


	public void gizmoConnect( IElement secondElement){
		//if(!firstElement.equals(null)||!secondElement.equals(null)) {
			this.addTriggerable((Triggerable) secondElement);
		//}
	}
}
