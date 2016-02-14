package model;

/**
 * Created by baird on 14/02/2016.
 */
public class Collision {
    private double time;
    private Coordinate velocity;

    public Collision(Coordinate velocity, double time) {
        this.velocity = velocity;
        this.time = time;
    }

    public Collision(double x, double y, double time) {
        this.velocity = new Coordinate(x, y);
        this.time = time;
    }

    public Coordinate getVelocity() {
        return velocity;
    }

    public void setVelocity(Coordinate velocity) {
        this.velocity = velocity;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }
}
