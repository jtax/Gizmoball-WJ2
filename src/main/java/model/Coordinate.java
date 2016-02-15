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

    public void rotate(Coordinate centerCoord, float angle) {

        angle = (float) (angle * Math.PI / 180);

        double xPoint = x; //original point
        double yPoint = y; //original point

        x = Math.round(centerCoord.getX() + Math.cos(angle) * (xPoint - centerCoord.getX()) + Math.sin(angle) * (yPoint - centerCoord.getY()));
        y = Math.round(centerCoord.getX() + -Math.sin(angle) * (xPoint - centerCoord.getX()) + Math.cos(angle) * (yPoint - centerCoord.getY()));

    }

    public boolean equals(Coordinate coord){
        return Double.compare(coord.getX(), this.getX()) == 0 && Double.compare(coord.getY(), this.getY()) == 0;
    }
}
