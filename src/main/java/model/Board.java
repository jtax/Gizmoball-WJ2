package model;

import model.Gizmos.Wall;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by baird on 06/02/2016.
 */
public class Board extends Observable {
    List<IElement> elements;
    List<Ball> balls;
    int frictionConst, gravityConst;
    int width,height;

    public Board(int frictionConst, int gravityConst, int width, int height) {
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

    public void addWalls() {
        Coordinate topLeft = new Coordinate(0, 0);
        Coordinate topRight = new Coordinate(20, 0);
        Coordinate bottomLeft = new Coordinate(0, 20);
        Coordinate bottomRight = new Coordinate(20, 20);
        IElement top = new Wall(topLeft, topRight, "WallTop");
        IElement right = new Wall(topRight, bottomRight, "WallRight");
        IElement bottom = new Wall(bottomLeft, bottomRight, "WallBottom");
        IElement left = new Wall(topLeft, bottomLeft, "WallLeft");
        addElement(top);
        addElement(right);
        addElement(bottom);
        addElement(left);
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

    public int getFrictionConst() {
        return frictionConst;
    }

    public void setFrictionConst(int frictionConst) {
        this.frictionConst = frictionConst;
    }

    public int getGravityConst() {
        return gravityConst;
    }

    public void setGravityConst(int gravityConst) {
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
