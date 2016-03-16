package junit.model.gizmos;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import model.gizmos.Square;
import physics.Circle;
import physics.LineSegment;
import physics.Vect;

/**
 * Created by baird on 16/02/2016.
 */
public class SquareTest {
	Square square;

	@Before
	public void setUp() throws Exception {
		square = new Square(5, 5, "Test");
	}

	@Test
	public void testSquare(){
		//See spreadsheet Test 84
		assert (square.getOrigin().equals(new Vect(5, 5)));
		assert (square.getName().equals("Test"));
		assert (square.getRotation() == 0);

	}

	@Test
	public void getCenterTest() {
		//See spreadsheet Test 87
		Vect expected = new Vect(5.5, 5.5);
		assert (square.getCenterPoint().equals(expected));
	}

	@Test
	public void calcLinesTest() {
		//See spreadsheet Test 88
		Vect expTL = new Vect(5, 5);
		Vect expBR = new Vect(6, 6);
		Vect expBL = new Vect(5, 6);
		Vect expTR = new Vect(6, 5);
		List<LineSegment> expected = Arrays.asList(new LineSegment(expBL, expTL), new LineSegment(expBR, expBL), new LineSegment(expTR, expBR),new LineSegment(expTL,expTR));
		assert compareLists(square.getLines(), expected);
	}

	@Test
	public void move() {
		//See spreadsheet Test 90
		square.move(new Vect(1, 1));
		assert square.getOrigin().equals(new Vect(6, 6));
	}


	@Test
	public void calculateCirclesTest() {
		//See spreadsheet Test 86
		List<Vect> expected = Arrays.asList(new Vect(5, 5), new Vect(5, 6), new Vect(6, 5),new Vect(6, 6));
		List<Vect> actual = new ArrayList<>();
		for (Circle circle : square.getCircles()) {
			actual.add(circle.getCenter());
		}
		assert (compareLists(actual, expected));
	}



	@Test
	public void testCalculateBound() throws Exception {
		//See spreadsheet Test 89
		Assert.assertEquals(square.getBound(), new Vect(6, 6));
	}

	@Test
	public void testCoordinates() throws Exception {
		//See spreadsheet Test 93
		List<LineSegment> actLines = square.getLines();
		LineSegment top = new LineSegment(5, 5, 6, 5);
		LineSegment right = new LineSegment(6, 5, 6, 6);
		LineSegment bottom = new LineSegment(6, 6, 5, 6);
		LineSegment left = new LineSegment(5, 6, 5, 5);
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
	public void saveString() {
		//See spreadsheet Test 91
		String expected = "Square Test 5 5";
		assert(square.getSaveInfo().equals(expected));
	}

	@Test
	public void rotate1() {
		//See spreadsheet Test 149
		square.rotate();
		List<Vect> expected = Arrays.asList(new Vect(5, 5), new Vect(6, 5), new Vect(6, 6),new Vect(5, 6));
		assert (compareLists(square.getCoordinates(), expected));
	}

	@Test
	public void rotate2() {
		//See spreadsheet Test 150
		square.rotate();
		Vect expectedOrigin = new Vect(5, 5);
		Vect expectedBound = new Vect(6, 6);
		assert square.getOrigin().equals(expectedOrigin);
		assert square.getBound().equals(expectedBound);
	}

	@Test
	public void rotate3() {
		//See spreadsheet Test 151
		square.rotate();
		List<Vect> expected = Arrays.asList(new Vect(5, 5), new Vect(6, 5), new Vect(6, 6),new Vect(5, 6));
		List<Vect> actual = new ArrayList<>();
		for (Circle circle : square.getCircles()) {
			actual.add(circle.getCenter());
		}
		assert compareLists(expected, actual);
	}

	@Test
	public void rotate4Lines() {
		//See spreadsheet Test 152
		square.rotate();
		Vect expTL = new Vect(6, 5);
		Vect expTR = new Vect(6, 6);
		Vect expBR = new Vect(5, 6);
		Vect expBL = new Vect(5, 5);
		LineSegment top = new LineSegment(expTL, expTR);
		LineSegment right = new LineSegment(expTR, expBR);
		LineSegment bot = new LineSegment(expBR,expBL );
		LineSegment left = new LineSegment(expBL, expTL);
		List<LineSegment> expectedLines = Arrays.asList(top, right, left,bot);
		assert compareLists(square.getLines(), expectedLines);
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