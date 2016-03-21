package model;

import physics.Vect;

import java.util.Collection;

/**
 * 
 * 
 * Gizmoball - IBoard
 * Created by Group WJ2 on 02/03/2016.
 * @authors J Baird, C Bean, N Stannage, U Akhtar, L Sakalauskas
 */
public interface IBoard {
	/**
	 * Add the ball to the board.
	 * 
	 * @param ball
	 */
	void addBall(Ball ball);

	/**
	 * Replace all balls with the new balls.
	 * 
	 * @param newBalls
	 */
	void setBalls(Collection<Ball> newBalls);

	/**
	 * Get the collection of balls in the board.
	 * 
	 * @return all the balls
	 */
	Collection<Ball> getBalls();

	/**
	 * Get all the gizmos form the board.
	 * 
	 * @return a collection of gizmos
	 */
	Collection<IElement> getElements();

	/**
	 * Get all the gizmos and balls in the board.
	 * 
	 * @return a collection of all the gizmos and balls in the board
	 */
	Collection<IElement> getAllElements();

	/**
	 * set the elements in the board to the new elements.
	 * 
	 * @param elements
	 */
	void setElements(Collection<IElement> elements);

	/**
	 * add an element to the board
	 * 
	 * @param element the new element
	 * @return true if added successfully otherwise false
	 */
	boolean addElement(IElement element);

	/**
	 * Remove element from the board.
	 * 
	 * @param element
	 */
	void removeElement(IElement element);

	/**
	 * get the board's friction.
	 * 
	 * @return the board's friction
	 */
	double[] getFrictionConst();

	/**
	 * set the boards friction to frictionConst.
	 * 
	 * @param frictionConst the new friction
	 */
	void setFrictionConst(double[] frictionConst);

	/**
	 * Get the board's gravity.
	 * 
	 * @return the board's gravity
	 */
	double getGravityConst();

	/**
	 * set the board's gravity
	 * 
	 * @param gravityConst the board's new gravity
	 */
	void setGravityConst(double gravityConst);

	/**
	 * get the board's width
	 * 
	 * @return the board's width
	 */
	int getWidth();

	/**
	 * set the board's width
	 * 
	 * @param width the board's new width
	 */
	void setWidth(int width);

	/**
	 * get the board's height
	 * 
	 * @return the board's height
	 */
	int getHeight();

	/**
	 * set the board's height
	 * 
	 * @param height  the board's new height
	 */
	void setHeight(int height);

	/**
	 * set the board as changed
	 */
	void changed();
	
	/**
	 * clear all gizmos, elements, connections, etc from the board.
	 */
	void clear();

	/**
	 * get the location of the last "mouse click"
	 * 
	 * @return the location of the last "mouse click"
	 */
	Vect getMouseClick();

	/**
	 * set the location of the mouse click
	 * 
	 * @param mouseClick the location of the mouse click
	 */
	void setMouseClick(Vect mouseClick);
	
	/**
	 * get the location of the last mouse press 
	 * @return the location of the last mouse press 
	 */
	Vect getMousePress();

	/**
	 * set the location of the last mouse press 
	 * @param mousePress the location of the last mouse press 
	 */
	void setMousePress(Vect mousePress);

	/**
	 * get the location of the last mouse release
	 * @return the location of the last mouse release
	 */
	Vect getMouseRelease();

	/**
	 * set the location of the last mouse release
	 * @param mouseRelease the location of the last mouse release
	 */
	void setMouseRelease(Vect mouseRelease);

	/**
	 * get the currently selected element
	 * @return the currently selected element
	 */
	IElement getSelectedElement();

	/**
	 * set the selected element
	 * 
	 * @param selectedElement the newly selected element
	 */
	void setSelectedElement(IElement selectedElement);

	/**
	 * Move the gizmo selectedElement by distance
	 * 
	 * @param selectedElement the element to move
	 * @param distance the distance to move it
	 * @return true if successful, otherwise false
	 */
	boolean moveGizmo(IElement selectedElement, Vect distance);

	/**
	 * run the simulation for one tick
	 */
	void tick();
	
	/**
	 * @param location the element's location
	 * @return the element at that location
	 */
	IElement getElementAtLocation(Vect location);

	/**
	 * see getElementAtLocation(Vect location);
	 * 
	 * @param x the x location of the element's location
	 * @param y the y location of the element's location
	 * @return the element at that location
	 */
	IElement getElementAtLocation(double x, double y);

	/**
	 * unselect the currently selected element
	 */
	void clearSelection();

	/**
	 * stop overriding the gizmo's colour to show it as highlighted
	 */
	void stopHighlighting();

	/**
	 * override the gizmo's colour to show it as highlighted
	 */
	void startHighlighting();

	/**
	 * Gets the next possible ID for a gizmo
	 * 
	 * @return the next possible ID for a gizmo
	 */
	int getNextElementID();
}
