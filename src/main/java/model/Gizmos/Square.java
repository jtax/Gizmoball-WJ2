package model.Gizmos;

import model.Gizmo;
import physics.Vect;

import java.util.Arrays;

/**
 * Created by baird on 06/02/2016.
 */
public class Square extends Gizmo {

    private int reflectionCoefficient = 1;

    public Square(Vect origin, String name) {
        super(origin, name);
        calculateComponents();
    }

    public Square(int x, int y, String name) {
        this(new Vect(x, y), name);
    }

	@Override
    protected void calculateComponents() {
        Vect origin = super.getOrigin();
        double x = origin.x();
        double y = origin.y();
        Component top = new Line(x,y, x+1,y);
        Component right = new Line(x+1,y, x+1,y-1);
        Component bottom = new Line(x,y-1, x+1,y-1);
        Component left = new Line(x,y, x,y-1);
        super.setComponents(Arrays.asList(top,right,bottom,left));
    }

    public void rotate(){
        //Pointless for Square: do nothing
    }

    public Vect calculateBound() {
        Vect origin = super.getOrigin();
        Vect bound = new Vect(1, -1);
        return origin.plus(bound);
    }
}