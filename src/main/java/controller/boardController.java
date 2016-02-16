package controller;

import model.*;
import model.Gizmos.Circle;
import model.Gizmos.Square;
import model.Gizmos.Triangle;
import view.GizmoBallView;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

/**
 * Created by bairdjb on 11/02/2016.
 */
public class boardController {
    private BoardManager boardManager;
    private GizmoBallView view;

    public boardController(){
        boardManager = new BoardManager();
        view = new GizmoBallView(boardManager);
        boardManager.getBoard().addObserver(view);
        test();
    }

    //TODO Remove test method
    private void test(){
        Gizmo test1 = new Square(1, 6, "Test");
        Gizmo test2 = new Square(2, 6, "Test");
        Gizmo test3 = new Square(3, 6, "Test");
        Gizmo test4 = new Square(1, 2, "Test");
        Gizmo test5 = new Square(2, 1, "Test");
        Gizmo test6 = new Square(3, 2, "Test");
        //test3.setColor(Color.GREEN);
        List<IElement> testShapes = Arrays.asList(new IElement[]{test1, test2, test3, test4, test5, test6});
        boardManager.getBoard().setElements(testShapes);
        Ball ball = new Ball("Ball", 2.5, 3, .0, 5.0);
        boardManager.getBoard().addBall(ball);
    }


}
