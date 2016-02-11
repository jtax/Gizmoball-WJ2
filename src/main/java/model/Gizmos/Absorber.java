package model.Gizmos;

import model.Component;
import model.Components.Line;
import model.Coordinate;
import model.Gizmo;

import java.util.Arrays;
import java.util.List;

/**
 * Created by baird on 06/02/2016.
 */
public class Absorber  extends Gizmo {

	private Coordinate bound;

	public Absorber(Coordinate origin, Coordinate bound, String name) {
		super(origin,name);
		this.bound = bound;
		calculateComponents();
	}

	@Override
	protected void calculateComponents() {
		Coordinate origin = super.getOrigin();
		double originx = origin.getX();
		double originy = origin.getY();
		double boundx = bound.getX();
		double boundy = bound.getY();
		Component top = new Line(originx,originy, boundx,originy);
		Component right = new Line(boundx,originy, boundx,boundy);
		Component bottom = new Line(originx,boundy, boundx,boundy);
		Component left = new Line(originx,originy, originx,boundy);
		super.setComponents(Arrays.asList(top,right,bottom,left));
	}

	@Override
	public void rotate() {
		// TODO Auto-generated method stub

	}

	@Override
	public Coordinate calculateBound() {
		return bound;
	}
}
