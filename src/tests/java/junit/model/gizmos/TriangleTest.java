package junit.model.gizmos;


import org.junit.Before;
import org.junit.Test;
import physics.Circle;
import physics.*;
import model.gizmos.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by baird on 16/02/2016.
 */
public class TriangleTest {
    Triangle triangle;

    @Before
    public void setUp() throws Exception {
        triangle = new Triangle(5, 5, "Test");
    }

    @Test
    public void triangleTest() {
        //See spreadsheet Test 92
        assert (triangle.getOrigin().equals(new Vect(5, 5)));
        assert (triangle.getName().equals("Test"));
        assert (triangle.getRotation() == 0);
    }

    @Test
    public void calculateCoordinatesTest() {
        //See spreadsheet Test 93
        List<Vect> expected = Arrays.asList(new Vect(5, 5), new Vect(5, 6), new Vect(6, 5));
        assert (compareLists(triangle.getCoordinates(), expected));
    }

    @Test
    public void calculateCirclesTest() {
        //See spreadsheet Test 94
        List<Vect> expected = Arrays.asList(new Vect(5, 5), new Vect(5, 6), new Vect(6, 5));
        List<Vect> actual = new ArrayList<>();
        for (Circle circle : triangle.getCircles()) {
            actual.add(circle.getCenter());
        }
        assert (compareLists(actual, expected));
    }

    @Test
    public void getCenterTest() {
        //See spreadsheet Test 95
        Vect expected = new Vect(5.5, 5.5);
        assert (triangle.getCenterPoint().equals(expected));
    }

    @Test
    public void calcLinesTest() {
        //See spreadsheet Test 96
        Vect expTL = new Vect(5, 5);
        Vect expBR = new Vect(6, 5);
        Vect expBL = new Vect(5, 6);
        List<LineSegment> expected = Arrays.asList(new LineSegment(expTL, expBR), new LineSegment(expBR, expBL), new LineSegment(expBL, expTL));
        assert compareLists(triangle.getLines(), expected);
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
}