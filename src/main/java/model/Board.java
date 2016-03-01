package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Observable;

import model.gizmos.Wall;
import physics.Vect;

/**
 * Created by baird on 06/02/2016.
 */
public class Board extends Observable {
    private Collection<IElement> elements;
    private Collection<Ball> balls;
    private double[] frictionConst;
    private double gravityConst;
    private int width;
    private int height;
    
    private static Board soleInstance;

    public Board(double[] frictionConst, double gravityConst, int width, int height) {
    	if (soleInstance != null) {
    		System.err.println("Multiple boards instantiated!");
    	} else {
    		System.out.println("First board instantiated.");
    	}
    	
        this.frictionConst = frictionConst;
        this.gravityConst = gravityConst;
        this.width = width;
        this.height = height;
        elements = new ArrayList<>();
        balls = new ArrayList<>();
        addWalls();
        
        soleInstance = this;
    }

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

    public void setBalls(Collection<Ball> newBalls) {
        balls = newBalls;
        setChanged();
        notifyObservers();
    }

    public Collection<Ball> getBalls() {
        return balls;
    }

    public Collection<IElement> getElements() {
        return elements;
    }
    
    public Collection<IElement> getAllElements() {
    	Collection<IElement> allElements = new ArrayList<>();
    	allElements.addAll(elements);
    	allElements.addAll(balls);
    	return allElements;
    }

    public void setElements(Collection<IElement> elements) {
        for (IElement element : elements) {
            addElement(element);
        }
        addWalls();
        setChanged();
        notifyObservers();
    }

    public void addElement(IElement element) {
        elements.add(element);
        setChanged();
        notifyObservers();
    }

    public double[] getFrictionConst() {
        return frictionConst;
    }

    public void setFrictionConst(double[] frictionConst) {
        this.frictionConst = frictionConst;
    }

    public double getGravityConst() {
        return gravityConst;
    }

    public void setGravityConst(double gravityConst) {
        this.gravityConst = gravityConst;
    }

    public int getWidth() {
        return width;
    }


    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
    public void changed() {
    	setChanged();
    	notifyObservers();
    }

}
