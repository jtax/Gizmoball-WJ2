package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Observable;

import model.gizmos.Wall;
import physics.Vect;

/**
 * Created by baird on 06/02/2016.
 */
public class Board extends Observable implements IBoard {
    private Collection<IElement> elements;
    private Collection<Ball> balls;
    private double[] frictionConst;
    private double gravityConst;
    private int width;
    private int height;

    public Board(double[] frictionConst, double gravityConst, int width, int height) {
        this.frictionConst = frictionConst;
        this.gravityConst = gravityConst;
        this.width = width;
        this.height = height;
        elements = new ArrayList<>();
        balls = new ArrayList<>();
        addWalls();
    }

    @Override
    public void addBall(Ball ball) {
        balls.add(ball);
        setChanged();
        notifyObservers();
    }

    private void addWalls() {
        Vect topLeft = new Vect(0, 0);
        Vect topRight = new Vect(20, 0);
        Vect bottomLeft = new Vect(0, 20);
        Vect bottomRight = new Vect(20, 20);
        Gizmo walls = new Wall(topLeft, bottomRight, "Wall");
        addElement(walls);
    }

    @Override
    public void setBalls(Collection<Ball> newBalls) {
        balls = newBalls;
        setChanged();
        notifyObservers();
    }

    @Override
    public Collection<Ball> getBalls() {
        return balls;
    }

    @Override
    public Collection<IElement> getElements() {
        return elements;
    }
    
    @Override
    public Collection<IElement> getAllElements() {
    	Collection<IElement> allElements = new ArrayList<>();
    	allElements.addAll(elements);
    	allElements.addAll(balls);
    	return allElements;
    }

    @Override
    public void setElements(Collection<IElement> elements) {
        for (IElement element : elements) {
            addElement(element);
        }
        addWalls();
        setChanged();
        notifyObservers();
    }

    @Override
    public void addElement(IElement element) {
        elements.add(element);
        setChanged();
        notifyObservers();
    }

    @Override
    public double[] getFrictionConst() {
        return frictionConst;
    }

    @Override
    public void setFrictionConst(double[] frictionConst) {
        this.frictionConst = frictionConst;
    }

    @Override
    public double getGravityConst() {
        return gravityConst;
    }

    @Override
    public void setGravityConst(double gravityConst) {
        this.gravityConst = gravityConst;
    }

    @Override
    public int getWidth() {
        return width;
    }


    @Override
    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
    }
    
    @Override
    public void changed() {
    	setChanged();
    	notifyObservers();
    }

}
