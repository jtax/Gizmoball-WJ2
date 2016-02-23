package view;

import java.util.Observable;

import javax.swing.JPanel;

/**
 * Created by baird on 06/02/2016.
 */
public interface BoardView{

    JPanel getPanel();

    int getHorizontalScalingFactor();

    int getVerticalScalingFactor();

    void update(Observable o, Object arg);
}