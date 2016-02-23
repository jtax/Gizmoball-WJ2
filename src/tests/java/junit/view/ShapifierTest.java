package junit.view;

import model.Board;
import model.IElement;
import model.gizmos.Absorber;
import model.gizmos.Circle;
import model.gizmos.Flipper;
import model.gizmos.Square;

import org.junit.Before;
import org.junit.Test;
import physics.Vect;
import view.BoardView;
import view.BoardViewImpl;
import view.Shapifier;

import java.awt.*;
import java.awt.geom.Ellipse2D;

import static org.junit.Assert.*;

/**
 * Created by baird on 14/02/2016.
 */
public class ShapifierTest {
    Shapifier shapifier;
    BoardView boardView;
    Board board;

    @Before
    public void createTestShapifier(){
        board = new Board(new double[]{0.025, 0.025}, 25, 20, 20);
        boardView = new BoardViewImpl(board);
        shapifier = new Shapifier(boardView);
    }

    @Test
    public void AbsorberTest(){
        Rectangle answer = new Rectangle(5,5,1,1);
        IElement absorber = new Absorber(new Vect(5, 5), new Vect(6, 6), "");
        assertEquals(shapifier.shapify(absorber),answer);
    }
    @Test
    public void circleTest(){
        Ellipse2D answer = new Ellipse2D.Double(4.5,4.5,1,1);
        IElement circle = new Circle(4,4,"");
        assertEquals(shapifier.shapify(circle),answer);
    }
    @Test
    public void flipperTest(){
        Rectangle answer = new Rectangle(5,5,1,1);
        IElement flipper = new Flipper(new Vect(5.0, 5.0), "");
        assertEquals(shapifier.shapify(flipper),answer);
    }
    @Test
    public void squareTest(){
        Rectangle answer = new Rectangle(5,5,1,1);
        assertEquals(shapifier.shapify(new Square(5, 5, "Square")),answer);
    }

}