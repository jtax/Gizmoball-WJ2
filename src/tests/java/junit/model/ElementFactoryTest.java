package junit.model;

import model.Ball;
import model.Direction;
import model.ElementFactory;
import model.IElement;
import model.gizmos.*;
import org.junit.Before;
import org.junit.Test;
import physics.Vect;

import static org.junit.Assert.*;

/**
 * Created by baird on 15/03/2016.
 */
public class ElementFactoryTest {

    ElementFactory ef;
    @Before
    public void setUp() throws Exception {
        ef = new ElementFactory();
    }

    @Test
    public void createCircle() {
        IElement test = ef.createElement("Circle", new Vect(5, 5));
        assert test instanceof Circle;
        assert test.getOrigin().equals(new Vect(5, 5));
        assert test.getName().startsWith("C");
    }

    @Test
    public void createSquare() {
        IElement test = ef.createElement("Square", new Vect(5, 5));
        assert test instanceof Square;
        assert test.getOrigin().equals(new Vect(5, 5));
        assert test.getName().startsWith("S");
    }

    @Test
    public void createTriangle() {
        IElement test = ef.createElement("Triangle", new Vect(5, 5));
        assert test instanceof Triangle;
        assert test.getOrigin().equals(new Vect(5, 5));
        assert test.getName().startsWith("T");
    }

    @Test
    public void createleftFlipper() {
        IElement test = ef.createElement("Left Flipper", new Vect(5, 5));
        assert test instanceof Flipper;
        assert test.getOrigin().equals(new Vect(5, 5));
        assertEquals(((Flipper) test).getDirection(), Direction.LEFT);
        assert test.getName().startsWith("LF");
    }

    @Test
    public void createrightFlipper() {
        IElement test = ef.createElement("Right Flipper", new Vect(5, 5));
        assert test instanceof Flipper;
        assert test.getOrigin().equals(new Vect(5, 5));
        assertEquals(((Flipper) test).getDirection(), Direction.RIGHT);
        assert test.getName().startsWith("RF");
    }


    @Test
    public void createBall() {
        IElement test = ef.createElement("Ball", new Vect(5, 5), new Vect(1, 1));
        assert test instanceof Ball;
        assert test.getOrigin().equals(new Vect(4.75, 4.75));
        assert test.getName().startsWith("B");
        assert test.getBound().equals(new Vect(5.25, 5.25));
    }

    @Test
    public void createAbsorber() {
        IElement test = ef.createElement("Absorber", new Vect(5, 5), new Vect(6, 6));
        assert test instanceof Absorber;
        assert test.getOrigin().equals(new Vect(5, 5));
        assert test.getName().startsWith("A");
        assert test.getBound().equals(new Vect(6, 6));
    }
}
