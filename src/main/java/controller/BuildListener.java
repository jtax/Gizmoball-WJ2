package controller;

import model.*;
import physics.Vect;
import view.GizmoBallView;
import view.LoadBoard;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Umar on 07/03/2016.
 */
public class BuildListener implements ActionListener {

    private IBoard board;
    private GizmoBallView gbv;

    public BuildListener(IBoard b, GizmoBallView gbv) {
        System.out.println("i work!");
        this.board = b;
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
                rotateElement();
                break;

            case "Gravity":
                double userGravityValue = gbv.getBuildGUI().promptGravity();
                System.out.println("gravity: " + userGravityValue);
                board.setGravityConst(userGravityValue);
                break;

            case "Friction":
                double[] userFrictionValue = gbv.getBuildGUI().promptFriction();
                System.out.println("friction: " + userFrictionValue);
                board.setFrictionConst(userFrictionValue);
                break;

            case "Remove":
                removeElement();
                break;

            case "Move":
                moveElement();
                break;

            case "Add":
                addElement();
                break;

            case "Gizmo Connection":
                setGizmoConnection();
                break;

            case "Key Connection":
                setKeyConnection();
                break;

            case "Load Board":
                loadBoard();
                break;

            case "Save Board":
                saveBoard();
                break;

            case "Clear Board":
                board.clear();
                break;
        }
    }

    private IElement getSelectedElement() {
        return board.getSelectedElement();
    }

    private Vect getClick() {
        return board.getMouseClick();
    }

    private Vect getPress() {
        return board.getMousePress();
    }

    private Vect snapToGrid(Vect coord) {
        double x = Math.round(coord.x());
        double y = Math.round(coord.y());
        return new Vect(x, y);
    }

    private Vect getRelease() {
        return board.getMouseRelease();
    }

    private void rotateElement(){
        if (getSelectedElement() != null) {
            getSelectedElement().rotate();
            gbv.updateBoardView();
            gbv.changeStatusMessage("Rotated " + getSelectedElement().getName());
        }
        else{
            gbv.changeStatusMessage("Error: No Element Was Selected");
        }
    }

    private void removeElement(){
        if (getSelectedElement() != null){
            gbv.changeStatusMessage("Removed " + getSelectedElement().getName());
            board.removeElement(getSelectedElement());
            gbv.updateBoardView();
        }
        else{
            gbv.changeStatusMessage("Error: No Element Was Selected");
        }
    }

    private void moveElement(){
        if (getSelectedElement() != null) {
            Vect distance = getRelease().minus(getPress());
            double x = Math.round(distance.x());
            double y = Math.round(distance.y());
            distance = new Vect(x, y);
            if (board.moveGizmo(getSelectedElement(), distance)) {
                gbv.updateBoardView();
                gbv.changeStatusMessage("Moved " + getSelectedElement().getName());
            }
            gbv.changeStatusMessage("Error: Invalid ");
        } else {
            gbv.changeStatusMessage("Error: No Element Was Selected");
        }
    }

    private void loadBoard(){
        LoadBoard l = new LoadBoard();
        Board lboard = l.loadFile();
        if (lboard != null) {
            board.clear();
            board.setElements(lboard.getElements());
            board.setFrictionConst(lboard.getFrictionConst());
            board.setGravityConst(lboard.getGravityConst());
            board.setHeight(lboard.getHeight());
            board.setWidth(lboard.getWidth());
            board.setBalls(lboard.getBalls());
        }
        else{
            System.out.println("failed");

        }
    }

    private void saveBoard(){
        SaveBoardToFile s = new SaveBoardToFile();
        if (s.saveBoard((Board) board)) {
            System.out.println("successful save");
        }
        else{
            System.out.println("unsuccessful save");
        }
    }

    private void addElement(){
        String option = gbv.getBuildGUI().dropboxValue();
        if(!option.equals("Pick a gizmo")){
            System.out.println("Adding the element "+ option +"\n To the coords: " + getPress());
            ElementFactory ef = new ElementFactory();
            IElement e = null;
            switch (option) {
                case "Absorber":
                    e = ef.createElement(option, snapToGrid(getPress()), snapToGrid(getRelease()));
                    break;
                case "Ball":
                    e = ef.createElement(option, getPress(), new Vect(0.5,0.5));
                    break;
                default:
                    e = ef.createElement(option, snapToGrid(getPress()));
            }

            if(e.getName().matches("[B]\\d+")){
                board.addBall((Ball)e);
                gbv.changeStatusMessage(e.getName() + " was added to" + e.getOrigin().toString());
            }
            else if (board.addElement(e)){
                gbv.changeStatusMessage(e.getName() + " was added to" + e.getOrigin().toString());
            }
            else{
                gbv.changeStatusMessage("Error: Add gizmo failed.");
            }
        }
    }


    private void setKeyConnection() {
        IElement selectedElement;

        if ((selectedElement = getSelectedElement()) != null && selectedElement instanceof Gizmo) {

            JDialog dialog = gbv.getBuildGUI().promptSetKeyListener(selectedElement);

            dialog.addKeyListener(new KeyListener() {
                @Override
                public void keyPressed(KeyEvent e) {

                    // Close dialog
                    dialog.dispose();

                    // Set trigger
                    selectedElement.addKeyConnect(e.getKeyCode());

                    // Change status
                    gbv.changeStatusMessage("Success! " + selectedElement + " will be triggered by pressing " + KeyEvent.getKeyText(e.getKeyCode()));
                }

                public void keyTyped(KeyEvent e) {}
                public void keyReleased(KeyEvent e) {}
            });


        } else {
            gbv.changeStatusMessage("Error: Please select a Gizmo.");
        }
    }

    private void setGizmoConnection() {
        IElement firstElement;
        // FIXME: nasty casty
        if ((firstElement = getSelectedElement()) != null && firstElement instanceof Gizmo) {
            Vect secondElementLocation = board.getMouseRelease();
            IElement secondElement;
            if ((secondElement = board.getElementAtLocation(secondElementLocation)) != null && secondElement instanceof Triggerable) {
                if (!firstElement.equals(secondElement)) {
                     firstElement.gizmoConnect(secondElement);
                    gbv.changeStatusMessage("Success! " + secondElement + " will now be triggered by " + firstElement + ".");
                } else {
                    gbv.changeStatusMessage("Error: You can't connect a gizmo to itself.");
                }
            } else {
                gbv.changeStatusMessage("Error: Please select a second Gizmo.");
            }
        } else {
            gbv.changeStatusMessage("Error: Please select an initial Gizmo.");
        }
    }
}

