package view.buttongroups;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;

/**
 * Gizmoball - RunGUI
 *
 * Description: This class represents the elements of the
 * GUI that are specific to "run mode" only.
 *
 * Created by Group WJ2 on 06/02/2016.
 * Authors: J Baird, C Bean, N Stannage, U Akhtar, L Sakalauskas
 */
public class RunGUI {

	private ActionListener listener;
	private JLabel statusBar;

	/**
	 * Constructor for BuildGUI
	 * @param listener
	 */
	public RunGUI(ActionListener listener) {
		makeFrame();
		this.listener = listener;
	}

	/**
	 * Creates the frame
	 */
	private void makeFrame() {
		createButton();
		createMenu();
	}

	/**
	 * Creates the buttons the appear on the GUI.
	 * @return panel
	 */
	public JPanel createButton() {
		JButton start = new JButton("Start");
		start.addActionListener(listener);
		start.setFocusable(false);

		JButton stop = new JButton("Stop");
		stop.addActionListener(listener);
		stop.setFocusable(false);

		JButton tick = new JButton("Tick");
		tick.addActionListener(listener);
		tick.setFocusable(false);

		JButton switchMode = new JButton("Build Mode");
		switchMode.addActionListener(listener);
		switchMode.setFocusable(false);


		JPanel bottomButtons = new JPanel(new GridLayout(1, 4));

		bottomButtons.add(start);
		bottomButtons.add(stop);
		bottomButtons.add(tick);
		bottomButtons.add(switchMode);

		return bottomButtons;
	}

	/**
	 * Creates the menu for the frame.
	 * @return menu bar
	 */
	public JMenuBar createMenu() {
		JMenuBar menus = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenuItem tick = new JMenuItem("Tick");
		file.add(tick);
		JMenuItem quit = new JMenuItem("Quit");
		quit.addActionListener(listener);
		quit.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
		file.add(quit);
		menus.add(file);

		return menus;
	}

	/**
	 * Updates the status bar with a given message.
	 * @param message
	 */
	public void updateStatusBar(String message) {
		statusBar.setText(message);
	}

}
