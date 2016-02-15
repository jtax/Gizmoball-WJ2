package model.Gizmos;

import model.Coordinate;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Package: model.Gizmos
 * Created by Laurynas Sakalauskas on 15/02/16 16:19.
 * Project: Gizmoball-WJ2
 */
public class FlipperTest {

    @Test
    public void testFlip() throws Exception {

        Flipper test = new Flipper(new Coordinate(0,0),"Test");

        System.out.println("Before X:" + test.getBound().getX() + ", Y:" + test.getBound().getY() );
        test.flip();
        System.out.println("After X:" + test.getBound().getX() + ", Y:" + test.getBound().getY() );

        assertTrue(test.getBound().equals(new Coordinate(1, -1)));

        test.flip();

        System.out.println("Flip back X:" + test.getBound().getX() + ", Y:" + test.getBound().getY() );
        assertTrue(test.getBound().equals(new Coordinate(1, 1)));

    }
}