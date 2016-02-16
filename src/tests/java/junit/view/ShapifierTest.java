package junit.view;

import model.Board;
import model.Gizmos.Absorber;
import model.Gizmos.Circle;
import model.Gizmos.Flipper;
import model.Gizmos.Square;
import model.IElement;
import org.junit.Before;
import org.junit.Test;
import view.BoardView;
import view.BoardViews.BoardViewImpl;
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
        board = new Board(10,10,20,20);
        boardView = new BoardViewImpl(board);
        shapifier = new Shapifier(boardView);
    }

    @Test
    public void AbsorberTest(){
        Rectangle answer = new Rectangle(5,5,1,1);
        IElement absorber = new Absorber(new Coordinate(5,5), new Coordinate(6,6), "");
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
        IElement flipper = new Flipper(new Coordinate(5.0,5.0),"");
        assertEquals(shapifier.shapify(flipper),answer);
    }
    @Test
    public void squareTest(){
        Rectangle answer = new Rectangle(5,5,1,1);
        assertEquals(shapifier.shapify(new Square(5, 5, "Square")),answer);
    }

}