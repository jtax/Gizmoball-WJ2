package junit.model.gizmos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import model.Direction;
import model.gizmos.Flipper;
import physics.Vect;

public class FlipperTest {

	private final int expectedOriginX = 0, expectedOriginY = 0;
	private final Vect expectedOrigin = new Vect(expectedOriginX, expectedOriginY), flipperSize = new Vect(2, 2),
			expectedBound = expectedOrigin.plus(flipperSize);
	private final String expectedName = "flipper";
	private final Flipper immutableFlipper = new Flipper(expectedOrigin, expectedName, Direction.LEFT);

	@Test
	public void testMove() {
		Vect originalOrigin = new Vect(2, 2);
		Vect moveBy = new Vect(5, 6);
		Vect expectedOrigin = originalOrigin.plus(moveBy);

		Flipper f = new Flipper(originalOrigin, "Flipper", Direction.LEFT);
		f.move(moveBy);
		Vect actualOrigin = f.getOrigin();

		assertEquals(expectedOrigin, actualOrigin);
	}

	@Test
	public void testTrigger() {
		fail("Not yet implemented");
	}

	@Test
	public void testRotateOrigin() {
		Flipper f = new Flipper(expectedOrigin, "flipper", Direction.LEFT);
		f.rotate();

		Vect actualOrigin = f.getOrigin();
		assertEquals(expectedOrigin, actualOrigin);
	}

	@Test
	public void testRotateBound() {
		Flipper f = new Flipper(expectedOrigin, "flipper", Direction.LEFT);
		f.rotate();

		Vect actualBound = f.getBound();
		assertEquals(expectedBound, actualBound);
	}

	@Test
	public void testCalculateBound() {
		Vect actualBound = immutableFlipper.getBound();
		assertEquals(expectedBound, actualBound);
	}

	@Test
	public void testFlipperVectString() { // i.e. the constructor
		Vect actualOrigin = immutableFlipper.getOrigin();
		assertEquals(expectedOrigin, actualOrigin);

		String actualName = immutableFlipper.getName();
		assertEquals(expectedName, actualName);
	}

	@Test
	public void testGetPivotPointLeftRotate0() {
		Flipper f = new Flipper(expectedOrigin, "Flipper", Direction.LEFT);
		
		Vect expectedPivotPoint = expectedOrigin.plus(new Vect(0.25, 0.25));
		Vect actualPivotPoint = f.getPivotPoint();
		
		assertEquals(expectedPivotPoint, actualPivotPoint);
	}
	
	@Test
	public void testGetPivotPointLeftRotate1() {
		Flipper f = new Flipper(expectedOrigin, "Flipper", Direction.LEFT);
		for (int r = 0; r < 1; ++r)
			f.rotate();
		
		Vect expectedPivotPoint = expectedOrigin.plus(new Vect(1.75, 0.25));
		Vect actualPivotPoint = f.getPivotPoint();
		
		assertEquals(expectedPivotPoint, actualPivotPoint);
	}
	
	@Test
	public void testGetPivotPointLeftRotate2() {
		Flipper f = new Flipper(expectedOrigin, "Flipper", Direction.LEFT);
		for (int r = 0; r < 2; ++r)
			f.rotate();
		
		Vect expectedPivotPoint = expectedOrigin.plus(new Vect(1.75, 1.75));
		Vect actualPivotPoint = f.getPivotPoint();
		
		assertEquals(expectedPivotPoint, actualPivotPoint);
	}
	
	@Test
	public void testGetPivotPointLeftRotate3() {
		Flipper f = new Flipper(expectedOrigin, "Flipper", Direction.LEFT);
		for (int r = 0; r < 3; ++r)
			f.rotate();
		
		Vect expectedPivotPoint = expectedOrigin.plus(new Vect(0.25, 1.75));
		Vect actualPivotPoint = f.getPivotPoint();
		
		assertEquals(expectedPivotPoint, actualPivotPoint);
	}
	
	@Test
	public void testGetPivotPointRightRotate0() {
		Flipper f = new Flipper(expectedOrigin, "Flipper", Direction.RIGHT);
		
		Vect expectedPivotPoint = expectedOrigin.plus(new Vect(1.75, 0.25));
		Vect actualPivotPoint = f.getPivotPoint();
		
		assertEquals(expectedPivotPoint, actualPivotPoint);
	}
	
	@Test
	public void testGetPivotPointRightRotate1() {
		Flipper f = new Flipper(expectedOrigin, "Flipper", Direction.RIGHT);
		for (int r = 0; r < 1; ++r)
			f.rotate();
		
		Vect expectedPivotPoint = expectedOrigin.plus(new Vect(1.75, 1.75));
		Vect actualPivotPoint = f.getPivotPoint();
		
		assertEquals(expectedPivotPoint, actualPivotPoint);
	}
	
