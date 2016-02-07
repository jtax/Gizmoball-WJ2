package model;

import model.Components.Point;

/**
 * Created by baird on 06/02/2016.
 */
public class Ball {
    Component point;
    Coordinate velocity;

    public Ball(int x, int y, int velocityX, int velocityY, int width){
        point = new Point(x,y,width);
        velocity = new Coordinate(velocityX,velocityY);
    }
}
