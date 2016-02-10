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

    public void rotate(float angle) {

        angle = (float) Math.toRadians((double) angle);
        double xPoint = x; //original point
        double yPoint = y; //original point

        x = (int) (115 + Math.cos(angle) * (xPoint - 115) - Math.sin(angle) * (yPoint - 165));
        y = (int) (165 + Math.sin(angle) * (xPoint - 115) + Math.cos(angle) * (yPoint - 165));

    }

    public boolean equals(Coordinate coord){
        return coord.getX() == this.getX() && coord.getY() == this.getY();
    }
}
