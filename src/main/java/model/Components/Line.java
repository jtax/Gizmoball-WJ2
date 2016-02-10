package model.Components;

import model.Component;
import model.Coordinate;

/**
 * Created by baird on 06/02/2016.
 */
public class Line implements Component {
    Coordinate start, end;

    public Line(double startX, double startY, double endX, double endY){
        start = new Coordinate(startX, startY);
        end = new Coordinate(endX, endY);
    }

    public Coordinate getStart() {
        return start;
    }

    public void setStart(Coordinate start) {
        this.start = start;
    }

    public Coordinate getEnd() {
        return end;
    }

    public void setEnd(Coordinate end) {
        this.end = end;
    }
}
