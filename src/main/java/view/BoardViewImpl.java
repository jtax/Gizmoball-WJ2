package view;

import controller.BoardMouseListener;
import model.IBoard;
import model.IElement;
import model.gizmos.Wall;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Gizmoball - ${NAME}
 * Created by Group WJ2 on 06/02/2016.
 * Authors: J Baird, C Bean, N Stannage, U Akhtar, L Sakalauskas
 */
public class BoardViewImpl implements BoardView, Observer {

	private IBoard board;
	private final JPanel panel;
	private Mode mode;

	private List<IElement> shapes;
	private final Shapifier shapifier;


	public BoardViewImpl(IBoard board) {
		setBoard(board);

		panel = getDefaultLayout();
		panel.setPreferredSize(new Dimension(500, 500));
		panel.setBackground(new Color(0x34495e));
		panel.addMouseListener(new BoardMouseListener(this, board));

		mode = Mode.BUILD;

		shapes = new ArrayList<>();
		shapifier = new Shapifier(this);
	}

	private JPanel getDefaultLayout() {
		return new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);

				if (mode == Mode.BUILD) {
					drawGrid((Graphics2D) g);
				}

				drawElements((Graphics2D) g);
			}
		};
	}

	@Override
	public JPanel getPanel() {
		return panel;
	}

	@Override
	public void update(Observable o, Object arg) {
		IBoard board = (IBoard) o;
		shapes.clear();


		for (IElement e : board.getElements()) {
			shapes.add(e);
		}
		for (IElement e : board.getBalls()) {
			shapes.add(e);
		}

		panel.repaint();
	}

	public Mode getMode() {
		return mode;
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
		return panel.getWidth() / board.getWidth();
	}

	public int getVerticalScalingFactor() {
		return panel.getHeight() / board.getHeight();
	}

	private void setBoard(IBoard board) {
		this.board = board;
	}

	private void drawElements(Graphics2D g) {
		for (IElement s : shapes) {
			g.setColor(s.getColor());
			Shape shape = shapifier.shapify(s);
			if (s instanceof Wall) {

				g.draw(shape);
			} else {
				/*g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
						RenderingHints.VALUE_ANTIALIAS_ON);
				g.setRenderingHint(RenderingHints.KEY_RENDERING,
						RenderingHints.VALUE_RENDER_QUALITY);*/
				g.fill(shape);
			}
		}
	}

	private void drawGrid(Graphics2D g) {
		for (int x = 0; x < board.getWidth(); x++)
			drawColumn(g, x);
		g.setColor(Color.green);
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
