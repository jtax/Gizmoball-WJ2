package junit.model.gizmos;

import model.IElement;
import model.gizmos.Circle;

import org.junit.Before;
import org.junit.Test;
import physics.LineSegment;
import physics.Vect;

import javax.sound.sampled.Line;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by baird on 16/02/2016.
 */
public class CircleTest {

	Circle circle;

	@Before
	public void setUp() throws Exception {
		circle = new Circle(new Vect(5, 5), "Test");
	}

	@Test
	public void testCircle(){
		//See spreadsheet Test 77
		assert (circle.getOrigin().equals(new Vect(5, 5)));
		assert (circle.getName().equals("Test"));
		assert (circle.getRotation() == 0);
	}

	@Test
	public void getCenterTest() {
		//See spreadsheet Test 80
		Vect expected = new Vect(5.5, 5.5);
		assert(circle.getCircles().get(0).getCenter().equals(expected));
	}

	@Test
	public void calculateCirclesTest() {
		//See spreadsheet Test 80
		Vect expected = new Vect(5.5, 5.5);
		assert(circle.getCircles().get(0).getCenter().equals(expected));
	}

	@Test
	public void testRotate() throws Exception {
		Circle circleClone = circle;
		circle.rotate();
		assertEquals(circle.getOrigin(), circleClone.getOrigin());
		assertEquals(circle.getBound(), circleClone.getBound());

	}

	@Test
	public void testCalculateBound() throws Exception {
		//See spreadsheet Test 81
		Vect bound = circle.calculateBound();
		assertEquals(bound, new Vect(6, 6));

	}

	@Test
	public void testLines() throws Exception {
		//See spreadsheet Test 88
		assert (circle.getLines().size() == 0);
	}

	@Test
	public void testCircles() throws Exception {
		//See spreadsheet Test 86
		assert (circle.getCircles().size() == 1);
		physics.Circle circ = circle.getCircles().get(0);
		assertEquals(circ.getCenter(), new Vect(5.5, 5.5));
		assertEquals(circ.getRadius(), 0.5, 2);
	}

	@Test
	public void move() {
		//See spreadsheet Test 90
		circle.move(new Vect(1, 1));
		assert circle.getOrigin().equals(new Vect(6, 6));
	}

	@Test
	public void saveString() {
		//See spreadsheet Test 91
		String expected = "Circle Test 5 5";
		assert(circle.getSaveInfo().equals(expected));
	}


}