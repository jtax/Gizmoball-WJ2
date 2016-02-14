package view;

import model.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by baird on 06/02/2016.
 */
public interface BoardView{

    public JPanel getPanel();
    public int getHorizontalScalingFactor();
	public int getVerticalScalingFactor();
    public void update(Observable o, Object arg);
}
