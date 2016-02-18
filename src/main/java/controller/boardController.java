package controller;

import model.*;
import model.Gizmos.Absorber;
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
        IElement absorber = new Absorber(0,18,20,20,"fab abs");
        List<IElement> testShapes = Arrays.asList(new IElement[]{absorber});
        boardManager.getBoard().setElements(testShapes);
        Ball ball = new Ball("Ball", 5, 10, 0, 0);
        Ball ball1 = new Ball("Ball", 5, 5, 0, 0);
        boardManager.getBoard().addBall(ball);
    }


}
