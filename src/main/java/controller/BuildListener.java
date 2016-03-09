package controller;

import model.IBoardManager;
import model.IElement;
import physics.Vect;
import view.GizmoBallView;

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
                System.out.println("i work here, Run mode");
                gbv.switchMode();
                break;

            case "Rotate":
                if (getSelectedElement() != null) {
                    getSelectedElement().rotate();
                    gbv.updateBoardView();
                }
                else{
                    System.out.println("No Element Was Selected");
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
                    boardManager.getBoard().removeElement(getSelectedElement());
                    gbv.updateBoardView();
                }
                else{
                    System.out.println("No Element Was Selected");
                }
                break;

            case "Move":
                if (getSelectedElement() != null) {

                    getSelectedElement().move(getRelease().minus(getPress()));
                    gbv.updateBoardView();
                } else {
                    System.out.println("No Element Was Selected");
                }
                break;
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

