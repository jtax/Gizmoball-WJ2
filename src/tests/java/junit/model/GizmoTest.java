package junit.model;

import model.Ball;
import model.Board;
import model.gizmos.Circle;
import org.junit.Before;
import org.junit.Test;
import physics.Vect;

import java.awt.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by baird on 15/03/2016.
 */
public class GizmoTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testSubHandle() throws Exception {
        //See spreadsheet Test 144

        Board board = new Board();
        Ball ball = new Ball("", 5.5, 4.746, 0, 1);
        Circle s = new Circle(new Vect(5,5),"t");

        board.addBall(ball);
        board.addElement(s);

        s.handle(board.getTimeTillCollision(ball));

        assertThat(ball.getVelocity(), not(new Vect(0,1)));
        assertThat(ball.getCenter(), not(new Vect(5.5, 4.746)));
    }

    @Test
    public void testTrigger() throws Exception {
        //See spreadsheet Test 145

        Board board = new Board();
        Ball ball = new Ball("", 5.5, 4.746, 0, 1);
        Circle s = new Circle(new Vect(5,5),"t");

        Color oldColor = s.getColor();

        board.addBall(ball);
        board.addElement(s);


        s.trigger();

        assertThat(s.getColor(), not(oldColor));
    }
    @Test
    public void testOnCollision() throws Exception {
        //See spreadsheet Test 146

        Board board = new Board();
        Ball ball = new Ball("", 5.5, 4.746, 0, 1);

        Circle s = new Circle(new Vect(5,5),"t");
        Circle s2 = new Circle(new Vect(10,5),"t");

        s.gizmoConnect(s2);
        Color oldColor = s2.getColor();

        board.addBall(ball);
        board.addElement(s);
        board.addElement(s2);

        s.handle(board.getTimeTillCollision(ball));

        assertThat(s2.getColor(), not(oldColor));
    }
}