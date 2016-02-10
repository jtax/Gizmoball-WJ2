package junit.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import model.Coordinate;

@SuppressWarnings("deprecation")
public class CoordinateTest {

	private Coordinate c;
	private double expectedX = 1, expectedY = 1;
	private final double delta = 0;

	@Before
	public void setUp() throws Exception {
		c = new Coordinate(expectedX, expectedY);
	}
	
	@Test
	public void testCoordinate() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetX() {
		double expected = expectedX, actual = c.getX();

		assertEquals(expected, actual, delta);
	}

	@Test
	public void testGetY() {
		double expected = expectedY, actual = c.getY();

		assertEquals(expected, actual, delta);
	}

	@Test
	public void testSetX() {
		double expected = 5;
		c.setX(expected);

		double actual = c.getX();
		
		assertEquals(expected, actual, delta);
	}

	@Test
	public void testSetY() {
		double expected = 5;
		c.setY(expected);

		double actual = c.getY();
		
		assertEquals(expected, actual, delta);
	}

	@Test
	public void testEqualsCoordinate() {
		Coordinate figure = new Coordinate(2,3);
		Coordinate ground = new Coordinate(2,3);

		assertTrue(figure.equals(ground));
		
		figure.setX(1);
		
		assertFalse(figure.equals(ground));
	}

}
