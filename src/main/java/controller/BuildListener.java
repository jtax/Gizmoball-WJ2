package controller;

import model.Board;
import model.IBoardManager;
import model.IElement;
import model.SaveBoardToFile;
import physics.Vect;
import view.GizmoBallView;
import view.LoadBoard;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Umar on 07/03/2016.
 */
public class BuildListener implements ActionListener {

    private IBoardManager boardManager;
    private GizmoBallView gbv;

    public BuildListener(IBoardManager bm, GizmoBallView gbv) {
        System.out.println("i work!");
        this.boardManager = bm;
        this.gbv = gbv;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {

            case "Run Mode":
                gbv.changeStatusMessage("Ready");
                gbv.switchMode();
                break;

            case "Rotate":
                if (getSelectedElement() != null) {
                    getSelectedElement().rotate();
                    gbv.updateBoardView();
                    gbv.changeStatusMessage("Rotated " + getSelectedElement().getName());
                }
                else{
                    gbv.changeStatusMessage("Error: No Element Was Selected");
                }
                break;

            case "Gravity":
                double userGravityValue = gbv.getBuildGUI().promptGravity();
                System.out.println("gravity: " + userGravityValue);
                boardManager.getBoard().setGravityConst(userGravityValue);
                break;

            case "Friction":
                double[] userFrictionValue = gbv.getBuildGUI().promptFriction();
                System.out.println("friction: " + userFrictionValue);
                boardManager.getBoard().setFrictionConst(userFrictionValue);
                break;

            case "Remove":
                if (getSelectedElement() != null){
                    gbv.changeStatusMessage("Removed " + getSelectedElement().getName());
                    boardManager.getBoard().removeElement(getSelectedElement());
                    gbv.updateBoardView();
                }
                else{
                    gbv.changeStatusMessage("Error: No Element Was Selected");
                }
                break;

            case "Move":
                if (getSelectedElement() != null) {
                    Vect distance = getRelease().minus(getPress());
                    if (boardManager.getBoard().moveGizmo(getSelectedElement(), distance)) {
                        gbv.updateBoardView();
                        gbv.changeStatusMessage("Moved " + getSelectedElement().getName());
                    }
                    gbv.changeStatusMessage("Error: Invalid ");
                } else {
                    gbv.changeStatusMessage("Error: No Element Was Selected");
                }
                break;

            case "Add":
                String option = gbv.getBuildGUI().dropboxValue();
                if(!option.equals("Pick a gizmo")){
                    System.out.println("Adding the element "+ option +"\n To the coords: " + getPress());
                    // IElement elementToAdd =
                }
                break;

            case "Load Board":
                LoadBoard l = new LoadBoard();
                Board board = l.loadFile();
                if(board != null){
                boardManager.setBoard(board);
                }
                else{
                    System.out.println("failed");

                }

            case "Save Board":
                SaveBoardToFile s = new SaveBoardToFile();
                if(s.saveBoard(boardManager.getBoard())){
                    System.out.println("successful save");
                }
                else{
                    System.out.println("unsuccessful save");
                }
        }


    }

    private IElement getSelectedElement() {
        return boardManager.getBoard().getSelectedElement();
    }

    private Vect getClick() {
        return boardManager.getBoard().getMouseClick();
    }

    private Vect getPress() {
        return boardManager.getBoard().getMousePress();
    }

    private Vect getRelease() {
        return boardManager.getBoard().getMouseRelease();
    }
}

