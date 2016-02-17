package model.Gizmos;

import model.Direction;
import org.junit.Test;
import physics.Vect;

import static org.junit.Assert.assertTrue;

/**
 * Package: model.Gizmos
 * Created by Laurynas Sakalauskas on 15/02/16 16:19.
 * Project: Gizmoball-WJ2
 */
public class FlipperTest {

    @Test
    public void testFlipLeftDirection() throws Exception {

        Flipper test = new Flipper(new Vect(0,0), "Test Left");

        System.out.println("Before X:" + test.getBound().x() + ", Y:" + test.getBound().y() );
        test.flip();
        System.out.println("After X:" + test.getBound().x() + ", Y:" + test.getBound().y() );

        assertTrue(test.getBound().equals(new Vect(-1, 1)));

        test.flip();

        System.out.println("Flip back X:" + test.getBound().x() + ", Y:" + test.getBound().y() );

        assertTrue(test.getBound().equals(new Vect(1, 1)));

    }
    @Test
    public void testFlipRightDirection() throws Exception {

        Flipper test = new Flipper(new Vect(0,0),"Test Right");
        test.setDirection(Direction.RIGHT);

        System.out.println("Before X:" + test.getBound().x() + ", Y:" + test.getBound().y() );
        test.flip();
        System.out.println("After X:" + test.getBound().x() + ", Y:" + test.getBound().y() );

        assertTrue(test.getBound().equals(new Vect(1, -1)));

        test.flip();

        System.out.println("Flip back X:" + test.getBound().x() + ", Y:" + test.getBound().y() );
        assertTrue(test.getBound().equals(new Vect(1, 1)));

    }
}