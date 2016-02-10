package junit.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import model.Coordinate;

public class CoordinateTest {

	private Coordinate c;
	private int expectedX = 1, expectedY = 1;

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
		int expected = expectedX, actual = c.getX();

		assertEquals(expected, actual);
	}

	@Test
	public void testGetY() {
		int expected = expectedY, actual = c.getY();

		assertEquals(expected, actual);
	}

	@Test
	public void testSetX() {
		int expected = 5;
		c.setX(expected);

		int actual = c.getX();
		
		assertEquals(expected, actual);
	}

	@Test
	public void testSetY() {
		int expected = 5;
		c.setY(expected);

		int actual = c.getY();
		
		assertEquals(expected, actual);
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
