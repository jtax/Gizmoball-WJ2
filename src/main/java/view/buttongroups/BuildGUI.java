package view.buttongroups;

import model.IElement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Gizmoball - BuildGUI
 * Created by Group WJ2 on 06/02/2016.
 * Authors: J Baird, C Bean, N Stannage, U Akhtar, L Sakalauskas
 */
public class BuildGUI {

	private JComboBox<String> shape;
	private JLabel statusBar;
	private ActionListener listener;

	public BuildGUI(ActionListener listener) {
		this.listener = listener;
		makeFrame();
	}

	private void makeFrame() {
		createTopButton();
		createBottomButton();
		createMenu();

	}

	public JPanel createBottomButton() {

		shape = new JComboBox<>();
		shape.addItem("Pick Element");
		shape.addItem("Square");
		shape.addItem("Circle");
		shape.addItem("Triangle");
		shape.addItem("Left Flipper");
		shape.addItem("Right Flipper");
		shape.addItem("Absorber");
		shape.addItem("Ball");
		JButton rotate = new JButton("Rotate");
		rotate.addActionListener(listener);
		JButton friction = new JButton("Friction");
		friction.addActionListener(listener);
		JButton gravity = new JButton("Gravity");
		gravity.addActionListener(listener);
		JButton keyConn = new JButton("Key Connection");
		keyConn.addActionListener(listener);
		JButton connGizmo = new JButton("Gizmo Connection");
		connGizmo.addActionListener(listener);

		JPanel bottomButtons = new JPanel(new GridLayout(2, 3));
		bottomButtons.add(shape);
		bottomButtons.add(rotate);
		bottomButtons.add(friction);
		bottomButtons.add(gravity);
		bottomButtons.add(keyConn);
		bottomButtons.add(connGizmo);

		statusBar = new JLabel("Build Mode");

		JPanel bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.add(bottomButtons, BorderLayout.CENTER);
		bottomPanel.add(statusBar, BorderLayout.SOUTH);


		return bottomPanel;
	}

	public JPanel createTopButton() {
		JButton add = new JButton("Add");
		add.addActionListener(listener);
		JButton switchToRun = new JButton("Run Mode");
		switchToRun.addActionListener(listener);
		JButton move = new JButton("Move");
		move.addActionListener(listener);
		JButton remove = new JButton("Remove");
		remove.addActionListener(listener);


		JPanel topButtons = new JPanel(new GridLayout(1, 6));
		topButtons.add(add);
		topButtons.add(remove);
		topButtons.add(move);
		topButtons.add(switchToRun);
		return topButtons;
	}

	public JMenuBar createMenu() {
		JMenuBar menus = new JMenuBar();

		JMenu file = new JMenu("File");
		JMenuItem loadModel = new JMenuItem("Load Board");
		loadModel.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_L, ActionEvent.CTRL_MASK));
		loadModel.addActionListener(listener);
		file.add(loadModel);

		JMenuItem saveModel = new JMenuItem("Save Board");
		saveModel.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		saveModel.addActionListener(listener);
		file.add(saveModel);

		JMenuItem remKeyConn = new JMenuItem("Remove Key Connection");
		remKeyConn.addActionListener(listener);
		file.add(remKeyConn);



		JMenuItem undo = new JMenuItem("Undo build");
		file.add(undo);

		JMenuItem redo = new JMenuItem("Redo build");
		file.add(redo);

		JMenuItem clear = new JMenuItem("Clear Board");
		clear.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		clear.addActionListener(listener);
		file.add(clear);

		JMenuItem quit = new JMenuItem("Quit");
		quit.addActionListener(listener);
		quit.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
		file.add(quit);



		menus.add(file);
		return menus;
	}

	public double promptGravity(){
		String gravVal = JOptionPane.showInputDialog("Please enter a value for gravity (numerical)");
		double gravValDouble;

		try {
			gravValDouble = Double.parseDouble(gravVal);
		}
		catch (Exception e){
			return 25.0;
		}
		return gravValDouble;
	}

	public JDialog promptSetKeyListener(IElement element){


		JDialog dialog = new JDialog();
		dialog.setTitle("Press a key to connect");
		dialog.setSize(200, 0);
		dialog.setVisible(true);
		dialog.setFocusable(true);
		dialog.requestFocus();
		JLabel label = new JLabel();
		dialog.add(label);


		return dialog;
	}

	public double[] promptFriction() {
		String frictVal1 = JOptionPane.showInputDialog("Please enter the 1st value for friction (numerical)");
		String frictVal2 = JOptionPane.showInputDialog("Please enter the 2nd value for friction (numerical)");

		double frictVal1Double;
		double frictVal2Double;


		try {
			frictVal1Double = Double.parseDouble(frictVal1);
			frictVal2Double = Double.parseDouble(frictVal2);
		}
		catch (Exception e){
			return new double[]{0.025, 0.025};
		}
		return new double[]{frictVal1Double, frictVal2Double};
	}

	public String dropboxValue(){
		return shape.getSelectedItem().toString();
	}

	public void updateStatusBar(String message) {
		statusBar.setText(message);
	}


}
