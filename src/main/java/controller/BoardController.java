package controller;

import model.*;
import model.Gizmos.Circle;
import model.Gizmos.Flipper;
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
        Gizmo test1 = new Triangle(1, 19, "Test");
        boardManager.getBoard().addElement(test1);

        Gizmo test2 = new Triangle(2, 19, "Test");
        test2.rotate();
        boardManager.getBoard().addElement(test2);

        Gizmo test3 = new Triangle(3, 19, "Test");
        test3.rotate();
        test3.rotate();
        boardManager.getBoard().addElement(test3);

        Gizmo test4 = new Triangle(4, 19, "Test");
        test4.rotate();
        test4.rotate();
        test4.rotate();
        boardManager.getBoard().addElement(test4);

        Gizmo test5 = new Triangle(5, 19, "Test");
        test5.rotate();
        test5.rotate();
        test5.rotate();
        test5.rotate();
        boardManager.getBoard().addElement(test5);

        Gizmo test6 = new Square(6, 19, "Test");
        boardManager.getBoard().addElement(test6);

        Gizmo test7 = new Flipper(new physics.Vect(7, 19), "Test");
        boardManager.getBoard().addElement(test6);

        Ball ball = new Ball("Ball", 2.5, 18, 0, 0);
        Ball ball1 = new Ball("Ball", 7, 18, 0, 0);
        boardManager.getBoard().addBall(ball);
        boardManager.getBoard().addBall(ball1);
    }


}
