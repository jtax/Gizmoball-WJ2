package view;

import java.util.Observable;

import javax.swing.JPanel;

/**
 * Gizmoball - BoardView
 * Created by Group WJ2 on 06/02/2016.
 * Authors: J Baird, C Bean, N Stannage, U Akhtar, L Sakalauskas
 */
public interface BoardView {

	JPanel getPanel();

	int getHorizontalScalingFactor();

	int getVerticalScalingFactor();

	void update(Observable o, Object arg);
	
	Mode getMode();
	
	Mode toggleMode();
}