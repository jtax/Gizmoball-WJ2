package model;

/**
 * Created by baird on 06/02/2016.
 */
public class Coordinate {
    double x, y;

    public Coordinate(double x, double y){
        setX(x);
        setY(y);
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public void setX(double x){
        this.x = x;
    }

    public void setY(double y){
        this.y = y;
    }

    public boolean equals(Coordinate coord){
        return coord.getX() == this.getX() && coord.getY() == this.getY();
    }
}
