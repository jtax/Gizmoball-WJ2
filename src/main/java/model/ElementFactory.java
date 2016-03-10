package model;

import model.gizmos.*;
import physics.Vect;

import javax.lang.model.element.Element;

/**
 * Created by Umar on 10/03/2016.
 */
public class ElementFactory {
    int index;
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
        Ball b = new Ball(name, origin, bound);
        return b;
    }

    private IElement createAbsorber(Vect origin, Vect bound) {
        index++;
        String name = "A" + index;
        Absorber a = new Absorber(origin, bound, name);
        return a;
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
        Triangle t = new Triangle(origin, name);
        return t;
    }

    private IElement createSquare(Vect origin) {
        index ++;
        String name = "S" + index;
        Square s = new Square(origin, name);
        return s;
    }

    private IElement createCircle(Vect origin) {
        index ++;
        String name = "C" + index;
        Circle c = new Circle(origin, name);
        return c;
    }
}
