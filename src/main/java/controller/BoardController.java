package controller;

import model.*;
import model.Gizmos.Circle;
import model.Gizmos.Flipper;
import model.Gizmos.Square;
import model.Gizmos.Triangle;
import view.GizmoBallView;

import java.util.Arrays;
import java.util.List;

/**
 * Created by bairdjb on 11/02/2016.
 */
public class BoardController {
    BoardManager boardManager;
    GizmoBallView view;

    public BoardController(){
        boardManager = new BoardManager();
        view = new GizmoBallView(boardManager.getBoard());
        boardManager.getBoard().addObserver(view);
        test();
    }

    //TODO Remove test method
    private void test(){
        Gizmo test1 = new Square(new Coordinate(0,0),"Test");
        Gizmo test2 = new Circle(new Coordinate(3,3),"Test");
        Gizmo test3 = new Triangle(new Coordinate(8,7), "Test");
        Gizmo test4 = new Flipper(new Coordinate(5,5), "Test");
        List<IElement> testShapes = Arrays.asList(new IElement[]{test1,test2, test3, test4});
        boardManager.getBoard().setElements(testShapes);
        Ball ball = new Ball("Ball", 10, 10, 5.0, 5.0);
        boardManager.getBoard().addBall(ball);
    }
}
