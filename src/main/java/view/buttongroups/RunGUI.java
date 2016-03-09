package view.buttongroups;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;

/**
 * Created by baird on 06/02/2016.
 */
public class RunGUI {

	private ActionListener listener;
	private JLabel statusBar;

	public RunGUI(ActionListener listener) {
		makeFrame();
		this.listener = listener;
	}

	private void makeFrame() {
		createButton();
		createMenu();
	}

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

	public JMenuBar createMenu() {
		JMenuBar menus = new JMenuBar();

		JMenu file = new JMenu("File");

		file.setBackground(new Color(0x2C3E50));
		file.setForeground(new Color(0xECF0F1));

		JMenuItem tick = new JMenuItem("Tick");
		file.add(tick);

		JMenuItem quit = new JMenuItem("Exit");
		quit.addActionListener(listener);
		file.add(quit);

		menus.add(file);

		menus.setBackground(new Color(0x2C3E50));
		menus.setForeground(new Color(0xECF0F1));

		return menus;
	}

	public void updateStatusBar(String message) {
		statusBar.setText(message);
	}

	public JPanel getStatusBar() {
		JPanel status = new JPanel(new GridLayout(1, 1));
		statusBar = new JLabel("Run Mode");
		status.add(statusBar);
		return status;
	}
}
