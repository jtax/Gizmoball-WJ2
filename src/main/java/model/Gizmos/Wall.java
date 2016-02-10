package model.Gizmos;

import model.Component;
import model.Coordinate;
import model.Gizmo;

import java.util.List;

/**
 * Created by baird on 06/02/2016.
 */
public class Wall extends Gizmo {

	public Wall(Coordinate origin, String name) {
		super(origin, name);
		calculateComponents();
		}

	@Override
	protected void calculateComponents() {

	}

	@Override
	public void rotate() {
		// TODO Auto-generated method stub

	}

	@Override
	public Coordinate calculateBound() {
		return null;
	}

}