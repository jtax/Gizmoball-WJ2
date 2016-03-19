package model;

import model.gizmos.*;
import physics.Vect;

/**
 * Gizmoball - ElementFactory
 * Created by Group WJ2 on 10/03/2016.
 * Authors: J Baird, C Bean, N Stannage, U Akhtar, L Sakalauskas
 */
public class ElementFactory {
    private int index;
    public ElementFactory(){
        index = 0;
    }

    public IElement createElement(String type, Vect origin){
        switch(type){
            case "Circle":
                return createCircle(origin);
            case "Square":
                return createSquare(origin);
            case "Triangle":
                return createTriangle(origin);
            case "Left Flipper":
                return createFlipper(origin, Direction.LEFT);
            case "Right Flipper":
                return createFlipper(origin, Direction.RIGHT);
        }
        return null;
    }

    public IElement createElement(String type, Vect origin, Vect bound) {
        switch (type) {
            case "Absorber":
                return createAbsorber(origin, bound);
            case "Ball":
                return createBall(origin, bound);
        }
        return null;
    }

    private IElement createBall(Vect origin, Vect bound) {
        index++;
        String name = "B" + index;
        return new Ball(name, origin, bound);
    }

    private IElement createAbsorber(Vect origin, Vect bound) {
        index++;
        String name = "A" + index;
        return new Absorber(origin, bound, name);
    }

    private IElement createFlipper(Vect origin, Direction d) {
        index ++;
        String name = (d==Direction.LEFT?"LF":"RF") + index;
        Flipper f = new Flipper(origin, name);
        f.setDirection(d);
        return f;
    }

    private IElement createTriangle(Vect origin) {
        index ++;
        String name = "T" + index;
        return new Triangle(origin, name);
    }

    private IElement createSquare(Vect origin) {
        index ++;
        String name = "S" + index;
        return new Square(origin, name);
    }

    private IElement createCircle(Vect origin) {
        index ++;
        String name = "C" + index;
        return new Circle(origin, name);
    }
}
