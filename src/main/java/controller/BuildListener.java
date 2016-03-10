package controller;

import model.*;
import physics.Vect;
import view.GizmoBallView;
import view.LoadBoard;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                board.setGravityConst(userGravityValue);
                break;

            case "Friction":
                double[] userFrictionValue = gbv.getBuildGUI().promptFriction();
                System.out.println("friction: " + userFrictionValue);
                board.setFrictionConst(userFrictionValue);
                break;

            case "Remove":
                if (getSelectedElement() != null){
                    gbv.changeStatusMessage("Removed " + getSelectedElement().getName());
                    board.removeElement(getSelectedElement());
                    gbv.updateBoardView();
                }
                else{
                    gbv.changeStatusMessage("Error: No Element Was Selected");
                }
                break;

            case "Move":
                if (getSelectedElement() != null) {
                    Vect distance = getRelease().minus(getPress());
                    if (board.moveGizmo(getSelectedElement(), distance)) {
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
                
		case "Gizmo Connection":
			IElement firstElement;
			// FIXME: nasty casty
			if ((firstElement = getSelectedElement()) != null && firstElement instanceof Gizmo) {
				Vect secondElementLocation = board.getMouseRelease();
				IElement secondElement;
				if ((secondElement = board.getElementAtLocation(secondElementLocation)) != null && secondElement instanceof Triggerable) {
					if (!firstElement.equals(secondElement)) {
						((Gizmo) firstElement).addTriggerable((Triggerable) secondElement);
						gbv.changeStatusMessage("Success! " + secondElement + " will now be tiggered by " + firstElement + ".");
					} else {
						gbv.changeStatusMessage("Error: You can't connect a gizmo to itself.");
					}
				} else {
					gbv.changeStatusMessage("Error: Please select a second Gizmo.");
				}
			} else {
				gbv.changeStatusMessage("Error: Please select an initial Gizmo.");
			}
			break;

            case "Load Board":
                LoadBoard l = new LoadBoard();
                Board lboard = l.loadFile();
                if (lboard != null) {
                    board.setElements(lboard.getAllElements());
                    board.setFrictionConst(lboard.getFrictionConst());
                    board.setGravityConst(lboard.getGravityConst());
                    board.setHeight(lboard.getHeight());
                    board.setWidth(lboard.getWidth());
                }
                else{
                    System.out.println("failed");

                }

            case "Save Board":
                SaveBoardToFile s = new SaveBoardToFile();
                if (s.saveBoard((Board) board)) {
                    System.out.println("successful save");
                }
                else{
                    System.out.println("unsuccessful save");
                }
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

    private Vect getRelease() {
        return board.getMouseRelease();
    }
}

