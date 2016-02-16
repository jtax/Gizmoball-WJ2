package model.Gizmos;

import model.Gizmo;
import physics.Angle;
import physics.Vect;

import java.util.Arrays;

/**
 * Created by baird on 06/02/2016.
 */
public class Flipper extends Gizmo {

	public Flipper(Vect origin, String name) {

		super(origin,name);
		calculateComponents();
	}

	@Override
	protected void calculateComponents() {
		Vect origin = super.getOrigin();
		double x = origin.x();
		double y = origin.y();
		Component top = new Line(x,y, x+1,y);
		Component right = new Line(x+1,y, x+1,y-2);
		Component bottom = new Line(x,y-2, x+1,y-2);
		Component left = new Line(x,y, x,y-2);
		super.setComponents( Arrays.asList(top,right,bottom,left));
	}


	@Override
	public void rotate() {
		bound.rotateBy(new Angle(90));
	}

	public void rotateBack() {
		bound.rotateBy(new Angle(-90));
	}

	@Override
	public Vect calculateBound() {
		Vect origin = super.getOrigin();
		Vect bound = new Vect(2, -2);
		return origin.plus(bound);
	}
}