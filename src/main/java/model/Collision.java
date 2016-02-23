package model;

import physics.Vect;

/**
 * Created by baird on 14/02/2016.
 */
public class Collision {
    private double time;
    private Vect velocity;
    private IElement collisionElement;


    public Collision(Vect velocity, double time, IElement element) {
        this.velocity = velocity;
        this.time = time;
        this.collisionElement = element;
    }

    public Collision(Vect velocity, double time) {
        this.velocity = velocity;
        this.time = time;
    }

    public Collision(double x, double y, double time, IElement element) {
        this(new Vect(x, y), time, element);
    }


    public Collision(double x, double y, double time) {
        this(new Vect(x, y), time);
    }

    public Vect getVelocity() {
        return velocity;
    }

    public double getTime() {
        return time;
    }

    public IElement getElement() {
        return collisionElement;
    }

}
