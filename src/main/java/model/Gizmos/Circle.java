package model.Gizmos;

import model.Gizmo;
import physics.Vect;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

/**
 * Created by baird on 06/02/2016.
 */
public class Circle extends Gizmo {

	Vect center;
	private String saveInfo;
	public Circle(Vect origin, String name) {
		super(origin, name);
		super.setCircles(Arrays.asList(calculateCircle()));
		super.setColor(Color.GREEN);
		saveInfo = "Circle" +" " +name + " " + (int)origin.getXCoord() + " " + (int)origin.getyCoord();
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
		// TODO Auto-generated method stub

	}


	@Override
	public Vect calculateBound() {
		Vect origin = super.getOrigin();
		Vect bound = new Vect(1, 1);
		return origin.plus(bound);
	}

	public String getSaveInfo(){
		return saveInfo;
	}

	@Override
	public int getRotation() {
		return 0;
	}

	@Override
	public List<Vect> getCoordinates() {
		return Arrays.asList(center);
	}
}
