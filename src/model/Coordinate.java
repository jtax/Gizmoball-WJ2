package model;

/**
 * Created by baird on 06/02/2016.
 */
public class Coordinate {
    int x, y;

    public Coordinate(int x, int y){
        setX(x);
        setY(y);
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
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
