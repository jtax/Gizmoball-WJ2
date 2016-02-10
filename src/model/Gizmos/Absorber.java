package model.Gizmos;

import model.Component;
import model.Coordinate;
import model.Gizmo;

import java.util.List;

/**
 * Created by baird on 06/02/2016.
 */
public class Absorber  extends Gizmo {

	public Absorber(Coordinate origin) {
		super(origin);
	}

	@Override
	protected List<Component> calculateComponents() {
		return null;
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
