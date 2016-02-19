package controller;

import model.Ball;
import model.BoardManager;
import model.Gizmos.Absorber;
import model.Gizmos.Flipper;
import model.Gizmos.Square;
import model.IElement;
import view.GizmoBallView;

import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.List;

/**
 * Created by bairdjb on 11/02/2016.
 */
public class BoardController {
    private BoardManager boardManager;
    private GizmoBallView view;

    public BoardController(){
        boardManager = new BoardManager();
        view = new GizmoBallView(boardManager);
        boardManager.getBoard().addObserver(view);
        test();
    }

    //TODO Remove test method
    private void test(){
        Absorber absorber = new Absorber(0,18,20,20,"fab abs");
        Flipper flipper1 = new Flipper(5, 3, "Test");
        Flipper flipper2 = new Flipper(8, 3, "Test");

        Square square = new Square(5,7, "Test");
        square.addGizmoTrigger(flipper1);

        absorber.addKeyPressTrigger(KeyEvent.VK_SPACE);
        flipper1.addKeyPressTrigger(KeyEvent.VK_LEFT);
        flipper2.addKeyPressTrigger(KeyEvent.VK_RIGHT);

        List<IElement> testElements = Arrays.asList(new IElement[]{absorber, flipper1, flipper2});
        boardManager.getBoard().setElements(testElements);

        Ball ball = new Ball("Ball", 5, 5, 0, 0);
        boardManager.getBoard().addBall(ball);
    }


}
