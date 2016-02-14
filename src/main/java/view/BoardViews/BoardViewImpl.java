package view.BoardViews;

import java.awt.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.Board;
import model.Gizmos.Square;
import model.IElement;
import model.Gizmos.Circle;
import view.BoardView;
import view.Mode;
import view.Shapifier;

/**
 * Created by baird on 06/02/2016.
 */
public class BoardViewImpl implements BoardView, Observer {

	private Board board;
	private JPanel panel;
	private Mode mode;
	private Collection<Shape> shapes;
	private Shapifier shapifier;

	public BoardViewImpl(Board board) {
		setBoard(board);

		panel = getDefaultLayout();
		panel.setPreferredSize(new Dimension(500, 500));
		panel.setBackground(Color.white);

		mode = Mode.BUILD;

		shapes = new HashSet<Shape>();

		shapifier = new Shapifier(this);

		// TODO: remove this test code can't test here. The panel scaling isnt ready yet
		//shapes.add(shapifier.shapify(new Square(1, 0, "Your Mother")));
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
		Board board = (Board) o;

		// TODO: uncomment this
		// shapes.clear();

		for (IElement e : board.getElements()) {
			Shape s = shapifier.shapify(e);
			shapes.add(s);
		}

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

	public int getHorizontalScalingFactor() {
		int panelWidth = panel.getWidth();
		int boardWidth = board.getWidth();
		return panel.getWidth() / board.getWidth();
	}

	public int getVerticalScalingFactor() {
		return panel.getHeight() / board.getHeight();
	}

	private void setBoard(Board board) {
		this.board = board;
	}

	private void drawShapes(Graphics2D g) {
		for (Shape s : shapes) {
			g.setColor(Color.BLUE);
			g.fill(s);
		}
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
}
