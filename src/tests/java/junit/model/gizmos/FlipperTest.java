package junit.model.gizmos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import model.Direction;
import model.gizmos.Flipper;
import physics.Circle;
import physics.LineSegment;
import physics.Vect;

public class FlipperTest {

	private final int expectedOriginX = 0, expectedOriginY = 0;
	private final Vect expectedOrigin = new Vect(expectedOriginX, expectedOriginY), flipperSize = new Vect(2, 2),
			expectedBound = expectedOrigin.plus(flipperSize);
	private final String expectedName = "flipper";
	private final Flipper immutableFlipper = new Flipper(expectedOrigin, expectedName);

	@Test
	public void testMove() {
		Vect originalOrigin = new Vect(2, 2);
		Vect moveBy = new Vect(5, 6);
		Vect expectedOrigin = originalOrigin.plus(moveBy);

		Flipper f = new Flipper(originalOrigin, "Flipper");
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
		Flipper f = new Flipper(expectedOrigin, "flipper");
		f.rotate();

		Vect actualOrigin = f.getOrigin();
		assertEquals(expectedOrigin, actualOrigin);
	}

	@Test
	public void testRotateBound() {
		Flipper f = new Flipper(expectedOrigin, "flipper");
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
		Flipper f = new Flipper(expectedOrigin, "Flipper");

		Vect expectedPivotPoint = expectedOrigin.plus(new Vect(0.25, 0.25));
		Vect actualPivotPoint = f.getPivotPoint();

		assertEquals(expectedPivotPoint, actualPivotPoint);
	}

	@Test
	public void testGetPivotPointLeftRotate1() {
		Flipper f = new Flipper(expectedOrigin, "Flipper");
		for (int r = 0; r < 1; ++r)
			f.rotate();

		Vect expectedPivotPoint = expectedOrigin.plus(new Vect(1.75, 0.25));
		Vect actualPivotPoint = f.getPivotPoint();

		assertEquals(expectedPivotPoint, actualPivotPoint);
	}

	@Test
	public void testGetPivotPointLeftRotate2() {
		Flipper f = new Flipper(expectedOrigin, "Flipper");
		for (int r = 0; r < 2; ++r)
			f.rotate();

		Vect expectedPivotPoint = expectedOrigin.plus(new Vect(1.75, 1.75));
		Vect actualPivotPoint = f.getPivotPoint();

		assertEquals(expectedPivotPoint, actualPivotPoint);
	}

	@Test
	public void testGetPivotPointLeftRotate3() {
		Flipper f = new Flipper(expectedOrigin, "Flipper");
		for (int r = 0; r < 3; ++r)
			f.rotate();

		Vect expectedPivotPoint = expectedOrigin.plus(new Vect(0.25, 1.75));
		Vect actualPivotPoint = f.getPivotPoint();

		assertEquals(expectedPivotPoint, actualPivotPoint);
	}

	@Test
	public void testGetPivotPointRightRotate0() {
		Flipper f = new Flipper(expectedOrigin, "Flipper");
		f.setDirection(Direction.RIGHT);

		Vect expectedPivotPoint = expectedOrigin.plus(new Vect(1.75, 0.25));
		Vect actualPivotPoint = f.getPivotPoint();

		assertEquals(expectedPivotPoint, actualPivotPoint);
	}

	@Test
	public void testGetPivotPointRightRotate1() {
		Flipper f = new Flipper(expectedOrigin, "Flipper");
		f.setDirection(Direction.RIGHT);
		for (int r = 0; r < 1; ++r)
			f.rotate();

		Vect expectedPivotPoint = expectedOrigin.plus(new Vect(1.75, 1.75));
		Vect actualPivotPoint = f.getPivotPoint();

		assertEquals(expectedPivotPoint, actualPivotPoint);
	}

	@Test
	public void testGetPivotPointRightRotate2() {
		Flipper f = new Flipper(expectedOrigin, "Flipper");
		f.setDirection(Direction.RIGHT);
		for (int r = 0; r < 2; ++r)
			f.rotate();

		Vect expectedPivotPoint = expectedOrigin.plus(new Vect(0.25, 1.75));
		Vect actualPivotPoint = f.getPivotPoint();

		assertEquals(expectedPivotPoint, actualPivotPoint);
	}

	@Test
	public void testGetPivotPointRightRotate3() {
		Flipper f = new Flipper(expectedOrigin, "Flipper");
		f.setDirection(Direction.RIGHT);
		for (int r = 0; r < 3; ++r)
			f.rotate();

		Vect expectedPivotPoint = expectedOrigin.plus(new Vect(0.25, 0.25));
		Vect actualPivotPoint = f.getPivotPoint();

		assertEquals(expectedPivotPoint, actualPivotPoint);
	}