	@Test
	public void testGetPivotPointRightRotate2() {
		Flipper f = new Flipper(expectedOrigin, "Flipper", Direction.RIGHT);
		for (int r = 0; r < 2; ++r)
			f.rotate();
		
		Vect expectedPivotPoint = expectedOrigin.plus(new Vect(0.25, 1.75));
		Vect actualPivotPoint = f.getPivotPoint();
		
		assertEquals(expectedPivotPoint, actualPivotPoint);
	}
	
	@Test
	public void testGetPivotPointRightRotate3() {
		Flipper f = new Flipper(expectedOrigin, "Flipper", Direction.RIGHT);
		for (int r = 0; r < 3; ++r)
			f.rotate();
		
		Vect expectedPivotPoint = expectedOrigin.plus(new Vect(0.25, 0.25));
		Vect actualPivotPoint = f.getPivotPoint();
		
		assertEquals(expectedPivotPoint, actualPivotPoint);
	}

	@Test
	public void testGetAngularVelocity() {
		fail("Not yet implemented");
	}

	@Test
	public void testFlipperIntIntString() {
		int expectedOriginX = (int) expectedOrigin.x(), expectedOriginY = (int) expectedOrigin.y();
		String expectedName = "Flipperino";

		Flipper f = new Flipper(expectedOriginX, expectedOriginY, expectedName, Direction.LEFT);

		int actualOriginX = (int) f.getOrigin().x(), actualOriginY = (int) f.getOrigin().y();
		String actualName = f.getName();

		assertEquals(expectedOriginX, actualOriginX);
		assertEquals(expectedOriginY, actualOriginY);
		assertEquals(expectedName, actualName);

	}

	@Test
	public void testRotationMatrix() {
		fail("Not yet implemented");
	}

	@Test
	public void testFlip() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSaveInfoLeftFlipper() {
		String expectedSaveInfo = String.format("\"LeftFlipper\" %s %d %d", expectedName, expectedOriginX,
				expectedOriginY);
		String actualSaveInfo = immutableFlipper.getSaveInfo();
		
		assertEquals(expectedSaveInfo, actualSaveInfo);
	}
	
	@Test
	public void testGetSaveInfoRightFlipper() {
		String expectedSaveInfo = String.format("\"RightFlipper\" %s %d %d", expectedName, expectedOriginX,
				expectedOriginY);
		String actualSaveInfo = immutableFlipper.getSaveInfo();
		
		assertEquals(expectedSaveInfo, actualSaveInfo);
	}

	@Test
	public void testGetRotation() {
		fail("Not yet implemented");
	}

	@Test
	public void testLeftFlipperCoordinatesRotate0() {
		Flipper f = new Flipper(expectedOrigin, "El Flipperino", Direction.LEFT);
		
		List<Vect> expectedCoordinates = new ArrayList<>();
		expectedCoordinates.add(expectedOrigin);
		expectedCoordinates.add(expectedOrigin.plus(new Vect(0.5, 0)));
		expectedCoordinates.add(expectedOrigin.plus(new Vect(0.5, 2)));
		expectedCoordinates.add(expectedOrigin.plus(new Vect(0, 2)));

		List<Vect> actualCoordinates = f.getCoordinates();
		
		assertEquals(expectedCoordinates, actualCoordinates);
	}
	
	@Test
	public void testLeftFlipperCoordinatesRotate1() {
		Flipper f = new Flipper(expectedOrigin, "El Flipperino", Direction.LEFT);
		f.rotate();
		
		List<Vect> expectedCoordinates = new ArrayList<>();
		expectedCoordinates.add(expectedOrigin);
		expectedCoordinates.add(expectedOrigin.plus(new Vect(2, 0)));
		expectedCoordinates.add(expectedOrigin.plus(new Vect(2, 0.5)));
		expectedCoordinates.add(expectedOrigin.plus(new Vect(0, 0.5)));

		List<Vect> actualCoordinates = f.getCoordinates();
		
		assertEquals(expectedCoordinates, actualCoordinates);
	}
	
	@Test
	public void testLeftFlipperCoordinatesRotate2() {
		Flipper f = new Flipper(expectedOrigin, "El Flipperino", Direction.LEFT);
		for (int c = 0; c < 2; ++c)
			f.rotate();

		List<Vect> expectedCoordinates = new ArrayList<>();
		expectedCoordinates.add(expectedOrigin.plus(new Vect(1.5, 0)));
		expectedCoordinates.add(expectedOrigin.plus(new Vect(2, 0)));
		expectedCoordinates.add(expectedOrigin.plus(new Vect(2, 2)));
		expectedCoordinates.add(expectedOrigin.plus(new Vect(1.5, 2)));

		List<Vect> actualCoordinates = f.getCoordinates();
		
		assertEquals(expectedCoordinates, actualCoordinates);
	}
	
