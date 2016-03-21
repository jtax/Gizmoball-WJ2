package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import controller.BuildListener;
import controller.KeyPressListener;
import controller.RunListener;
import model.IBoard;
import util.MagicKeyListener;
import view.buttongroups.BuildGUI;
import view.buttongroups.RunGUI;

/**
 * Gizmoball - GizmoBallView
 * Created by Group WJ2 on 06/02/2016.
 * Authors: J Baird, C Bean, N Stannage, U Akhtar, L Sakalauskas
 */
public class GizmoBallView implements Observer {

	private final MagicKeyListener keyPressListener;
	private JFrame frame;
	private Container contentPane;
	private RunGUI runGUI;
	private BuildGUI buildGUI;
	private JPanel bottomButtons;
	private JPanel topButtons;
	private JMenuBar menu;
	private BoardView boardView;
	private ActionListener runListener;
	private ActionListener buildListener;

	public GizmoBallView(IBoard bm) {
		frame = new JFrame("Gizmo Baw");
		contentPane = frame.getContentPane();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		boardView = new BoardViewImpl(bm);
		runListener = new RunListener(bm, this);
		buildListener = new BuildListener(bm, this);
		keyPressListener = new MagicKeyListener(new KeyPressListener(bm));
		makeFrame();
	}

	private void makeFrame() {
		JPanel boardPanel = boardView.getPanel();
		
		if (boardView.getMode() == Mode.RUN) {
			makeRunGUI();

			// Listen for key events
			frame.addKeyListener(keyPressListener);
		} else {
			makeBuildGUI();
			contentPane.add(topButtons, BorderLayout.NORTH);
		}
		
		contentPane.add(boardPanel, BorderLayout.CENTER);
		contentPane.add(bottomButtons, BorderLayout.SOUTH);
		frame.setJMenuBar(menu);
		frame.setLocation(100, 100);
		frame.setVisible(true);
		frame.setFocusable(true);
		frame.requestFocus();
		frame.pack();

		// frame.setResizable(false);
	}

	private void makeRunGUI() {
		runGUI = new RunGUI(runListener);
		bottomButtons = runGUI.createButton();
		menu = runGUI.createMenu();
	}

	private void makeBuildGUI() {
		buildGUI = new BuildGUI(buildListener);
		bottomButtons = buildGUI.createBottomButton();
		topButtons = buildGUI.createTopButton();
		menu = buildGUI.createMenu();
	}

	@Override
	public void update(Observable o, Object arg) {

		boardView.update(o, arg);

	}

	public JFrame getFrame(){
		return frame;
	}

	public void switchMode(){
		boardView.toggleMode();
		contentPane.removeAll();
		makeFrame();
	}

	public BuildGUI getBuildGUI(){
		return buildGUI;
	}

	public RunGUI getRunGUI(){
		return runGUI;
	}

	public void updateBoardView() {
		boardView.getPanel().repaint();
	}

	public void changeStatusMessage(String message) {
		if (boardView.getMode() == Mode.BUILD) {
			buildGUI.updateStatusBar("Build Mode: " + message);
		} else if (boardView.getMode() == Mode.RUN) {
			runGUI.updateStatusBar("Run Mode: " + message);
		} else {
			System.out.println(message);
		}
	}

	public int gizmoInfo(String gizmoInfo) {
		Object[] options = {"Remove Gizmo Connect", "Remove Key Connect", "Cancel"};
		return JOptionPane.showOptionDialog(frame, gizmoInfo, "Gizmo Information", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[2]);
	}
}
