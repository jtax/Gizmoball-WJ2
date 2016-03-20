package controller;

import model.*;
import model.gizmos.Absorber;
import physics.Vect;
import view.GizmoBallView;
import view.LoadBoard;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.List;

/**
 * Gizmoball - Build Listener
 * Created by Group WJ2 on 07/03/2016.
 * Authors: J Baird, C Bean, N Stannage, U Akhtar, L Sakalauskas
 */
public class BuildListener implements ActionListener {

    private final IBoard board;
    private final GizmoBallView gbv;

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
                board.clearSelection();
                board.stopHighlighting();
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
                System.out.println("friction: " + Arrays.toString(userFrictionValue));
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
            case "Info":
                showInfo();
                break;
            case "Quit" :
                quit();
                break;
        }
    }

    private void showInfo() {
        IElement elem = getSelectedElement();
        String name = elem.getName();
        double posx = elem.getOrigin().x();
        double posy = elem.getOrigin().y();
        List<String> gizmos = elem.getConnections();
        String gizmoString = "";
        for (String gizmo : gizmos) {
            String clean = gizmo.replace("Connect ", "");
            gizmoString += "[" + clean + "] ";
        }
        List<String> keys = elem.returnKeyConnects();
        String keyString = "";
        for (String key : keys) {
            String clean = key.replace("KeyConnect ", "");
            keyString += "[" + clean + "] ";
        }
        String output = "Name: " + name + "\nPosition X:" + posx + " Y:" + posy + "\nGizmo Connections:" + gizmoString + "\nKey Connections:" + keyString;
        int response = gbv.gizmoInfo(output); //0 = Remove Gizmos, 1 = Remove Keys, 2 = Cancel
        if (response == 0) {
            elem.clearConnections();
            gbv.changeStatusMessage("Removed Gizmo Connections for " + elem.getName());
        } else if (response == 1) {
            elem.clearKeyConnections();
            gbv.changeStatusMessage("Removed Key Connections for " + elem.getName());
        }
    }

    private IElement getSelectedElement() {
        return board.getSelectedElement();
    }


    private Vect getPress() {
        return board.getMousePress();
    }

    private Vect snapToGrid(Vect coord) {
        double x = Math.floor(coord.x());
        double y = Math.floor(coord.y());
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
            gbv.changeStatusMessage("File Could Not Be Loaded");
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
        if(!option.equals("Pick Element") && getPress() !=null){
            System.out.println("Adding the element "+ option +"\n To the coords: " + getPress());
            ElementFactory ef = new ElementFactory(board.getNextElementID());
            IElement e;
            switch (option) {
                case "Absorber":
                    e = ef.createElement(option, snapToGrid(getPress()), snapToGrid(getRelease()));
                    break;
                case "Ball":
                    if (board.getBalls().size() > 0) {
                        gbv.changeStatusMessage("Only One Ball at a time please.");
                        return;
                    } else {
                        Vect userVectVal = gbv.getBuildGUI().promptVelocity();
                        e = ef.createElement(option, getPress(), userVectVal);
                    }
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
        else gbv.changeStatusMessage("Error: Add gizmo failed.");
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
                if (!firstElement.equals(secondElement) || firstElement instanceof Absorber) {
                     firstElement.gizmoConnect(secondElement);
                    gbv.changeStatusMessage("Success! " + secondElement.getName() + " will now be triggered by " + firstElement.getName() + ".");
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

    private void quit(){
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure you want to quit","Warning",dialogButton);
        if(dialogResult == JOptionPane.YES_OPTION){
            System.exit(0);
        }

    }
}