	@Test
	public void testLeftFlipperCoordinatesRotate3() {
		Flipper f = new Flipper(expectedOrigin, "El Flipperino", Direction.LEFT);
		for (int c = 0; c < 3; ++c)
			f.rotate();

		List<Vect> expectedCoordinates = new ArrayList<>();
		expectedCoordinates.add(expectedOrigin.plus(new Vect(0, 1.5)));
		expectedCoordinates.add(expectedOrigin.plus(new Vect(2, 1.5)));
		expectedCoordinates.add(expectedOrigin.plus(new Vect(2, 2)));
		expectedCoordinates.add(expectedOrigin.plus(new Vect(0, 2)));

		List<Vect> actualCoordinates = f.getCoordinates();
		
		assertEquals(expectedCoordinates, actualCoordinates);
	}
	
	@Test
	public void testRightFlipperCoordinatesRotate0() {
		Flipper f = new Flipper(expectedOrigin, "onireppilF lE", Direction.RIGHT);
		for (int c = 0; c < 0; ++c)
			f.rotate();

		List<Vect> expectedCoordinates = new ArrayList<>();
		expectedCoordinates.add(expectedOrigin.plus(new Vect(1.5, 0)));
		expectedCoordinates.add(expectedOrigin.plus(new Vect(2, 0)));
		expectedCoordinates.add(expectedOrigin.plus(new Vect(2, 2)));
		expectedCoordinates.add(expectedOrigin.plus(new Vect(1.5, 2)));

		List<Vect> actualCoordinates = f.getCoordinates();
		
		assertEquals(expectedCoordinates, actualCoordinates);
	}
	
	@Test
	public void testRightFlipperCoordinatesRotate1() {
		Flipper f = new Flipper(expectedOrigin, "onireppilF lE", Direction.RIGHT);
		for (int c = 0; c < 1; ++c)
			f.rotate();

		List<Vect> expectedCoordinates = new ArrayList<>();
		expectedCoordinates.add(expectedOrigin.plus(new Vect(0, 1.5)));
		expectedCoordinates.add(expectedOrigin.plus(new Vect(2, 1.5)));
		expectedCoordinates.add(expectedOrigin.plus(new Vect(2, 2)));
		expectedCoordinates.add(expectedOrigin.plus(new Vect(0, 2)));

		List<Vect> actualCoordinates = f.getCoordinates();
		
		assertEquals(expectedCoordinates, actualCoordinates);
	}
	
	@Test
	public void testRightFlipperCoordinatesRotate2() {
		Flipper f = new Flipper(expectedOrigin, "onireppilF lE", Direction.RIGHT);
		for (int c = 0; c < 2; ++c)
			f.rotate();

		List<Vect> expectedCoordinates = new ArrayList<>();
		expectedCoordinates.add(expectedOrigin);
		expectedCoordinates.add(expectedOrigin.plus(new Vect(0.5, 0)));
		expectedCoordinates.add(expectedOrigin.plus(new Vect(0.5, 2)));
		expectedCoordinates.add(expectedOrigin.plus(new Vect(0, 2)));

		List<Vect> actualCoordinates = f.getCoordinates();
		
		assertEquals(expectedCoordinates, actualCoordinates);
	}
	
	@Test
	public void testRightFlipperCoordinatesRotate3() {
		Flipper f = new Flipper(expectedOrigin, "onireppilF lE", Direction.RIGHT);
		for (int c = 0; c < 3; ++c)
			f.rotate();

		List<Vect> expectedCoordinates = new ArrayList<>();
		expectedCoordinates.add(expectedOrigin);
		expectedCoordinates.add(expectedOrigin.plus(new Vect(2, 0)));
		expectedCoordinates.add(expectedOrigin.plus(new Vect(2, 0.5)));
		expectedCoordinates.add(expectedOrigin.plus(new Vect(0, 0.5)));

		List<Vect> actualCoordinates = f.getCoordinates();
		
		assertEquals(expectedCoordinates, actualCoordinates);
	}

	/*
	// FIXME using getBound incorrectly.
	@Test
	public void testFlipLeftDirection() throws Exception {

		Flipper test = new Flipper(new Vect(0, 0), "Test Left");

		System.out.println("Before X:" + test.getBound().x() + ", Y:" + test.getBound().y());
		test.flip();
		System.out.println("After X:" + test.getBound().x() + ", Y:" + test.getBound().y());

		assertTrue(test.getBound().equals(new Vect(-1, 1)));

		test.flip();

		System.out.println("Flip back X:" + test.getBound().x() + ", Y:" + test.getBound().y());

		assertTrue(test.getBound().equals(new Vect(1, 1)));

	}

	// FIXME using getBound incorrectly.
	@Test
	public void testFlipRightDirection() throws Exception {

		Flipper test = new Flipper(new Vect(0, 0), "Test Right");
		test.setDirection(Direction.RIGHT);

		System.out.println("Before X:" + test.getBound().x() + ", Y:" + test.getBound().y());
		test.flip();
		System.out.println("After X:" + test.getBound().x() + ", Y:" + test.getBound().y());

		assertTrue(test.getBound().equals(new Vect(1, -1)));

		test.flip();

		System.out.println("Flip back X:" + test.getBound().x() + ", Y:" + test.getBound().y());
		assertTrue(test.getBound().equals(new Vect(1, 1)));

	}
	*/
}