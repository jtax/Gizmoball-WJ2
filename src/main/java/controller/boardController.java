package controller;

import model.*;
import model.Gizmos.Circle;
import model.Gizmos.Square;
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
        view = new GizmoBallView(boardManager.getBoard());
        boardManager.getBoard().addObserver(view);
        Gizmo testSquare = new Square(new Coordinate(50.0,50.0),"Test");
        List<IElement> testShapes = Arrays.asList(new Gizmo[]{testSquare});
        boardManager.getBoard().setElements(testShapes);
    }
}
