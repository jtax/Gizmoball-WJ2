package controller;

import model.IBoardManager;
import model.IElement;
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
                System.out.println("Rotating");
                if (getSelectedElement() != null) {
                    getSelectedElement().rotate();
                    gbv.updateBoardView();
                }
                else{
                    System.out.println("No Element Was Selected");
                }
                break;
            case "Gravity":
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
        }


    }

    private IElement getSelectedElement() {
        return boardManager.getBoard().getSelectedElement();
    }
}

