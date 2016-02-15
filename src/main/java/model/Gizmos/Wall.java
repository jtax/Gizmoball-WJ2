package model.Gizmos;

import model.Component;
import model.Components.Line;
import model.Coordinate;
import model.Gizmo;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

/**
 * Created by baird on 06/02/2016.
 */
public class Wall extends Gizmo {

	Coordinate bound;

	public Wall(Coordinate origin, Coordinate bound, String name) {
		super(origin, name);
		this.bound = bound;
		calculateComponents();

		}

	@Override
	protected void calculateComponents() {
		Component Line = new Line(origin.getX(), origin.getY(), bound.getX(), bound.getY());
		super.setComponents(Arrays.asList(Line));
		super.setColor(Color.blue);
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
