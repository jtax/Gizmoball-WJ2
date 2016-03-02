package junit.model.gizmos;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.gizmos.Square;
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
	public void testRotate() throws Exception {

	}

	@Test
	public void testCalculateBound() throws Exception {
		assertEquals(square.getBound(), new Vect(6, 6));
	}

	@Test
	public void testCoordinates() throws Exception {
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
}