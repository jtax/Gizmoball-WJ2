package view.buttongroups;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 * Created by baird on 06/02/2016.
 */
public class RunGUI {

	private ActionListener listener;

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

		JButton exit = new JButton("Exit");
		exit.addActionListener(listener);
		exit.setFocusable(false);

		JPanel bottomButtons = new JPanel(new GridLayout(1, 5));

		bottomButtons.add(start);
		bottomButtons.add(stop);
		bottomButtons.add(tick);
		bottomButtons.add(switchMode);
		bottomButtons.add(exit);
		return bottomButtons;
	}

	public JMenuBar createMenu() {
		JMenuBar menus = new JMenuBar();

		JMenu file = new JMenu("File");

		JMenuItem tick = new JMenuItem("Tick");
		file.add(tick);

		JMenuItem quit = new JMenuItem("Quit");
		file.add(quit);

		menus.add(file);

		return menus;
	}
}
