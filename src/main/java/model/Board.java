package model;

import java.util.List;

/**
 * Created by baird on 06/02/2016.
 */
public class Board {
    List<IElement> elements;
    int frictionConst, gravityConst;
    int width,height;

    public Board(int frictionConst, int gravityConst, int width, int height) {
        this.frictionConst = frictionConst;
        this.gravityConst = gravityConst;
        this.width = width;
        this.height = height;
    }

    public List<IElement> getElements() {
        return elements;
    }

    public void setElements(List<IElement> elements) {
        this.elements = elements;
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
