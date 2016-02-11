package view.BoardViews;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.Board;
import view.BoardView;
import view.Mode;

/**
 * Created by baird on 06/02/2016.
 */
public class BoardViewImpl implements BoardView, Observer {

	private Board board;
	private JPanel panel;
	private Mode mode;

	public BoardViewImpl(Board board) {
		setBoard(board);

		panel = getDefaultLayout();
		panel.setPreferredSize(new Dimension(500, 500));
		panel.setBackground(Color.WHITE);
		
		mode = Mode.BUILD;
	}
	
	private JPanel getDefaultLayout() {
		return new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);

				if (mode == Mode.BUILD)
					drawGrid((Graphics2D) g, getHorizontalScalingFactor(), getVerticalScalingFactor());
			}
		};
	}
	
	@Override
	public JPanel getPanel() {
		return panel;
	}

	@Override
	public void update(Observable o, Object arg) {
		panel.repaint();
	}
	
	public Mode getMode() {
		return mode;
	}
	
	public void setMode(Mode mode) {
		this.mode = mode;
	}
	
	/**
	 * Toggle run/build mode and return the new mode.
	 * 
	 * @return the new mode
	 */
	public Mode toggleMode() {
		return mode = ((mode == Mode.BUILD) ? Mode.RUN : Mode.BUILD);
	}

	private void setBoard(Board board) {
		this.board = board;
	}

	private void drawGrid(Graphics2D g, int gridWidth, int gridHeight) {
		for (int x = 0; x < board.getWidth(); x++)
			drawColumn(g, x, gridWidth, gridHeight);
	}

	private void drawColumn(Graphics2D g, int x, int gridWidth, int gridHeight) {
		for (int y = 0; y < board.getHeight(); y++)
			drawGridSquare(g, x, y, gridWidth, gridHeight);
	}

	private void drawGridSquare(Graphics2D g, int x, int y, int w, int h) {
		Rectangle square = new Rectangle(x * w, y * h, w, h);
		g.draw(square);
	}

	private int getHorizontalScalingFactor() {
		return panel.getWidth() / board.getWidth();
	}

	private int getVerticalScalingFactor() {
		return panel.getHeight() / board.getHeight();
	}
}
