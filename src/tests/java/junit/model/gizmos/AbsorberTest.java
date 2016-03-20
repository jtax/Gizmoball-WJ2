package junit.model.gizmos;

import model.Ball;
import model.gizmos.Absorber;
import org.junit.Before;
import org.junit.Test;
import physics.Circle;
import physics.LineSegment;
import physics.Vect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Gizmoball - Absorber Test
 * Created by Group WJ2 on 15/03/2016.
 * Authors: J Baird, C Bean, N Stannage, U Akhtar, L Sakalauskas
 */
public class AbsorberTest {
    Absorber absorber;
    @Before
    public void setUp() throws Exception {
        absorber = new Absorber(new Vect(0, 19),new Vect(20, 20), "Test");
    }

    @Test
    public void testAbsorber(){
        //See spreadsheet Test 56
        assert (absorber.getOrigin().equals(new Vect(0, 19)));
        assert (absorber.getName().equals("Test"));
        assert (absorber.getRotation() == 0);


    }

    @Test
    public void testCoordinates() throws Exception {
        //See spreadsheet Test 57
        List<LineSegment> actLines = absorber.getLines();
        LineSegment top = new LineSegment(0, 19, 20, 19);
        LineSegment right = new LineSegment(20, 19, 20, 20);
        LineSegment bottom = new LineSegment(20, 20, 0, 20);
        LineSegment left = new LineSegment(0, 20, 0, 19);
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
    public void calculateCirclesTest() {
        //See spreadsheet Test 58
        List<Vect> expected = Arrays.asList(new Vect(0, 19), new Vect(20, 19), new Vect(20, 20),new Vect(0, 20));
        List<Vect> actual = new ArrayList<>();
        for (Circle circle : absorber.getCircles()) {
            actual.add(circle.getCenter());
        }
        assert (compareLists(actual, expected));
    }

    @Test
    public void calculateLinesTest(){
        List<LineSegment> actLines = absorber.getLines();
        LineSegment top = new LineSegment(0, 19, 20, 19);
        LineSegment right = new LineSegment(20, 19, 20, 20);
        LineSegment bottom = new LineSegment(20, 20, 0, 20);
        LineSegment left = new LineSegment(0, 20, 0, 19);
        List<LineSegment> expLines = Arrays.asList(top, right, bottom, left);
        assert compareLists(expLines,actLines);
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


    @Test
    public void testRotate1() {
        //See spreadsheet Test 60

        absorber = new Absorber(new Vect(0, 9),new Vect(10, 10), "Test");

        absorber.rotate();

        List<Vect> coords = absorber.getCoordinates();

       assertEquals(coords.get(0), new Vect(5.5,4.5));
       assertEquals(coords.get(1), new Vect(5.5,14.5));
       assertEquals(coords.get(2), new Vect(4.5,14.5));
       assertEquals(coords.get(3), new Vect(4.5,4.5));
    }

    @Test
    public void testRotate2() {
        //See spreadsheet Test 61

        absorber = new Absorber(new Vect(0, 9),new Vect(10, 10), "Test");

        absorber.rotate();

        Vect expectedOrigin = new Vect(0, 9);
        Vect expectedBound = new Vect(10, 10);

        assert absorber.getOrigin().equals(expectedOrigin);
        assert absorber.getBound().equals(expectedBound);
    }

    @Test
    public void testRotate3Circles() {
        //See spreadsheet Test 62

        absorber = new Absorber(new Vect(0, 9),new Vect(10, 10), "Test");

        absorber.rotate();

        List<Vect> expected = Arrays.asList(new Vect(5.5,4.5), new Vect(5.5,14.5), new Vect(4.5,14.5), new Vect(4.5,4.5));
        List<Vect> actual = new ArrayList<>();
        for (Circle circle : absorber.getCircles()) {
            actual.add(circle.getCenter());
        }
        assert compareLists(expected, actual);
    }

    @Test
    public void testRotate3Lines() {
        //See spreadsheet Test 62
        absorber = new Absorber(new Vect(0, 9),new Vect(10, 10), "Test");

        absorber.rotate();

        Vect expTL = new Vect(5.5,4.5);
        Vect expTR = new Vect(5.5,14.5);
        Vect expBR = new Vect(4.5,14.5);
        Vect expBL = new Vect(4.5,4.5);

        LineSegment top = new LineSegment(expTL, expTR);
        LineSegment right = new LineSegment(expTR, expBR);
        LineSegment bot = new LineSegment(expBR,expBL );
        LineSegment left = new LineSegment(expBL, expTL);
        List<LineSegment> expectedLines = Arrays.asList(top, right, left,bot);
        assert compareLists(absorber.getLines(), expectedLines);
    }
    @Test
    public void testRotate4() {
        //See spreadsheet Test 63
        absorber = new Absorber(new Vect(0, 9),new Vect(10, 10), "Test");

        assertEquals(absorber.getRotation(), 0);

        absorber.rotate();
        assertEquals(absorber.getRotation(), 1);

        absorber.rotate();
        assertEquals(absorber.getRotation(), 2);

        absorber.rotate();
        assertEquals(absorber.getRotation(), 3);

        absorber.rotate();
        assertEquals(absorber.getRotation(), 0);

    }
    @Test
    public void testRotateOffBoard() {
        //See spreadsheet Test 64


        absorber.rotate();

        for (LineSegment line: absorber.getLines()) {
            assertTrue(line.p1().getXCoord() <= 20.0);
            assertTrue(line.p1().getyCoord() <= 20.0);
            assertTrue(line.p2().getXCoord() <= 20.0);
            assertTrue(line.p2().getyCoord() <= 20.0);
        }

    }

    @Test
    public void testRotationMatrix() {
        //See spreadsheet Test 65
        Vect c = absorber.rotationMatrix(new Vect(1,1), new Vect(2,2), 90);

        assertThat(c, is(new Vect(3.0, 1.0)));
    }

    @Test
    public void testGetCenterPoint() {
        //See spreadsheet Test 66


        Vect c = absorber.getCenterPoint();

        assertThat(c, is(new Vect(10.0, 19.5)));
    }

    @Test
    public void testCalculateBound() {
        //See spreadsheet Test 67


        Vect c = absorber.calculateBound();

        assertThat(c, is(new Vect(20, 20)));
    }

    @Test
    public void testAbsorbBall() {
        //See spreadsheet Test 68

        Ball b = new Ball("Test", 1, 19, 1, 1);

        assertFalse(b.isAbsorbed());

        absorber.absorb(b);

        assertTrue(b.isAbsorbed());
    }
    @Test
    public void testAbsorbBallAndCheckPosition() {
        //See spreadsheet Test 69

        Ball b = new Ball("Test", 1, 19, 1, 1);

        assertFalse(b.isAbsorbed());
        assertFalse(absorber.weHaveABall());

        absorber.absorb(b);

        assertThat(b.getCenter(), is(new Vect(19.75, 19.75)));
    }

    @Test
    public void testAbsorbAndReleaseTheBall() {
        //See spreadsheet Test 70 & 75

        Ball b = new Ball("Test", 1, 19, 1, 1);

        assertFalse(b.isAbsorbed());

        absorber.absorb(b);

        assertTrue(b.isAbsorbed());
        assertTrue(absorber.weHaveABall());

        absorber.releaseOurBall();

        assertFalse(b.isAbsorbed());
        assertFalse(absorber.weHaveABall());

        double xVelocity = 0, yVelocity = -50;

        assertEquals(b.getVelocity(), new Vect(xVelocity, yVelocity));

    }

    @Test
    public void testReleaseWithNoBall() {
        //See spreadsheet Test 71 & 75

        // Should do nothing and do not throw any exceptions
        assertFalse(absorber.weHaveABall());
        absorber.releaseOurBall();
    }

    @Test
    public void testPositionBall() {
        //See spreadsheet Test 72
        Ball b = new Ball("Test", 1, 19, 1, 1);
        absorber.absorb(b);
        absorber.positionBall();

        assertThat(b.getCenter(), is(new Vect(19.75, 19.75)));
    }

    @Test
    public void testPositionBallWithNoBall() {
        //See spreadsheet Test 73

        // Should do nothing and do not throw any exceptions
        assertFalse(absorber.weHaveABall());
        absorber.positionBall();
    }

    @Test
    public void testSaveInfo() {
        //See spreadsheet Test 74

        String save = "Absorber Test 0 19 20 20";

        assertEquals(absorber.getSaveInfo(), save);
    }

    @Test
    public void testMove() {
        //See spreadsheet Test 75
        absorber = new Absorber(new Vect(0, 9),new Vect(10, 10), "Test");

        absorber.move(new Vect(1, 1));

        assertEquals(absorber.getOrigin(), new Vect(1.0,10.0));
        assertEquals(absorber.getBound(), new Vect(11.0,11.0));
    }
}