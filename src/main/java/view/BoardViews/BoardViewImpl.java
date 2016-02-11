package view.BoardViews;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.Collection;
import java.util.HashSet;
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
	private Collection<Shape> shapes;

	public BoardViewImpl(Board board) {
		setBoard(board);

		panel = getDefaultLayout();
		panel.setPreferredSize(new Dimension(500, 500));
		panel.setBackground(Color.WHITE);

		mode = Mode.BUILD;
		
		shapes = new HashSet<Shape>();

		// TODO: remove this bit of test code.
		shapes.add(new Ellipse2D.Double(0, 0, 25, 25));
	}

	private JPanel getDefaultLayout() {
		return new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);

				if (mode == Mode.BUILD)
					drawGrid((Graphics2D) g);

				drawShapes((Graphics2D) g);
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

	private void drawShapes(Graphics2D g) {
		for (Shape s : shapes)
			g.draw(s);
	}

	private void drawGrid(Graphics2D g) {
		for (int x = 0; x < board.getWidth(); x++)
			drawColumn(g, x);
	}

	private void drawColumn(Graphics2D g, int x) {
		for (int y = 0; y < board.getHeight(); y++)
			drawGridSquare(g, x, y);
	}

	private void drawGridSquare(Graphics2D g, int x, int y) {
		int width = getHorizontalScalingFactor(), height = getVerticalScalingFactor();

		Rectangle square = new Rectangle(x * width, y * height, width, height);
		g.draw(square);
	}

	private int getHorizontalScalingFactor() {
		return panel.getWidth() / board.getWidth();
	}

	private int getVerticalScalingFactor() {
		return panel.getHeight() / board.getHeight();
	}
}
