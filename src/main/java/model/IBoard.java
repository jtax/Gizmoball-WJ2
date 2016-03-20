package model;

import physics.Vect;

import java.util.Collection;

/**
 * Gizmoball - IBoard
 * Created by Group WJ2 on 02/03/2016.
 * Authors: J Baird, C Bean, N Stannage, U Akhtar, L Sakalauskas
 */
public interface IBoard {
	void addBall(Ball ball);

	void setBalls(Collection<Ball> newBalls);

	Collection<Ball> getBalls();

	Collection<IElement> getElements();

	Collection<IElement> getAllElements();

	void setElements(Collection<IElement> elements);

	boolean addElement(IElement element);

	void removeElement(IElement element);

	double[] getFrictionConst();

	void setFrictionConst(double[] frictionConst);

	double getGravityConst();

	void setGravityConst(double gravityConst);

	int getWidth();

	void setWidth(int width);

	int getHeight();

	void setHeight(int height);

	void changed();
	void clear();

	Vect getMouseClick();

	void setMouseClick(Vect mouseClick);

	Vect getMousePress();

	void setMousePress(Vect mousePress);

	Vect getMouseRelease();

	void setMouseRelease(Vect mouseRelease);

	IElement getSelectedElement();

	void setSelectedElement(IElement selectedElement);

	boolean moveGizmo(IElement selectedElement, Vect distance);

	void tick();
	
	IElement getElementAtLocation(Vect location);
	
	IElement getElementAtLocation(double x, double y);

	void clearSelection();

	void stopHighlighting();

	void startHighlighting();

	int getNextElementID();
}
