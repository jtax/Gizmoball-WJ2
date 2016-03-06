package model.Gizmos;

import model.gizmos.Triangle;
import org.junit.Before;
import org.junit.Test;

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
    public void testRotate() throws Exception {
        Triangle old = triangle;
        System.out.println(triangle.getCoordinates());
        triangle.rotate();
        System.out.println(triangle.getCoordinates());
        triangle.rotate();
        System.out.println(triangle.getCoordinates());
        triangle.rotate();
        System.out.println(triangle.getCoordinates());
        assert (old.getCoordinates().size() == triangle.getCoordinates().size());
    }

    @Test
    public void testCalculateBound() throws Exception {

    }
}