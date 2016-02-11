package view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by baird on 06/02/2016.
 */
public interface BoardView {

    public JPanel getPanel();

    public void paintElement(Shape shape);

}
