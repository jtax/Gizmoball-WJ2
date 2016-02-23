package controller;

import model.*;
import model.Gizmos.*;
import view.GizmoBallView;
import view.LoadBoard;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.List;

/**
 * Created by bairdjb on 11/02/2016.
 */
public class BoardController {
    private BoardManager boardManager;
    private GizmoBallView view;

    public BoardController() {
        LoadBoard l = new LoadBoard();
        Board board = l.loadFile();
        System.out.println(board.getElements().toString());

        boardManager = new BoardManager();
        boardManager.setBoard(board);

        view = new GizmoBallView(boardManager);
        boardManager.getBoard().addObserver(view);
        //test();
        //test();
        Ball ball = new Ball("Ball", 2.5, 15, -5.0, -5.0);
        boardManager.getBoard().addBall(ball);
        boardManager.tick();
    }


    private void test(){
        Gizmo test1 = new Square(3, 0, "Test");
        Gizmo test2 = new Triangle(2, 6, "Test");
        Gizmo test3 = new Square(1, 6, "Test");
        Gizmo test4 = new Square(1, 2, "Test");
        Gizmo test5 = new Circle(2, 1, "Test");
        Flipper test6 = new Flipper(5, 3, "Test");
        Flipper test7 = new Flipper(8, 3, "Test");
        test7.setDirection(Direction.RIGHT);
        test6.addKeyPressTrigger(KeyEvent.VK_LEFT);
        test7.addKeyPressTrigger(KeyEvent.VK_RIGHT);
        test7.trigger();
        List<Gizmo> testShapes = Arrays.asList(new Gizmo[]{test1, test2, test3, test4, test5, test6, test7});
        boardManager.getBoard().setElements(testShapes);
        Ball ball = new Ball("Ball", 3.5, 7, -5.0, -5.0);

        Ball ball2 = new Ball("Ball", 2.5, 3, -5.0, -5.0);
        boardManager.getBoard().addBall(ball);
        boardManager.getBoard().addBall(ball2);
    }

}
