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
public interface BoardView {

	JPanel getPanel();

	int getHorizontalScalingFactor();

	int getVerticalScalingFactor();

	void update(Observable o, Object arg);
}