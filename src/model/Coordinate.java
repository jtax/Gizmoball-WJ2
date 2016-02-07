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

    public boolean equals(Coordinate coord){
        return coord.getX() == this.getX() && coord.getY() == this.getY();
    }
}
