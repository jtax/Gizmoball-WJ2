package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import controller.BuildListener;
import controller.KeyPressListener;
import controller.RunListener;
import model.Board;
import model.BoardManager;
import model.IBoard;
import model.IBoardManager;
import util.MagicKeyListener;
import view.buttongroups.BuildGUI;
import view.buttongroups.RunGUI;

/**
 * Created by baird on 06/02/2016.
 */
public class GizmoBallView implements Observer {

	private final MagicKeyListener keyPressListener;
	private JFrame frame;
	Container contentPane;
	private RunGUI runGUI;
	private BuildGUI buildGUI;
	private JPanel bottomButtons, topButtons, boardPanel;
	private JMenuBar menu;
	private BoardView boardView;
	private IBoardManager boardManager;
	private ActionListener runListener;
	private ActionListener buildListener;

	public GizmoBallView(IBoardManager bm) {
		IBoard board = bm.getBoard();
		boardManager = bm;
		frame = new JFrame("Gizmo Baw");
		contentPane = frame.getContentPane();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		boardView = new BoardViewImpl(board);
		runListener = new RunListener(bm, this);
		buildListener = new BuildListener(bm, this);
		keyPressListener = new MagicKeyListener(new KeyPressListener(bm.getBoard().getElements()));
		makeFrame();
	}

	public void makeFrame() {
		boardPanel = boardView.getPanel();
		
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

	public void updateBoardView() {
		boardView.getPanel().repaint();
	}
}
