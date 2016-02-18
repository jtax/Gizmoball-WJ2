package view;

import model.Ball;
import model.Gizmos.*;
import model.IElement;
import physics.Vect;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.util.HashMap;

public class Shapifier {

	private static HashMap<Class<? extends IElement>, ShapeMaker> shapeMakerMap;

	private BoardView boardView;

	public Shapifier(BoardView boardView) {
		setBoardView(boardView);
		
		initialiseShapeMakerMap();
	}

	public Shape shapify(IElement e) {
		ShapeMaker shapeMaker = shapeMakerMap.get(e.getClass());

		int horizontalScalingFactor = boardView.getHorizontalScalingFactor();
		int verticalScalingFactor = boardView.getVerticalScalingFactor();

		Vect originCoordinate = e.getOrigin();

		double originX = originCoordinate.x() * horizontalScalingFactor;
		double originY = originCoordinate.y() * verticalScalingFactor;

		Vect boundingCoordinate = e.getBound();
		double boundX = boundingCoordinate.x() * horizontalScalingFactor;
		double boundY = boundingCoordinate.y() * verticalScalingFactor;


		double width = (boundX - originX);
		double height = (boundY - originY);

		Shape shape = shapeMaker.make((int) originX, (int) originY, (int) width, (int) height);
		
		return shape;
	}

	private void initialiseShapeMakerMap() {
		shapeMakerMap = new HashMap<>();

		shapeMakerMap.put(Absorber.class, (int x, int y, int w, int h) -> {
			return new Rectangle(x, y, w, h);
		});

		shapeMakerMap.put(Circle.class, (int x, int y, int w, int h) -> {
			return new Ellipse2D.Double(x, y, w, h);
		});

		// TODO: does this one actually work?
		shapeMakerMap.put(Flipper.class, (int x, int y, int w, int h) -> {
 			return new RoundRectangle2D.Double(x, y, w, h, 15, 10);
		});

		shapeMakerMap.put(Square.class, (int x, int y, int w, int h) -> {
			return new Rectangle(x, y, w, h);
		});

		shapeMakerMap.put(Triangle.class, (int x, int y, int w, int h) -> {
			// TODO: return a triangle

			Polygon p = new Polygon();
			p.addPoint(x, y);
			p.addPoint(x, y + h);
			p.addPoint(x + w, y + h);
			return p;
		});

		shapeMakerMap.put(Wall.class, (int x, int y, int w, int h) -> {
			return new Rectangle(x, y, w, h);
		});

		shapeMakerMap.put(Ball.class, (int x, int y, int w, int h) -> {
			return new Ellipse2D.Double(x, y, w, h);
		});
	}

	private void setBoardView(BoardView boardView) {
		this.boardView = boardView;
	}

	private interface ShapeMaker {
		Shape make(int x, int y, int w, int h);
	}
}
