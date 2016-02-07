package model.Components;

import model.Component;
import model.Coordinate;

/**
 * Created by baird on 06/02/2016.
 */
public class Line implements Component {
    Coordinate start, end;

    public Line(int startX, int startY, int endX, int endY){
        start = new Coordinate(startX, startY);
        end = new Coordinate(endX, endY);
    }
}
