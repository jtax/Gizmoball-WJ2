package controller;

import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.List;

import model.Ball;
import model.Board;
import model.BoardManager;
import model.Direction;
import model.Gizmo;
import model.gizmos.Absorber;
import model.gizmos.Flipper;
import model.gizmos.Square;
import view.GizmoBallView;
import view.LoadBoard;

/**
 * Created by bairdjb on 11/02/2016.
 */
public class BoardController {
    private BoardManager boardManager;
    private GizmoBallView view;

    public BoardController() {
        LoadBoard l = new LoadBoard();
        Board board = l.loadFile();

        if(board != null) {
            boardManager = new BoardManager();
            boardManager.setBoard(board);
            view = new GizmoBallView(boardManager);
            boardManager.getBoard().addObserver(view);
            //test();
            //test();
            // Ball ball = new Ball("Ball", 2.5, 15, -5.0, -5.0);
            //boardManager.getBoard().addBall(ball);
            boardManager.tick();
        }
        else{
            System.out.println("File reader closed");
            boardManager = new BoardManager();
            view = new GizmoBallView(boardManager);

        }
    }

    //TODO Remove test method
    private void test(){
        Absorber absorber = new Absorber(0,18,20,20,"fab abs");
        Flipper flipper1 = new Flipper(5, 3, "Test");
        Flipper flipper2 = new Flipper(8, 3, "Test");
        flipper2.setDirection(Direction.RIGHT);

        Square square = new Square(5,7, "Test");
        square.addGizmoTrigger(flipper1);

        absorber.addKeyPressTrigger(KeyEvent.VK_SPACE);
        flipper1.addKeyPressTrigger(KeyEvent.VK_LEFT);
        flipper2.addKeyPressTrigger(KeyEvent.VK_RIGHT);

        List<Gizmo> testElements = Arrays.asList(new Gizmo[]{absorber, flipper1, flipper2});
        boardManager.getBoard().setElements(testElements);

        Ball ball = new Ball("Ball", 5, 5, 0, 0);
        boardManager.getBoard().addBall(ball);
    }


}
