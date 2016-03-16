package junit.model.gizmos;

import model.Gizmo;
import model.gizmos.Wall;
import org.junit.Before;
import org.junit.Test;
import physics.Circle;
import physics.LineSegment;
import physics.Vect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by baird on 16/02/2016.
 */
public class WallTest {
	Wall wall;

	@Before
	public void setUp() throws Exception {
		Vect topLeft = new Vect(0, 0);
		Vect topRight = new Vect(20, 0);
		Vect bottomLeft = new Vect(0, 20);
		Vect bottomRight = new Vect(20, 20);
		wall = new Wall(topLeft, bottomRight, "Test");
	}

	@Test
	public void testCoordinates() throws Exception {
		//See spreadsheet Test 93
		List<LineSegment> actLines = wall.getLines();
		LineSegment top = new LineSegment(0, 0, 20, 0);
		LineSegment right = new LineSegment(20, 0, 20, 20);
		LineSegment bottom = new LineSegment(20, 20, 0, 20);
		LineSegment left = new LineSegment(20, 20, 0, 0);
		List<LineSegment> expLines = Arrays.asList(top, right, bottom, left);
		assert actLines.size() == expLines.size();
		assert checkCoordinates(actLines, expLines);
	}

	private Boolean checkCoordinates(List<LineSegment> actLines, List<LineSegment> expLines) {
		Boolean status = true;
		for (int i = 0; i < expLines.size() - 1; i++) {
			status = status && actLines.contains(expLines.get(i));
		}
		return status;
	}

	@Test
	public void calculateCirclesTest() {
		//See spreadsheet Test 86
		List<Vect> expected = Arrays.asList(new Vect(0, 0), new Vect(20, 0), new Vect(0, 20),new Vect(20, 20));
		List<Vect> actual = new ArrayList<>();
		for (Circle circle : wall.getCircles()) {
			actual.add(circle.getCenter());
		}
		assert (compareLists(actual, expected));
	}

	@Test
	public void calculateLines(){
		Vect topLeft = new Vect(0, 0);
		Vect topRight = new Vect(20, 0);
		Vect bottomLeft = new Vect(0, 20);
		Vect bottomRight = new Vect(20, 20);
		List<LineSegment> expected = Arrays.asList(new LineSegment(topLeft, topRight), new LineSegment(topRight, bottomRight), new LineSegment(bottomRight, bottomLeft),new LineSegment(bottomLeft,topLeft));
		assert compareLists(expected, wall.getLines());
	}

	@Test
	public void testCalculateBound() throws Exception {
		Vect bottomRight = new Vect(20, 20);
		assertTrue(wall.getBound().equals(bottomRight));
	}

	@Test
	public void testMove() {
		wall.move(new Vect(1, 1));
		Vect topLeft = new Vect(0, 0);
		Vect bottomRight = new Vect(20, 20);
		assertTrue(wall.getOrigin().equals(topLeft)&&wall.getBound().equals(bottomRight));
	}

	@Test
	public void testRotate() throws Exception {
		Vect topLeft = new Vect(0, 0);
		Vect bottomRight = new Vect(20, 20);
		wall.rotate();
		assertTrue(wall.getOrigin().equals(topLeft)&&wall.getBound().equals(bottomRight));
	}

	private boolean compareLists(List actual, List expected) {
		if (actual.size() == expected.size()) {
			for (Object obj : actual) {
				if (!expected.contains(obj)) {
					return false;
				}
			}
		}
		return true;
	}


}