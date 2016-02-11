package model;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

/**
 * Created by baird on 06/02/2016.
 */
abstract public class Gizmo implements IElement{

    protected Coordinate origin, bound;
    protected Gizmo trigger;
    private KeyEvent keyPressTrigger;
    protected Color color;
    protected Color[] colors;
    protected List<Component> components;
    protected  int rotation;
    protected  double reflection;
    protected String name;

    public Gizmo(Coordinate origin, String name){
        this.origin = origin;
        colors = new Color[]{Color.red, Color.green, Color.blue};
        rotation = 0;
        reflection = 1.0; // default is 1
        this.name = name;
    }

    protected abstract void calculateComponents();

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

    public void setKeyPressTrigger(KeyEvent keyPressTrigger){
        this.keyPressTrigger = keyPressTrigger;
    }

    public KeyEvent getKeyPressTrigger(){
        return keyPressTrigger;
    }

    public Color getColor(){
        return color;
    }

    public void setColor(Color color){
        this.color = color;
    }

    public abstract void rotate();

    public double getReflectionCoefficient(){
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
