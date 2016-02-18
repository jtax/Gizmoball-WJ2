package model;

import physics.Vect;

/**
 * Created by baird on 14/02/2016.
 */
public class Collision {
    private double time;
    private Vect velocity;
    private IElement collidingElement;

    public Collision(Vect velocity, double time, IElement collidingElement) {
        this.velocity = velocity;
        this.time = time;
        this.collidingElement = collidingElement;
    }

    public Collision(double x, double y, double time) {
        this.velocity = new Vect(x, y);
        this.time = time;
    }

    public Vect getVelocity() {
        return velocity;
    }

    public void setVelocity(Vect velocity) {
        this.velocity = velocity;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }
    
    public IElement getCollidingElement() {
    	return collidingElement;
    }
}
