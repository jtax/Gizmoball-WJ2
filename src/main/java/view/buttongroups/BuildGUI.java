package view.buttongroups;

import java.awt.*;
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
		friction.addActionListener(listener);
		JButton gravity = new JButton("Gravity");
		gravity.addActionListener(listener);
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
		add = new JButton("Add");
		switchToRun = new JButton("Run Mode");
		switchToRun.addActionListener(listener);
		remove = new JButton("Remove");
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
		file.setBackground(new Color(0x2C3E50));
		file.setForeground(new Color(0xECF0F1));

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
		menus.setBackground(new Color(0x2C3E50));
		menus.setForeground(new Color(0xECF0F1));
		return menus;
	}

	public double promptGravity(){
		String gravVal = JOptionPane.showInputDialog("Please enter a value for gravity (numerical)");
		double gravValDouble = 0.0;

		try {
			gravValDouble = Double.parseDouble(gravVal);
		}
		catch (Exception e){
			return 25.0;
		}
		return gravValDouble;
	}

	public double[] promptFriction() {
		String frictVal1 = JOptionPane.showInputDialog("Please enter the 1st value for friction (numerical)");
		String frictVal2 = JOptionPane.showInputDialog("Please enter the 2nd value for friction (numerical)");

		double frictVal1Double = 0.0;
		double frictVal2Double = 0.0;


		try {
			frictVal1Double = Double.parseDouble(frictVal1);
			frictVal2Double = Double.parseDouble(frictVal2);
		}
		catch (Exception e){
			return new double[]{0.025, 0.025};
		}
		return new double[]{frictVal1Double, frictVal2Double};
	}
}
