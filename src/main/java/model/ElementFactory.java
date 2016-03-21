package model;

import model.gizmos.*;
import physics.Vect;

/**
 * Gizmoball - ElementFactory
 * Description: This class acts as an element creating factory, given
 * a string and the required origin and bound methods will create
 * the required element needed by the caller.
 *
 * Created by Group WJ2 on 10/03/2016.
 * Authors: J Baird, C Bean, N Stannage, U Akhtar, L Sakalauskas
 */
public class ElementFactory {
    private int index;

    /**
     * Constructor for Element Factory.
     */
    public ElementFactory(){
        index = 0;
    }

    /**
     * Constructor for Element Factory
     * @param startingValue
     */
    public ElementFactory(int startingValue) {
        index = startingValue;
    }

    /**
     * Creates the required element given an element name,
     * for an element with just an origin and returns it
     * @param type (of element)
     * @param origin (of element)
     * @return element that's been created
     */
    public IElement createElement(String type, Vect origin){
        index++;
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

    /**
     * Creates the required element given an element a name,
     * origin and bound and returns it.
     * @param type
     * @param origin
     * @param bound
     * @return element that's been created
     */
    public IElement createElement(String type, Vect origin, Vect bound) {
        index++;
        switch (type) {
            case "Absorber":
                return createAbsorber(origin, bound);
            case "Ball":
                return createBall(origin, bound);
        }
        return null;
    }

    /**
     * Creates a ball and returns it.
     * @param origin
     * @param bound
     * @return the ball element
     */
    private IElement createBall(Vect origin, Vect bound) {
        index++;
        String name = "B" + index;
        return new Ball(name, origin, bound);
    }

    /**
     * Creates an absorber and returns it.
     * @param origin
     * @param bound
     * @return the created absorber element.
     */
    private IElement createAbsorber(Vect origin, Vect bound) {
        index++;
        String name = "A" + index;
        return new Absorber(origin, bound, name);
    }

    /**
     * Creates a flipper with the given direction and returns it.
     * @param origin
     * @param d (direction)
     * @return the created flipper element.
     */
    private IElement createFlipper(Vect origin, Direction d) {
        index ++;
        String name = (d==Direction.LEFT?"LF":"RF") + index;
        Flipper f = new Flipper(origin, name);
        f.setDirection(d);
        return f;
    }

    /**
     * Creates a triangle and returns it.
     * @param origin
     * @return the created triangle element.
     */
    private IElement createTriangle(Vect origin) {
        index ++;
        String name = "T" + index;
        return new Triangle(origin, name);
    }

    /**
     * Creates a square and returns it.
     * @param origin
     * @return the created square element.
     */
    private IElement createSquare(Vect origin) {
        index ++;
        String name = "S" + index;
        return new Square(origin, name);
    }

    /**
     * Creates a circle and returns it.
     * @param origin
     * @return the created circle element.
     */
    private IElement createCircle(Vect origin) {
        index ++;
        String name = "C" + index;
        return new Circle(origin, name);
    }
}
