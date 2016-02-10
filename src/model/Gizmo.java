package model;

import java.awt.*;
import java.util.List;

/**
 * Created by baird on 06/02/2016.
 */
abstract public class Gizmo {
    protected Coordinate origin, bound;
    protected Gizmo trigger;
    protected Color color;
    protected Color[] colors;
    protected List<Component> components;
    protected  int rotation, reflection;

    public Gizmo(){
        colors = new Color[]{Color.red, Color.green, Color.blue};
        rotation = 0;
    }

    public Coordinate getOriginCoordinate(){
        return origin;
    }

    public Coordinate getBound(){
        return bound;
    }

    public Gizmo getTrigger(){
        return trigger;
    }

    public void setTrigger(Gizmo trigger){
        this.trigger = trigger;
    }

    public Color getColor(){
        return color;
    }

    public void setColor(Color color){
        this.color = color;
    }

    public abstract void rotate();

    public int getReflectionCoefficient(){
        return reflection;
    }

}
