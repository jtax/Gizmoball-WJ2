package junit.model.gizmos;

import model.IElement;
import model.gizmos.Circle;

import org.junit.Before;
import org.junit.Test;
import physics.LineSegment;
import physics.Vect;

import javax.sound.sampled.Line;
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
        circle = new Circle(new Vect(5, 5), "TestCircle");
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
        Vect bound = circle.calculateBound();
        assertEquals(bound, new Vect(6, 6));

    }

    @Test
    public void testLines() throws Exception {
        assert (circle.getLines().size() == 0);
    }

    @Test
    public void testCircles() throws Exception {
        assert (circle.getCircles().size() == 1);
        physics.Circle circ = circle.getCircles().get(0);
        assertEquals(circ.getCenter(), new Vect(5.5, 5.5));
        assertEquals(circ.getRadius(), 0.5, 2);
    }


}