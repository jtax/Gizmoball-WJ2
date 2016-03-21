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
/**
 * Gizmoball - Shapifier
 *
 * Description: This class will convert the model implementation
 * of the shapes(gizmos) to the view implementation.
 *
 * Created by Group WJ2 on 06/02/2016.
 * Authors: J Baird, C Bean, N Stannage, U Akhtar, L Sakalauskas
 */
public class Shapifier {

	private static HashMap<Class<? extends IElement>, ShapeMaker> shapeMakerMap;

	private BoardView boardView;

	/**
	 * Constructor for shapifier
	 * @param boardView
     */
	public Shapifier(BoardView boardView) {
		setBoardView(boardView);
		initialiseShapeMakerMap();
	}

	/**
	 * Shapify the element given
	 * @param e
	 * @return a shape to be made
     */
	public Shape shapify(IElement e) {
		ShapeMaker shapeMaker = shapeMakerMap.get(e.getClass());
		return shapeMaker.make(e);
	}

	/**
	 * Initialise the shape maker hashmap
	 */
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

	/**
	 * Set the board view to the correct board view.
	 * @param boardView
     */
	private void setBoardView(BoardView boardView) {
		this.boardView = boardView;
	}

	/**
	 * Sets the horizontal value of the shape
	 * @param value
	 * @return horizontal scale
     */
	private double scaleHorizontally(double value) {
		int horizontalScalingFactor = boardView.getHorizontalScalingFactor();
		return value * horizontalScalingFactor;
	}

	/**
	 * Sets the vertical value of the shape
	 * @param value
	 * @return vertical scale
	 */
	private double scaleVertically(double value) {
		int verticalScalingFactor = boardView.getVerticalScalingFactor();
		return value * verticalScalingFactor;
	}

	/**
	 * Makes a polygon from the element given
	 * @param e
	 * @return the created polygon
     */
	private Polygon makePolygon(IElement e) {
		List<Vect> coords = e.getCoordinates();
		Polygon p = new Polygon();
		for (Vect coord : coords) {
			p.addPoint((int) scaleHorizontally(coord.x()), (int) scaleVertically(coord.y()));
		}
		return p;
	}

	/**
	 * Makes a circle from the element given
	 * @param e
	 * @return the created circle
	 */
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

	/**
	 * Defines there is such a thing as a shape method.
	 */
	private interface ShapeMaker {
		Shape make(IElement e);
	}
}
