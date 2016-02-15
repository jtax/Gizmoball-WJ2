package model;

import java.awt.*;
import java.util.List;

/**
 * Created by baird on 06/02/2016.
 */
abstract public class Gizmo implements IElement{

    protected Coordinate origin, bound;
    protected Gizmo trigger;
    protected Color color;
    protected Color[] colors;
    protected List<Component> components;
    protected  int rotation, reflection;
    protected String name;

    public Gizmo(Coordinate origin, String name){
        this.origin = origin;
        colors = new Color[]{Color.red, Color.green, Color.blue};
        rotation = 0;
        this.name = name;
        color = colors[0];
        
        // TODO: set the bounds correctly according to which gizmo it is
        bound = new Coordinate(origin.getX() + 1, origin.getY() + 1);
    }

    protected abstract void calculateComponents();

    public Coordinate getOrigin(){
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

    @Override
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
