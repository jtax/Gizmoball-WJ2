package view;

import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.HashMap;
import java.util.List;

import model.Ball;
import model.IElement;
import model.gizmos.Absorber;
import model.gizmos.Circle;
import model.gizmos.Flipper;
import model.gizmos.Square;
import model.gizmos.Triangle;
import model.gizmos.Wall;
import physics.Vect;

public class Shapifier {

	private static HashMap<Class<? extends IElement>, ShapeMaker> shapeMakerMap;

	private BoardView boardView;

	public Shapifier(BoardView boardView) {
		setBoardView(boardView);
		initialiseShapeMakerMap();
	}

	public Shape shapify(IElement e) {
		ShapeMaker shapeMaker = shapeMakerMap.get(e.getClass());
		return shapeMaker.make(e);
	}

	private void initialiseShapeMakerMap() {
		shapeMakerMap = new HashMap<>();

		shapeMakerMap.put(Absorber.class, this::makePolygon);

		shapeMakerMap.put(Circle.class, this::makeCircle);

		shapeMakerMap.put(Flipper.class, this::makePolygon);

		shapeMakerMap.put(Square.class, this::makePolygon);

		shapeMakerMap.put(Triangle.class, this::makePolygon);

		shapeMakerMap.put(Wall.class, this::makePolygon);

		shapeMakerMap.put(Ball.class, this::makeCircle);
	}

	private void setBoardView(BoardView boardView) {
		this.boardView = boardView;
	}

	private interface ShapeMaker {
		Shape make(IElement e);
	}

	private double scaleHorizontally(double value) {
		int horizontalScalingFactor = boardView.getHorizontalScalingFactor();
		return value * horizontalScalingFactor;
	}

	private double scaleVertically(double value) {
		int verticalScalingFactor = boardView.getVerticalScalingFactor();
		return value * verticalScalingFactor;
	}

	private Polygon makePolygon(IElement e) {
		List<Vect> coords = e.getCoordinates();
		Polygon p = new Polygon();
		for (Vect coord : coords) {
			p.addPoint((int) scaleHorizontally(coord.x()), (int) scaleVertically(coord.y()));
		}
		return p;
	}

	private Ellipse2D makeCircle(IElement e) {
		Vect center = e.getOrigin();
		Vect o = e.getOrigin();
		Vect b = e.getBound();

		double width = scaleHorizontally(b.x() - o.x());
		double height = scaleVertically(b.y() - o.y());

		double x = scaleHorizontally(center.x());
		double y = scaleVertically(center.y());
		return new Ellipse2D.Double(x, y, width, height);
	}
}
