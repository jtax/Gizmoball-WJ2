package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by baird on 06/02/2016.
 */
abstract public class Gizmo {
    Coordinate origin, bound;
    Gizmo trigger;
    Color color;
    Color[] colors;
    List<Component> components;
    int rotation, reflection;

    public Gizmo(Coordinate origin){
        this.origin = origin;
        colors = new Color[]{Color.red, Color.green, Color.blue};
        rotation = 0;
        bound = calculateBound();
        components = calculateComponents();
    }

    protected abstract List<Component> calculateComponents();

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

    public abstract Coordinate calculateBound();

    public List<Component> getComponents(){
        return components;
    }

    public void setComponents(List<Component> components){
        this.components = components;
    }





}
