package model.Components;

import model.Component;
import model.Coordinate;

/**
 * Created by baird on 06/02/2016.
 */
public class Point  implements Component {
    Coordinate origin;
    int height,width;

    public Point(int x, int y, int diameter){
        origin = new Coordinate(x,y);
        height = diameter;
        width = diameter;
    }
}
