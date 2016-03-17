package junit.model;

import model.Ball;
import model.gizmos.Absorber;
import org.junit.Before;
import org.junit.Test;
import physics.Vect;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by baird on 16/02/2016.
 */
public class BallTest {

	Ball ball;
	@Before
	public void setUp() throws Exception {
		ball = new Ball("", 5, 5, 2, 2);
	}

	@Test
	public void testBall() throws Exception {
		//See spreadsheet test 125 127
		assert ball.getCenter().equals(new Vect(5, 5));
		assert ball.getBound().equals(new Vect(5.25, 5.25));
		assert ball.getVelocity().equals(new Vect(2, 2));
	}

	@Test
	public void testGetCenter() throws Exception {
		//See spreadsheet test 126
		ball.setCenter(new Vect(6, 6));
		assert ball.getCenter().equals(new Vect(6, 6));
	}

	@Test
	public void testUpdate() throws Exception {
		//See spreadsheet test 128
		ball.moveForTime(1);
		assert ball.getCenter().equals(new Vect(7, 7));
		assert ball.getCircle().getCenter().equals(new Vect(7, 7));
	}

	@Test
	public void testSaveString() throws Exception {
		//See spreadsheet test 129
		String actual = ball.getSaveInfo();
		String expected = "Ball  5.0 5.0 2.0 2.0";
		assertEquals(actual, expected);
	}

	@Test
	public void testInside() throws Exception {
		//See spreadsheet test 130

		Absorber absorber = new Absorber(new Vect(0, 4),new Vect(10, 5), "Test");


		assertTrue(ball.inside(absorber));

		absorber.move(new Vect(0, 2));

		assertFalse(ball.inside(absorber));
	}

	@Test
	public void testMoveForTime() throws Exception {
		//See spreadsheet test 131

		ball.moveForTime(0.05);

		assertThat(ball.getCenter(), is(new Vect(5.1, 5.1)));
	}

}