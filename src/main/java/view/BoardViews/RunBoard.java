package view.BoardViews;

import model.Board;
import model.Coordinate;
import model.Gizmos.Circle;
import view.BoardView;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.text.AttributedCharacterIterator;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by baird on 06/02/2016.
 */
public class RunBoard extends JPanel implements BoardView, Observer {

	private Board board;

	public RunBoard(Board board) {
		setBoard(board);

		this.setSize(500, 500);
		this.setBackground(Color.WHITE);
	}

	private void setBoard(Board board) {
		this.board = board;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		int horizontalScalingFactor = this.getWidth() / board.getWidth(),
				verticalScalingFactor = this.getHeight() / board.getHeight();
		
		drawGrid((Graphics2D) g, horizontalScalingFactor, verticalScalingFactor);
		drawCircle((Graphics2D) g, 0, 0, horizontalScalingFactor, verticalScalingFactor);
	}

	private void drawGrid(Graphics2D g, int gridWidth, int gridHeight) {
		for (int x = 0; x < board.getWidth(); x++) {
			drawColumn(g, x, gridWidth, gridHeight);
		}
	}
	
	private void drawColumn(Graphics2D g, int x, int gridWidth, int gridHeight) {
		for (int y = 0; y < board.getHeight(); y++) {
			Rectangle grid = new Rectangle(x * gridWidth, y * gridHeight, gridWidth, gridHeight);
			g.draw(grid);
		}
	}
	
	private void drawCircle(Graphics2D g, int x, int y, int w, int h) {
		Ellipse2D e = new Ellipse2D.Double(x, y, w, h);

		g.draw(e);
	}

	/*
	 * private void drawSomething(){ Graphics2D g2 = (Graphics2D) new
	 * Line2D.Double(10, 10, 10, 10);
	 * 
	 * // super.paint(g2); }
	 */
	@Override
	public JPanel getPanel() {
		return this;
	}

	@Override
	public void update(Observable o, Object arg) {
		repaint();
	}
}
