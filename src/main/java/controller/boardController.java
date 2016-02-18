package controller;

import java.util.Arrays;
import java.util.List;

import model.Ball;
import model.BoardManager;
import model.Gizmo;
import model.IElement;
import model.Gizmos.Absorber;
import model.Gizmos.Circle;
import model.Gizmos.Square;
import model.Gizmos.Triangle;
import physics.Vect;
import view.GizmoBallView;

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
    	Gizmo test1 = new Square(new Vect(0,0),"Test");
        Gizmo test2 = new Circle(new Vect(3,3),"Test");
        Gizmo test3 = new Triangle(new Vect(8,7), "Test");
        Gizmo absorber = new Absorber(0, 18, 20, 20, "Your Mother");
        List<IElement> testShapes = Arrays.asList(new IElement[]{test1,test2, test3, absorber});
        boardManager.getBoard().setElements(testShapes);

        Ball ball = new Ball("Ball", 1, 10, 1, 5);
        Ball ball1 = new Ball("Ball", 5, 17, 0, -5);
        boardManager.getBoard().addBall(ball);
        //boardManager.getBoard().addBall(ball1);
    }


}
