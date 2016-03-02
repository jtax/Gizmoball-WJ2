package view.buttongroups;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 * Created by baird on 06/02/2016.
 */
public class BuildGUI {

	private JButton add, select, remove, switchToRun;
	private JComboBox<String> shape;
	private JButton absorber;
	private JButton ball;
	private JButton flipper;

	public BuildGUI() {
		makeFrame();
	}

	public void makeFrame() {
		createTopButton();
		createBottomButton();
		createMenu();

	}

	public JPanel createBottomButton() {
		switchToRun = new JButton("Run Mode");
		shape = new JComboBox<String>();
		shape.addItem("Pick a gizmo");
		shape.addItem("Square");
		shape.addItem("Circle");
		shape.addItem("Triangle");
		shape.addItem("Left Flipper");
		shape.addItem("Right Flipper");
		shape.addItem("Absorber");
		JButton rotate = new JButton("Rotate");
		JButton move = new JButton("Move");
		JButton friction = new JButton("Friction");
		JButton gravity = new JButton("Gravity");
		JButton keyConn = new JButton("Key Connection");
		JButton connGizmo = new JButton("Gizmo Connection");
		ball = new JButton("Ball");

		JPanel bottomButtons = new JPanel(new GridLayout(3, 5));
		bottomButtons.add(switchToRun);
		bottomButtons.add(shape);
		bottomButtons.add(ball);
		bottomButtons.add(rotate);
		bottomButtons.add(move);
		bottomButtons.add(friction);
		bottomButtons.add(gravity);
		bottomButtons.add(keyConn);
		bottomButtons.add(connGizmo);

		return bottomButtons;
	}

	public JPanel createTopButton() {
		add = new JButton("Add");
		select = new JButton("Select");
		remove = new JButton("Remove");

		JPanel topButtons = new JPanel(new GridLayout(1, 3));
		topButtons.add(add);
		topButtons.add(select);
		topButtons.add(remove);

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
