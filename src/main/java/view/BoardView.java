package view;

import java.util.Observable;

import javax.swing.JPanel;

/**
 * Gizmoball - BoardView
 *
 * Description: Board View is the interface for the board, here
 * the board will update according to the model.
 *
 * Created by Group WJ2 on 06/02/2016.
 * Authors: J Baird, C Bean, N Stannage, U Akhtar, L Sakalauskas
 */
public interface BoardView {

	/**
	 * Returns the board panel
	 * @return panel
     */
	JPanel getPanel();

	/**
	 * Returns the horizontal scale factor
	 * @return horizontal scale factor
     */
	int getHorizontalScalingFactor();

	/**
	 * Returns the vertical scale factor
	 * @return vertical scale factor
	 */
	int getVerticalScalingFactor();

	/**
	 * Update taken from the observer
	 * @param o
	 * @param arg
     */
	void update(Observable o, Object arg);

	/**
	 * Returns the mode the game is in
	 * @return mode
     */
	Mode getMode();

	/**
	 * Swaps between current and the other mode
	 * for e.g. build to run.
	 * @return
     */
	Mode toggleMode();
}