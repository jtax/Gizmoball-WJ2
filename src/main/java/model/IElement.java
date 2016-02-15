package model;

import java.util.List;

/**
 * Created by baird on 06/02/2016.
 */
public interface IElement {

    public List<Component> getComponents();
    
    public Coordinate getOrigin();
    
    public Coordinate getBound();
}
