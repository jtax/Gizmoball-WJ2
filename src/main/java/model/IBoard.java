package model;

import java.util.Collection;

/**
 * Created by Umar on 02/03/2016.
 */
public interface IBoard {
	void addBall(Ball ball);

	void setBalls(Collection<Ball> newBalls);

	Collection<Ball> getBalls();

	Collection<IElement> getElements();

	Collection<IElement> getAllElements();

	void setElements(Collection<IElement> elements);

	void addElement(IElement element);

	double[] getFrictionConst();

	void setFrictionConst(double[] frictionConst);

	double getGravityConst();

	void setGravityConst(double gravityConst);

	int getWidth();

	void setWidth(int width);

	int getHeight();

	void setHeight(int height);

	void changed();
}
