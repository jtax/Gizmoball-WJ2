package junit.model;

import model.Ball;
import model.Collision;
import model.gizmos.Absorber;
import model.gizmos.Square;
import org.junit.Before;
import org.junit.Test;
import physics.Vect;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
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


	@Test
	public void testHandle() throws Exception {
		//See spreadsheet test 132
		System.out.println(ball.getCenter());

		ball.handle(new Collision(new Vect(1,0), 0.05, new Square(new Vect(5,6), "Test"), ball));

		assertThat(ball.getCenter(), is(new Vect(5.1, 5.1)));
		assertThat(ball.getVelocity(), is(new Vect(1, 0)));
	}

	@Test
	public void testApplyForcesAbsorbed() throws Exception {
		//See spreadsheet test 133

		Vect oldVelo = ball.getVelocity();

		Absorber absorber = new Absorber(new Vect(0, 0),new Vect(20, 20), "Test");
		absorber.absorb(ball);
		ball.applyForces(0.05, 5, new double[]{0.025, 0.025});

		assertThat(ball.getVelocity(), is(oldVelo));
	}
	@Test
	public void testApplyForces() throws Exception {
		//See spreadsheet test 133 & 134 & 135

		Vect oldVelo = ball.getVelocity();

		ball.applyForces(0.05, 5, new double[]{0.025, 0.025});

		assertThat(ball.getVelocity(), not(oldVelo));

		Vect newVelo = oldVelo.times(1 - 0.025 * 0.05 - 0.025 * oldVelo.length() * 0.05);
		newVelo = newVelo.plus(new Vect(0, 5 * 0.05));

		assertEquals(ball.getVelocity().x(),newVelo.x(), 0.01);
		assertEquals(ball.getVelocity().y(),newVelo.y(), 0.01);
		assertEquals(ball.getVelocity().angle().radians(),newVelo.angle().radians(), 0.01);
	}

	@Test
	public void testRelease() throws Exception {
		//See spreadsheet test 136

		Absorber absorber = new Absorber(new Vect(0, 0),new Vect(20, 20), "Test");
		absorber.absorb(ball);

		assertTrue(ball.isAbsorbed());

		ball.release();

		assertFalse(ball.isAbsorbed());

		assertThat(ball.getVelocity(), is(new Vect(0, -50)));

	}

	@Test
	public void testReleaseNotAbsorbed() throws Exception {
		//See spreadsheet test 136

		assertFalse(ball.isAbsorbed());

		ball.release();

		assertFalse(ball.isAbsorbed());

		assertThat(ball.getVelocity(), not(new Vect(0, -50)));

	}

}