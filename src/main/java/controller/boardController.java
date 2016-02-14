package controller;

import model.*;
import model.Gizmos.Circle;
import model.Gizmos.Square;
import model.Gizmos.Triangle;
import view.GizmoBallView;

import java.util.Arrays;
import java.util.List;

/**
 * Created by bairdjb on 11/02/2016.
 */
public class boardController {
    BoardManager boardManager;
    GizmoBallView view;

    public boardController(){
        boardManager = new BoardManager();
        view = new GizmoBallView(boardManager);
        boardManager.getBoard().addObserver(view);
        test();
    }

    //TODO Remove test method
    private void test(){
        Gizmo test1 = new Square(new Coordinate(0,0),"Test");
        Gizmo test2 = new Circle(new Coordinate(3,3),"Test");
        Gizmo test3 = new Triangle(new Coordinate(4, 3), "Test");
        List<IElement> testShapes = Arrays.asList(new IElement[]{test1, test2, test3});
        boardManager.getBoard().setElements(testShapes);
        Ball ball = new Ball("Ball", 6.6, 9.9, 5.0, 5.0);
        Ball ball2 = new Ball("Ball", 5.4, 3.9, -2.0, 3.0);
        boardManager.getBoard().addBall(ball);
        boardManager.getBoard().addBall(ball2);
    }


}