	@Test
	public void testGetAngularVelocity() {
		// TODO what should the angular velocity be anyway?
		fail("Not yet implemented");
	}

	@Test
	public void testFlipperIntIntString() {
		int expectedOriginX = (int) expectedOrigin.x(), expectedOriginY = (int) expectedOrigin.y();
		String expectedName = "Flipperino";

		Flipper f = new Flipper(expectedOriginX, expectedOriginY, expectedName);

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
	public void testDefaultDirection() {
		Direction expectedDirection = Direction.LEFT;
		Direction actualDirection = immutableFlipper.getDirection();
		assertEquals(expectedDirection, actualDirection);
	}

	@Test
	public void testSetDirectionLeft() {
		Direction expectedDirection = Direction.LEFT;
		Flipper f = new Flipper(expectedOrigin, expectedName);

		f.setDirection(expectedDirection);
		Direction actualDirection = f.getDirection();

		assertEquals(expectedDirection, actualDirection);

		assertEquals(expectedOrigin, f.getOrigin());
		assertEquals(expectedBound, f.getBound());
	}

	@Test
	public void testSetDirectionRight() {
		Flipper f = new Flipper(expectedOrigin, expectedName);

		Direction expectedDirection = Direction.RIGHT;
		f.setDirection(expectedDirection);
		Direction actualDirection = f.getDirection();

		assertEquals(expectedDirection, actualDirection);
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
		Flipper f = new Flipper(expectedOrigin, "El Flipperino");

		List<Vect> expectedCoordinates = coordinateMapToList(expectedCoordinatesOfFlipperAtLeftOfBoundingBox());
		List<Vect> actualCoordinates = f.getCoordinates();

		assertEquals(expectedCoordinates, actualCoordinates);
	}

	@Test
	public void testLeftFlipperCoordinatesRotate1() {
		Flipper f = new Flipper(expectedOrigin, "El Flipperino");
		f.rotate();

		List<Vect> expectedCoordinates = coordinateMapToList(expectedCoordinatesOfFlipperAtTopOfBoundingBox());
		List<Vect> actualCoordinates = f.getCoordinates();

		assertEquals(expectedCoordinates, actualCoordinates);
	}

	@Test
	public void testLeftFlipperCoordinatesRotate2() {
		Flipper f = new Flipper(expectedOrigin, "El Flipperino");
		for (int c = 0; c < 2; ++c)
			f.rotate();

		List<Vect> expectedCoordinates = coordinateMapToList(expectedCoordinatesOfFlipperAtRightOfBoundingBox());
		List<Vect> actualCoordinates = f.getCoordinates();

		assertEquals(expectedCoordinates, actualCoordinates);
	}

	@Test
	public void testLeftFlipperCoordinatesRotate3() {
		Flipper f = new Flipper(expectedOrigin, "El Flipperino");
		for (int c = 0; c < 3; ++c)
			f.rotate();

		List<Vect> expectedCoordinates = coordinateMapToList(expectedCoordinatesOfFlipperAtBottomOfBoundingBox());
		List<Vect> actualCoordinates = f.getCoordinates();

		assertEquals(expectedCoordinates, actualCoordinates);
	}

	@Test
	public void testRightFlipperCoordinatesRotate0() {
		Flipper f = new Flipper(expectedOrigin, "onireppilF lE");
		f.setDirection(Direction.RIGHT);
		for (int c = 0; c < 0; ++c)
			f.rotate();

		List<Vect> expectedCoordinates = coordinateMapToList(expectedCoordinatesOfFlipperAtRightOfBoundingBox());
		List<Vect> actualCoordinates = f.getCoordinates();

		assertEquals(expectedCoordinates, actualCoordinates);
	}

	@Test
	public void testRightFlipperCoordinatesRotate1() {
		Flipper f = new Flipper(expectedOrigin, "onireppilF lE");
		f.setDirection(Direction.RIGHT);
		for (int c = 0; c < 1; ++c)
			f.rotate();

		List<Vect> expectedCoordinates = coordinateMapToList(expectedCoordinatesOfFlipperAtBottomOfBoundingBox());
		List<Vect> actualCoordinates = f.getCoordinates();

		assertEquals(expectedCoordinates, actualCoordinates);
	}

	@Test
	public void testRightFlipperCoordinatesRotate2() {
		Flipper f = new Flipper(expectedOrigin, "onireppilF lE");
		f.setDirection(Direction.RIGHT);
		for (int c = 0; c < 2; ++c)
			f.rotate();

		List<Vect> expectedCoordinates = coordinateMapToList(expectedCoordinatesOfFlipperAtLeftOfBoundingBox());
		List<Vect> actualCoordinates = f.getCoordinates();

		assertEquals(expectedCoordinates, actualCoordinates);
	}

	@Test
	public void testRightFlipperCoordinatesRotate3() {
		Flipper f = new Flipper(expectedOrigin, "onireppilF lE");
		f.setDirection(Direction.RIGHT);
		for (int c = 0; c < 3; ++c)
			f.rotate();

		List<Vect> expectedCoordinates = coordinateMapToList(expectedCoordinatesOfFlipperAtTopOfBoundingBox());
		List<Vect> actualCoordinates = f.getCoordinates();

		assertEquals(expectedCoordinates, actualCoordinates);
	}

	@Test
	public void testCalculateLinesLeftRotate0() {
		Flipper f = new Flipper(expectedOrigin, "El Flipperino");
		for (int c = 0; c < 0; c++)
			f.rotate();

		List<LineSegment> expectedLines = coordinateMapToLineSegments(
				expectedCoordinatesOfFlipperAtLeftOfBoundingBox());
		List<LineSegment> actualLines = f.getLines();

		assertEquals(expectedLines, actualLines);
	}

	@Test
	public void testCalculateLinesLeftRotate1() {
		Flipper f = new Flipper(expectedOrigin, "El Flipperino");
		for (int c = 0; c < 1; c++)
			f.rotate();

		List<LineSegment> expectedLines = coordinateMapToLineSegments(expectedCoordinatesOfFlipperAtTopOfBoundingBox());
		List<LineSegment> actualLines = f.getLines();

		assertEquals(expectedLines, actualLines);
	}

	@Test
	public void testCalculateLinesLeftRotate2() {
		Flipper f = new Flipper(expectedOrigin, "El Flipperino");
		for (int c = 0; c < 2; c++)
			f.rotate();

		List<LineSegment> expectedLines = coordinateMapToLineSegments(
				expectedCoordinatesOfFlipperAtRightOfBoundingBox());
		List<LineSegment> actualLines = f.getLines();

		assertEquals(expectedLines, actualLines);
	}

	@Test
	public void testCalculateLinesLeftRotate3() {
		Flipper f = new Flipper(expectedOrigin, "El Flipperino");
		for (int c = 0; c < 3; c++)
			f.rotate();

		List<LineSegment> expectedLines = coordinateMapToLineSegments(
				expectedCoordinatesOfFlipperAtBottomOfBoundingBox());
		List<LineSegment> actualLines = f.getLines();

		assertEquals(expectedLines, actualLines);
	}

	@Test
	public void testCalculateLinesRightRotate0() {
		Flipper f = new Flipper(expectedOrigin, "onireppilF lE");
		f.setDirection(Direction.RIGHT);
		for (int c = 0; c < 0; c++)
			f.rotate();

		List<LineSegment> expectedLines = coordinateMapToLineSegments(
				expectedCoordinatesOfFlipperAtRightOfBoundingBox());
		List<LineSegment> actualLines = f.getLines();

		assertEquals(expectedLines, actualLines);
	}

	@Test
	public void testCalculateLinesRightRotate1() {
		Flipper f = new Flipper(expectedOrigin, "onireppilF lE");
		f.setDirection(Direction.RIGHT);
		for (int c = 0; c < 1; c++)
			f.rotate();

		List<LineSegment> expectedLines = coordinateMapToLineSegments(
				expectedCoordinatesOfFlipperAtBottomOfBoundingBox());
		List<LineSegment> actualLines = f.getLines();

		assertEquals(expectedLines, actualLines);
	}

	@Test
	public void testCalculateLinesRightRotate2() {
		Flipper f = new Flipper(expectedOrigin, "onireppilF lE");
		f.setDirection(Direction.RIGHT);
		for (int c = 0; c < 2; c++)
			f.rotate();

		List<LineSegment> expectedLines = coordinateMapToLineSegments(
				expectedCoordinatesOfFlipperAtLeftOfBoundingBox());
		List<LineSegment> actualLines = f.getLines();

		assertEquals(expectedLines, actualLines);
	}

	@Test
	public void testCalculateLinesRightRotate3() {
		Flipper f = new Flipper(expectedOrigin, "onireppilF lE");
		f.setDirection(Direction.RIGHT);
		for (int c = 0; c < 3; c++)
			f.rotate();

		List<LineSegment> expectedLines = coordinateMapToLineSegments(expectedCoordinatesOfFlipperAtTopOfBoundingBox());
		List<LineSegment> actualLines = f.getLines();

		assertEquals(expectedLines, actualLines);
	}

	@Test
	public void testCalculateCirclesLeftRotate0() {
		Flipper f = new Flipper(expectedOrigin, "El Fliperrino");
		for (int c = 0; c < 0; c++)
			f.rotate();

		List<Circle> expectedCircles = coordinateMapToCircles(expectedCoordinatesOfFlipperAtLeftOfBoundingBox());
		List<Circle> actualCircles = f.getCircles();

		assertEquals(expectedCircles, actualCircles);
	}

	@Test
	public void testCalculateCirclesLeftRotate1() {
		Flipper f = new Flipper(expectedOrigin, "El Fliperrino");
		for (int c = 0; c < 1; c++)
			f.rotate();

		List<Circle> expectedCircles = coordinateMapToCircles(expectedCoordinatesOfFlipperAtTopOfBoundingBox());
		List<Circle> actualCircles = f.getCircles();

		assertEquals(expectedCircles, actualCircles);
	}

	@Test
	public void testCalculateCirclesLeftRotate2() {
		Flipper f = new Flipper(expectedOrigin, "El Fliperrino");
		for (int c = 0; c < 2; c++)
			f.rotate();

		List<Circle> expectedCircles = coordinateMapToCircles(expectedCoordinatesOfFlipperAtRightOfBoundingBox());
		List<Circle> actualCircles = f.getCircles();

		assertEquals(expectedCircles, actualCircles);
	}

	@Test
	public void testCalculateCirclesLeftRotate3() {
		Flipper f = new Flipper(expectedOrigin, "El Fliperrino");
		for (int c = 0; c < 3; c++)
			f.rotate();

		List<Circle> expectedCircles = coordinateMapToCircles(expectedCoordinatesOfFlipperAtRightOfBoundingBox());
		List<Circle> actualCircles = f.getCircles();

		assertEquals(expectedCircles, actualCircles);
	}

	@Test
	public void testCalculateCirclesRightRotate0() {
		Flipper f = new Flipper(expectedOrigin, "El Fliperrino");
		f.setDirection(Direction.RIGHT);
		for (int c = 0; c < 0; c++)
			f.rotate();

		List<Circle> expectedCircles = coordinateMapToCircles(expectedCoordinatesOfFlipperAtRightOfBoundingBox());
		List<Circle> actualCircles = f.getCircles();

		assertEquals(expectedCircles, actualCircles);
	}

	@Test
	public void testCalculateCirclesBottomRotate1() {
		Flipper f = new Flipper(expectedOrigin, "El Fliperrino");
		f.setDirection(Direction.RIGHT);
		for (int c = 0; c < 1; c++)
			f.rotate();

		List<Circle> expectedCircles = coordinateMapToCircles(expectedCoordinatesOfFlipperAtBottomOfBoundingBox());
		List<Circle> actualCircles = f.getCircles();

		assertEquals(expectedCircles, actualCircles);
	}

	@Test
	public void testCalculateCirclesRightRotate2() {
		Flipper f = new Flipper(expectedOrigin, "El Fliperrino");
		f.setDirection(Direction.RIGHT);
		for (int c = 0; c < 2; c++)
			f.rotate();

		List<Circle> expectedCircles = coordinateMapToCircles(expectedCoordinatesOfFlipperAtLeftOfBoundingBox());
		List<Circle> actualCircles = f.getCircles();

		assertEquals(expectedCircles, actualCircles);
	}

	@Test
	public void testCalculateCirclesRightRotate3() {
		Flipper f = new Flipper(expectedOrigin, "El Fliperrino");
		f.setDirection(Direction.RIGHT);
		for (int c = 0; c < 3; c++)
			f.rotate();

		List<Circle> expectedCircles = coordinateMapToCircles(expectedCoordinatesOfFlipperAtTopOfBoundingBox());
		List<Circle> actualCircles = f.getCircles();

		assertEquals(expectedCircles, actualCircles);
	}

	private enum CoordinateLocation {
		TOP_LEFT, TOP_RIGHT, BOTTOM_RIGHT, BOTTOM_LEFT;
	}

	private Map<CoordinateLocation, Vect> expectedCoordinatesOfFlipperAtLeftOfBoundingBox() {
		Map<CoordinateLocation, Vect> coords = new HashMap<>();

		coords.put(CoordinateLocation.TOP_LEFT, expectedOrigin);
		coords.put(CoordinateLocation.TOP_RIGHT, expectedOrigin.plus(new Vect(0.5, 0)));
		coords.put(CoordinateLocation.BOTTOM_RIGHT, expectedOrigin.plus(new Vect(0.5, 2)));
		coords.put(CoordinateLocation.BOTTOM_LEFT, expectedOrigin.plus(new Vect(0, 2)));

		return coords;
	}

	private Map<CoordinateLocation, Vect> expectedCoordinatesOfFlipperAtTopOfBoundingBox() {
		Map<CoordinateLocation, Vect> coords = new HashMap<>();

		coords.put(CoordinateLocation.TOP_LEFT, expectedOrigin);
		coords.put(CoordinateLocation.TOP_RIGHT, expectedOrigin.plus(new Vect(2, 0)));
		coords.put(CoordinateLocation.BOTTOM_RIGHT, expectedOrigin.plus(new Vect(2, 0.5)));
		coords.put(CoordinateLocation.BOTTOM_LEFT, expectedOrigin.plus(new Vect(0, 0.5)));

		return coords;
	}

	private Map<CoordinateLocation, Vect> expectedCoordinatesOfFlipperAtRightOfBoundingBox() {
		Map<CoordinateLocation, Vect> coords = new HashMap<>();

		coords.put(CoordinateLocation.TOP_LEFT, expectedOrigin.plus(new Vect(1.5, 0)));
		coords.put(CoordinateLocation.TOP_RIGHT, expectedOrigin.plus(new Vect(2, 0)));
		coords.put(CoordinateLocation.BOTTOM_RIGHT, expectedOrigin.plus(new Vect(2, 2)));
		coords.put(CoordinateLocation.BOTTOM_LEFT, expectedOrigin.plus(new Vect(1.5, 2)));

		return coords;
	}

	private Map<CoordinateLocation, Vect> expectedCoordinatesOfFlipperAtBottomOfBoundingBox() {
		Map<CoordinateLocation, Vect> coords = new HashMap<>();

		coords.put(CoordinateLocation.TOP_LEFT, expectedOrigin.plus(new Vect(0, 1.5)));
		coords.put(CoordinateLocation.TOP_RIGHT, expectedOrigin.plus(new Vect(2, 1.5)));
		coords.put(CoordinateLocation.BOTTOM_RIGHT, expectedOrigin.plus(new Vect(2, 2)));
		coords.put(CoordinateLocation.BOTTOM_LEFT, expectedOrigin.plus(new Vect(0, 1.5)));

		return coords;
	}

	private List<Vect> coordinateMapToList(Map<CoordinateLocation, Vect> coordMap) {
		List<Vect> coords = new ArrayList<>();

		coords.add(coordMap.get(CoordinateLocation.TOP_LEFT));
		coords.add(coordMap.get(CoordinateLocation.TOP_RIGHT));
		coords.add(coordMap.get(CoordinateLocation.BOTTOM_RIGHT));
		coords.add(coordMap.get(CoordinateLocation.BOTTOM_LEFT));

		return coords;
	}

	private List<LineSegment> coordinateMapToLineSegments(Map<CoordinateLocation, Vect> coordinates) {
		List<LineSegment> lines = new ArrayList<>();

		lines.add(new LineSegment(coordinates.get(CoordinateLocation.TOP_LEFT),
				coordinates.get(CoordinateLocation.TOP_RIGHT)));
		lines.add(new LineSegment(coordinates.get(CoordinateLocation.TOP_RIGHT),
				coordinates.get(CoordinateLocation.BOTTOM_RIGHT)));
		lines.add(new LineSegment(coordinates.get(CoordinateLocation.BOTTOM_RIGHT),
				coordinates.get(CoordinateLocation.BOTTOM_LEFT)));
		lines.add(new LineSegment(coordinates.get(CoordinateLocation.BOTTOM_LEFT),
				coordinates.get(CoordinateLocation.TOP_LEFT)));

		return lines;
	}

	private List<Circle> coordinateMapToCircles(Map<CoordinateLocation, Vect> coordinates) {
		List<Circle> circles = new ArrayList<>();

		circles.add(new Circle(coordinates.get(CoordinateLocation.TOP_LEFT), 0));
		circles.add(new Circle(coordinates.get(CoordinateLocation.TOP_RIGHT), 0));
		circles.add(new Circle(coordinates.get(CoordinateLocation.BOTTOM_RIGHT), 0));
		circles.add(new Circle(coordinates.get(CoordinateLocation.BOTTOM_LEFT), 0));

		return circles;
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