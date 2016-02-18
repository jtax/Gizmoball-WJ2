package model;

import model.Gizmos.Square;
import model.Gizmos.Wall;
import physics.Vect;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by baird on 06/02/2016.
 */
public class Board extends Observable {
    private List<IElement> elements;
    private List<Ball> balls;
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
        IElement walls = new Wall(topLeft, bottomRight, "Wall");
        addElement(walls);
    }

    public void setBalls(List<Ball> newBalls) {
        balls = newBalls;
        setChanged();
        notifyObservers();
    }

    public List<Ball> getBalls() {
        return balls;
    }
    public List<IElement> getElements() {
        return elements;
    }

    public void setElements(List<IElement> elements) {
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

}
