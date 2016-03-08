package view.buttongroups;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * Created by baird on 06/02/2016.
 */
public class BuildGUI {

	private JButton add, select, remove, switchToRun;
	private JComboBox<String> shape;
	private JButton absorber;
	private JButton ball;
	private JButton flipper;
	private ActionListener listener;

	public BuildGUI(ActionListener listener) {
		this.listener = listener;
		makeFrame();
	}

	public void makeFrame() {
		createTopButton();
		createBottomButton();
		createMenu();

	}

	public JPanel createBottomButton() {

		shape = new JComboBox<String>();
		shape.addItem("Pick a gizmo");
		shape.addItem("Square");
		shape.addItem("Circle");
		shape.addItem("Triangle");
		shape.addItem("Left Flipper");
		shape.addItem("Right Flipper");
		shape.addItem("Absorber");
		shape.addItem("Ball");
		JButton rotate = new JButton("Rotate");
		rotate.addActionListener(listener);
		JButton move = new JButton("Move");
		move.addActionListener(listener);
		JButton friction = new JButton("Friction");
		JButton gravity = new JButton("Gravity");
		JButton keyConn = new JButton("Key Connection");
		JButton connGizmo = new JButton("Gizmo Connection");
		ball = new JButton("Ball");

		JPanel bottomButtons = new JPanel(new GridLayout(3, 5));
		bottomButtons.add(shape);
		bottomButtons.add(rotate);
		bottomButtons.add(friction);
		bottomButtons.add(gravity);
		bottomButtons.add(keyConn);
		bottomButtons.add(connGizmo);


		return bottomButtons;
	}

	public JPanel createTopButton() {
		ImageIcon addIco = new ImageIcon("src/resources/add.png");
		add = new JButton(addIco);
		ImageIcon runIco = new ImageIcon("src/resources/play.png");
		switchToRun = new JButton(runIco);
		switchToRun.addActionListener(listener);
		ImageIcon removeIco = new ImageIcon("src/resources/delete.png");
		remove = new JButton(removeIco);
		remove.addActionListener(listener);


		JPanel topButtons = new JPanel(new GridLayout(1, 6));
		topButtons.add(add);
		topButtons.add(remove);
		topButtons.add(switchToRun);

		return topButtons;
	}

	public JMenuBar createMenu() {
		JMenuBar menus = new JMenuBar();

		JMenu file = new JMenu("File");

		JMenuItem loadModel = new JMenuItem("Load Board");
		file.add(loadModel);

		JMenuItem saveModel = new JMenuItem("Save Board");
		file.add(saveModel);

		JMenuItem undo = new JMenuItem("Undo build");
		file.add(undo);

		JMenuItem redo = new JMenuItem("Redo build");
		file.add(redo);

		JMenuItem quit = new JMenuItem("Quit");
		file.add(quit);

		menus.add(file);

		return menus;
	}
}
