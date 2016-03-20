package junit.model.gizmos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import model.Board;
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
	public void properTestFlip() {
		Flipper f = new Flipper(0,0,"");
		f.trigger();

		List<Vect> expectedCoordinates, actualCoordinates;
		Vect expectedTopLeft, expectedTopRight, expectedBottomRight, expectedBottomLeft;

		expectedTopLeft = new Vect(-0.022419999999999995, 0.024639999999999995);
		expectedTopRight = new Vect(0.47536, -0.022419999999999995);
		expectedBottomRight = new Vect(0.6635800000000001, 1.96871);
		expectedBottomLeft = new Vect(0.1658, 2.01576);

		expectedCoordinates = new ArrayList<>();
		expectedCoordinates.add(expectedTopLeft);
		expectedCoordinates.add(expectedTopRight);
		expectedCoordinates.add(expectedBottomRight);
		expectedCoordinates.add(expectedBottomLeft);

		actualCoordinates = f.getCoordinates();

		assertEquals(expectedCoordinates, actualCoordinates);


		f.flip();

		expectedTopLeft = new Vect(-0.04242000000000001, 0.05127999999999999);
		expectedTopRight = new Vect(0.44872, -0.04242000000000001);
		expectedBottomRight = new Vect(0.82349, 1.92216);
		expectedBottomLeft = new Vect(0.33235000000000003, 2.01585);

		expectedCoordinates = new ArrayList<>();
		expectedCoordinates.add(expectedTopLeft);
		expectedCoordinates.add(expectedTopRight);
		expectedCoordinates.add(expectedBottomRight);
		expectedCoordinates.add(expectedBottomLeft);

		actualCoordinates = f.getCoordinates();

		assertEquals(expectedCoordinates, actualCoordinates);


		f.flip();

		expectedTopLeft = new Vect(-0.059819999999999984, 0.07968);
		expectedTopRight = new Vect(0.42032, -0.059819999999999984);
		expectedBottomRight = new Vect(0.97831, 1.86077);
		expectedBottomLeft = new Vect(0.49817, 2.00026);

		expectedCoordinates = new ArrayList<>();
		expectedCoordinates.add(expectedTopLeft);
		expectedCoordinates.add(expectedTopRight);
		expectedCoordinates.add(expectedBottomRight);
		expectedCoordinates.add(expectedBottomLeft);

		actualCoordinates = f.getCoordinates();

		assertEquals(expectedCoordinates, actualCoordinates);


		f.flip();

		expectedTopLeft = new Vect(-0.07446999999999998, 0.10958999999999999);
		expectedTopRight = new Vect(0.39041000000000003, -0.07446999999999998);
		expectedBottomRight = new Vect(1.12666, 1.78508);
		expectedBottomLeft = new Vect(0.66178, 1.96914);

		expectedCoordinates = new ArrayList<>();
		expectedCoordinates.add(expectedTopLeft);
		expectedCoordinates.add(expectedTopRight);
		expectedCoordinates.add(expectedBottomRight);
		expectedCoordinates.add(expectedBottomLeft);

		actualCoordinates = f.getCoordinates();

		assertEquals(expectedCoordinates, actualCoordinates);


		f.flip();

		expectedTopLeft = new Vect(-0.08623999999999998, 0.14075);
		expectedTopRight = new Vect(0.35925, -0.08623999999999998);
		expectedBottomRight = new Vect(1.26723, 1.69577);
		expectedBottomLeft = new Vect(0.82174, 1.92276);

		expectedCoordinates = new ArrayList<>();
		expectedCoordinates.add(expectedTopLeft);
		expectedCoordinates.add(expectedTopRight);
		expectedCoordinates.add(expectedBottomRight);
		expectedCoordinates.add(expectedBottomLeft);

		actualCoordinates = f.getCoordinates();

		assertEquals(expectedCoordinates, actualCoordinates);


		f.flip();

		expectedTopLeft = new Vect(-0.09503, 0.17288);
		expectedTopRight = new Vect(0.32711999999999997, -0.09503);
		expectedBottomRight = new Vect(1.39877, 1.59362);
		expectedBottomLeft = new Vect(0.97662, 1.86153);

		expectedCoordinates = new ArrayList<>();
		expectedCoordinates.add(expectedTopLeft);
		expectedCoordinates.add(expectedTopRight);
		expectedCoordinates.add(expectedBottomRight);
		expectedCoordinates.add(expectedBottomLeft);

		actualCoordinates = f.getCoordinates();

		assertEquals(expectedCoordinates, actualCoordinates);


		f.flip();

		expectedTopLeft = new Vect(-0.10076000000000002, 0.20568999999999998);
		expectedTopRight = new Vect(0.29431, -0.10076000000000002);
		expectedBottomRight = new Vect(1.52012, 1.47955);
		expectedBottomLeft = new Vect(1.1250499999999999, 1.786);

		expectedCoordinates = new ArrayList<>();
		expectedCoordinates.add(expectedTopLeft);
		expectedCoordinates.add(expectedTopRight);
		expectedCoordinates.add(expectedBottomRight);
		expectedCoordinates.add(expectedBottomLeft);

		actualCoordinates = f.getCoordinates();

		assertEquals(expectedCoordinates, actualCoordinates);


		f.flip();

		expectedTopLeft = new Vect(-0.10337000000000002, 0.2389);
		expectedTopRight = new Vect(0.2611, -0.10337000000000002);
		expectedBottomRight = new Vect(1.63019, 1.35456);
		expectedBottomLeft = new Vect(1.26572, 1.69683);

		expectedCoordinates = new ArrayList<>();
		expectedCoordinates.add(expectedTopLeft);
		expectedCoordinates.add(expectedTopRight);
		expectedCoordinates.add(expectedBottomRight);
		expectedCoordinates.add(expectedBottomLeft);

		actualCoordinates = f.getCoordinates();

		assertEquals(expectedCoordinates, actualCoordinates);


		f.flip();

		expectedTopLeft = new Vect(-0.10285, 0.2722);
		expectedTopRight = new Vect(0.2278, -0.10285);
		expectedBottomRight = new Vect(1.72801, 1.21977);
		expectedBottomLeft = new Vect(1.39737, 1.59482);

		expectedCoordinates = new ArrayList<>();
		expectedCoordinates.add(expectedTopLeft);
		expectedCoordinates.add(expectedTopRight);
		expectedCoordinates.add(expectedBottomRight);
		expectedCoordinates.add(expectedBottomLeft);

		actualCoordinates = f.getCoordinates();

		assertEquals(expectedCoordinates, actualCoordinates);


		f.flip();

		expectedTopLeft = new Vect(-0.09919, 0.30530999999999997);
		expectedTopRight = new Vect(0.19469, -0.09919);
		expectedBottomRight = new Vect(1.81271, 1.07637);
		expectedBottomLeft = new Vect(1.51884, 1.48087);

		expectedCoordinates = new ArrayList<>();
		expectedCoordinates.add(expectedTopLeft);
		expectedCoordinates.add(expectedTopRight);
		expectedCoordinates.add(expectedBottomRight);
		expectedCoordinates.add(expectedBottomLeft);

		actualCoordinates = f.getCoordinates();

		assertEquals(expectedCoordinates, actualCoordinates);


		f.flip();

		expectedTopLeft = new Vect(-0.09244000000000002, 0.33793);
		expectedTopRight = new Vect(0.16207, -0.09244000000000002);
		expectedBottomRight = new Vect(1.88354, 0.92564);
		expectedBottomLeft = new Vect(1.62904, 1.356);

		expectedCoordinates = new ArrayList<>();
		expectedCoordinates.add(expectedTopLeft);
		expectedCoordinates.add(expectedTopRight);
		expectedCoordinates.add(expectedBottomRight);
		expectedCoordinates.add(expectedBottomLeft);

		actualCoordinates = f.getCoordinates();

		assertEquals(expectedCoordinates, actualCoordinates);


		f.flip();

		expectedTopLeft = new Vect(-0.08265, 0.36977);
		expectedTopRight = new Vect(0.13023, -0.08265);
		expectedBottomRight = new Vect(1.93987, 0.76891);
		expectedBottomLeft = new Vect(1.727, 1.22131);

		expectedCoordinates = new ArrayList<>();
		expectedCoordinates.add(expectedTopLeft);
		expectedCoordinates.add(expectedTopRight);
		expectedCoordinates.add(expectedBottomRight);
		expectedCoordinates.add(expectedBottomLeft);

		actualCoordinates = f.getCoordinates();

		assertEquals(expectedCoordinates, actualCoordinates);


		f.flip();

		expectedTopLeft = new Vect(-0.06990000000000002, 0.40054);
		expectedTopRight = new Vect(0.09945999999999999, -0.06990000000000002);
		expectedBottomRight = new Vect(1.9812, 0.60758);
		expectedBottomLeft = new Vect(1.81185, 1.0779999999999998);

		expectedCoordinates = new ArrayList<>();
		expectedCoordinates.add(expectedTopLeft);
		expectedCoordinates.add(expectedTopRight);
		expectedCoordinates.add(expectedBottomRight);
		expectedCoordinates.add(expectedBottomLeft);

		actualCoordinates = f.getCoordinates();

		assertEquals(expectedCoordinates, actualCoordinates);


		f.flip();

		expectedTopLeft = new Vect(-0.054310000000000025, 0.42998000000000003);
		expectedTopRight = new Vect(0.07002, -0.054310000000000025);
		expectedBottomRight = new Vect(2.00717, 0.44306999999999996);
		expectedBottomLeft = new Vect(1.88284, 0.92734);

		expectedCoordinates = new ArrayList<>();
		expectedCoordinates.add(expectedTopLeft);
		expectedCoordinates.add(expectedTopRight);
		expectedCoordinates.add(expectedBottomRight);
		expectedCoordinates.add(expectedBottomLeft);

		actualCoordinates = f.getCoordinates();

		assertEquals(expectedCoordinates, actualCoordinates);


		f.flip();

		expectedTopLeft = new Vect(-0.036019999999999996, 0.45782);
		expectedTopRight = new Vect(0.042179999999999995, -0.036019999999999996);
		expectedBottomRight = new Vect(2.01754, 0.27685);
		expectedBottomLeft = new Vect(1.93934, 0.77067);

		expectedCoordinates = new ArrayList<>();
		expectedCoordinates.add(expectedTopLeft);
		expectedCoordinates.add(expectedTopRight);
		expectedCoordinates.add(expectedBottomRight);
		expectedCoordinates.add(expectedBottomLeft);

		actualCoordinates = f.getCoordinates();

		assertEquals(expectedCoordinates, actualCoordinates);


		f.flip();

		expectedTopLeft = new Vect(-0.015189999999999981, 0.48380999999999996);
		expectedTopRight = new Vect(0.01619000000000001, -0.015189999999999981);
		expectedBottomRight = new Vect(2.01222, 0.11038999999999999);
		expectedBottomLeft = new Vect(1.98084, 0.60938);

		expectedCoordinates = new ArrayList<>();
		expectedCoordinates.add(expectedTopLeft);
		expectedCoordinates.add(expectedTopRight);
		expectedCoordinates.add(expectedBottomRight);
		expectedCoordinates.add(expectedBottomLeft);

		actualCoordinates = f.getCoordinates();

		assertEquals(expectedCoordinates, actualCoordinates);


		f.flip();

		expectedTopLeft = new Vect(1.0000000000010001E-5, 0.5);
		expectedTopRight = new Vect(0.0, 1.0000000000010001E-5);
		expectedBottomRight = new Vect(1.99998, 1.0000000000010001E-5);
		expectedBottomLeft = new Vect(1.99999, 0.49999);

		expectedCoordinates = new ArrayList<>();
		expectedCoordinates.add(expectedTopLeft);
		expectedCoordinates.add(expectedTopRight);
		expectedCoordinates.add(expectedBottomRight);
		expectedCoordinates.add(expectedBottomLeft);

		actualCoordinates = f.getCoordinates();

		assertEquals(expectedCoordinates, actualCoordinates);


		f.flip();

		expectedTopLeft = new Vect(1.0000000000010001E-5, 0.5);
		expectedTopRight = new Vect(0.0, 1.0000000000010001E-5);
		expectedBottomRight = new Vect(1.99998, 1.0000000000010001E-5);
		expectedBottomLeft = new Vect(1.99999, 0.49999);

		expectedCoordinates = new ArrayList<>();
		expectedCoordinates.add(expectedTopLeft);
		expectedCoordinates.add(expectedTopRight);
		expectedCoordinates.add(expectedBottomRight);
		expectedCoordinates.add(expectedBottomLeft);

		actualCoordinates = f.getCoordinates();

		assertEquals(expectedCoordinates, actualCoordinates);


		f.flip();
	}
	
	@Test
	public void testFlip() {
		Flipper f = new Flipper(0,0,"");
		f.trigger();

		List<Vect> expectedCoordinates, actualCoordinates;
		Vect expectedTopLeft, expectedTopRight, expectedBottomRight, expectedBottomLeft;

		for (int x = 0; x < 30; x++)
		f.flip();
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
		double expectedAngularVelocity = Board.moveTime * 1080,
				actualAngularVelocity = immutableFlipper.getAngularVelocity();
		
		assertEquals(expectedAngularVelocity, actualAngularVelocity, 0);
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
	public void testRotationMatrixPositiveAngle() {
		Flipper f = new Flipper(0,0,"doesn't matter");
		
		Vect coordinate = new Vect(1, 1);
		Vect center = new Vect(2, 2);
		double angle = 90;
		
		Vect expectedCoordinate = new Vect(3, 1);
		Vect actualCoordinate = f.rotationMatrix(coordinate, center, angle);
		
		assertEquals(expectedCoordinate, actualCoordinate);
	}
	
	@Test
	public void testRotationMatrixNegativeAngle() {
		Flipper f = new Flipper(0,0,"doesn't matter");
		
		Vect coordinate = new Vect(1, 1);
		Vect center = new Vect(2, 2);
		double angle = -90;
		
		Vect expectedCoordinate = new Vect(1, 3);
		Vect actualCoordinate = f.rotationMatrix(coordinate, center, angle);
		
		assertEquals(expectedCoordinate, actualCoordinate);
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
		String expectedSaveInfo = String.format("LeftFlipper %s %d %d", expectedName, expectedOriginX,
				expectedOriginY);
		String actualSaveInfo = immutableFlipper.getSaveInfo();

		assertEquals(expectedSaveInfo, actualSaveInfo);
	}

	@Test
	public void testGetSaveInfoRightFlipper() {
		Flipper f = new Flipper(expectedOriginX, expectedOriginY, expectedName);
		f.setDirection(Direction.RIGHT);
		
		String expectedSaveInfo = String.format("RightFlipper %s %d %d", expectedName, expectedOriginX,
				expectedOriginY);
		String actualSaveInfo = f.getSaveInfo();

		assertEquals(expectedSaveInfo, actualSaveInfo);
	}

	@Test
	public void testGetRotation() {
		Flipper f = new Flipper(0, 0, "doesn't matter");
		
		int expectedRotation, actualRotation, c = 0;
		while (c < 5) {
			expectedRotation = c % 4;
			actualRotation = f.getRotation();
			
			assertEquals(expectedRotation, actualRotation);
			
			f.rotate();
			++c;
		}
	}

	@Test
	public void testCoordinatesLeftRotate0() {
		Flipper f = new Flipper(expectedOrigin, "El Flipperino");

		List<Vect> expectedCoordinates = coordinateMapToList(expectedCoordinatesOfFlipperAtLeftOfBoundingBox());
		List<Vect> actualCoordinates = f.getCoordinates();

		assertEquals(expectedCoordinates, actualCoordinates);
	}

	@Test
	public void testCoordinatesLeftRotate1() {
		Flipper f = new Flipper(expectedOrigin, "El Flipperino");
		f.rotate();

		List<Vect> expectedCoordinates = coordinateMapToList(expectedCoordinatesOfFlipperAtTopOfBoundingBox());
		List<Vect> actualCoordinates = f.getCoordinates();

		assertEquals(expectedCoordinates, actualCoordinates);
	}

	@Test
	public void testCoordinatesLeftRotate2() {
		Flipper f = new Flipper(expectedOrigin, "El Flipperino");
		for (int c = 0; c < 2; ++c)
			f.rotate();

		List<Vect> expectedCoordinates = coordinateMapToList(expectedCoordinatesOfFlipperAtRightOfBoundingBox());
		List<Vect> actualCoordinates = f.getCoordinates();

		assertEquals(expectedCoordinates, actualCoordinates);
	}

	@Test
	public void testCoordinatesLeftRotate3() {
		Flipper f = new Flipper(expectedOrigin, "El Flipperino");
		for (int c = 0; c < 3; ++c)
			f.rotate();

		List<Vect> expectedCoordinates = coordinateMapToList(expectedCoordinatesOfFlipperAtBottomOfBoundingBox());
		List<Vect> actualCoordinates = f.getCoordinates();

		assertEquals(expectedCoordinates, actualCoordinates);
	}

	@Test
	public void testCoordinatesRightRotate0() {
		Flipper f = new Flipper(expectedOrigin, "onireppilF lE");
		f.setDirection(Direction.RIGHT);
		for (int c = 0; c < 0; ++c)
			f.rotate();

		List<Vect> expectedCoordinates = coordinateMapToList(expectedCoordinatesOfFlipperAtRightOfBoundingBox());
		List<Vect> actualCoordinates = f.getCoordinates();

		assertEquals(expectedCoordinates, actualCoordinates);
	}

	@Test
	public void testCoordinatesRightRotate1() {
		Flipper f = new Flipper(expectedOrigin, "onireppilF lE");
		f.setDirection(Direction.RIGHT);
		for (int c = 0; c < 1; ++c)
			f.rotate();

		List<Vect> expectedCoordinates = coordinateMapToList(expectedCoordinatesOfFlipperAtBottomOfBoundingBox());
		List<Vect> actualCoordinates = f.getCoordinates();

		assertEquals(expectedCoordinates, actualCoordinates);
	}

	@Test
	public void testCoordinatesRightRotate2() {
		Flipper f = new Flipper(expectedOrigin, "onireppilF lE");
		f.setDirection(Direction.RIGHT);
		for (int c = 0; c < 2; ++c)
			f.rotate();

		List<Vect> expectedCoordinates = coordinateMapToList(expectedCoordinatesOfFlipperAtLeftOfBoundingBox());
		List<Vect> actualCoordinates = f.getCoordinates();

		assertEquals(expectedCoordinates, actualCoordinates);
	}

	@Test
	public void testCoordinatesRightRotate3() {
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

		List<Circle> expectedCircles = coordinateMapToCircles(expectedCoordinatesOfFlipperAtBottomOfBoundingBox());
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
		coords.put(CoordinateLocation.BOTTOM_LEFT, expectedOrigin.plus(new Vect(0, 2)));

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
}