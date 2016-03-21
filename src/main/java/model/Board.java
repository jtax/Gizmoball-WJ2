package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Observable;

import model.gizmos.Absorber;
import model.gizmos.Flipper;
import model.gizmos.Wall;
import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

/**
 * Gizmoball - Board
 * Created by Group WJ2 on 06/02/2016.
 * Authors: J Baird, Mr Bean, N Stannage, U Akhtar, L Sakalauskas
 */
public class Board extends Observable implements IBoard {
	private Collection<IElement> elements;
	private Collection<Ball> balls;
	private double[] frictionConst;
	private double gravityConst;
	private int width;
	private int height;
	private Collision closestCollision;
	public final static double moveTime = 0.005;
	private boolean highlight = true;

	private Vect mouseClick, mousePress, mouseRelease;
	private IElement selectedElement;

	/**
	 * Make a new boad with the given friction, gravity, width and height.
	 * 
	 * @param frictionConst
	 * @param gravityConst
	 * @param width
	 * @param height
	 */
	public Board(double[] frictionConst, double gravityConst, int width, int height) {
		this.frictionConst = frictionConst;
		this.gravityConst = gravityConst;
		this.width = width;
		this.height = height;
		elements = new ArrayList<>();
		balls = new ArrayList<>();
		addWalls();
	}

	/**
	 * Make a new board with default gravity, friction, width and height.
	 */
	public Board() {
		this(new double[]{0.025, 0.025}, 25, 20, 20);
	}

	/**
	 * Add all the walls to the perimeter.
	 */
	private void addWalls() {
		Vect topLeft = new Vect(0, 0);
		Vect bottomRight = new Vect(20, 20);
		Gizmo walls = new Wall(topLeft, bottomRight, "Wall");
		elements.add(walls);
	}

	@Override
	public void addBall(Ball ball) {
		balls.add(ball);
		setChanged();
		notifyObservers();
	}

	@Override
	public void setBalls(Collection<Ball> newBalls) {
		balls = newBalls;
		setChanged();
		notifyObservers();
	}

	@Override
	public Collection<Ball> getBalls() {
		return balls;
	}

	@Override
	public Collection<IElement> getElements() {
		return elements;
	}

	@Override
	public Collection<IElement> getAllElements() {
		Collection<IElement> allElements = new ArrayList<>();
		allElements.addAll(elements);
		allElements.addAll(balls);
		return allElements;
	}

	@Override
	public void setElements(Collection<IElement> elems) {
		elements = new ArrayList<>();
		for (IElement element : elems) {
			addElement(element);
		}
		addWalls();
		setChanged();
		notifyObservers();
	}

	@Override
	public boolean addElement(IElement element) {
		if (detectEmptyArea(element.getOrigin(), element.getBound())) {
		elements.add(element);
		setChanged();
		notifyObservers();
			return true;
		}
		return false;
	}


	@Override
	public void removeElement(IElement element) {
		if (element instanceof Ball) {
			balls.remove(element);
		} else {
			removeGizmoConnections(element);
			elements.remove(element);
		}
		setChanged();
		notifyObservers();
	}

	private void removeGizmoConnections(IElement element) {
		for (IElement elem : elements) {
			if (elem instanceof Absorber)
				((Absorber) elem).releaseOurBall();
			
			((Gizmo) elem).getTriggerables().remove(element);
			elem.removeConnection(element);
		}
	}
	@Override
	public double[] getFrictionConst() {
		return frictionConst;
	}

	@Override
	public void setFrictionConst(double[] frictionConst) {
		this.frictionConst = frictionConst;
	}

	@Override
	public double getGravityConst() {
		return gravityConst;
	}

	@Override
	public void setGravityConst(double gravityConst) {
		this.gravityConst = gravityConst;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public void setWidth(int width) {
		this.width = width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public void changed() {
		setChanged();
		notifyObservers();
	}

	@Override
	public Vect getMouseClick() {
		return mouseClick;
	}

	@Override
	public void setMouseClick(Vect mouseClick) {
		this.mouseClick = mouseClick;
		selectElement(mouseClick.x(), mouseClick.y());
	}

	@Override
	public Vect getMousePress() {
		return mousePress;
	}

	@Override
	public void setMousePress(Vect mousePress) {
		this.mousePress = mousePress;
		selectElement(mousePress.x(), mousePress.y());
	}

	@Override
	public Vect getMouseRelease() {
		return mouseRelease;
	}

	public void setMouseRelease(Vect mouseRelease) {
		this.mouseRelease = mouseRelease;
	}

	@Override
	public IElement getSelectedElement() {
		return selectedElement;
	}

	@Override
	public void setSelectedElement(IElement selectedElement) {
		this.selectedElement = selectedElement;
	}

	@Override
	public boolean moveGizmo(IElement selectedElement, Vect distance) {
		Vect newOrigin = selectedElement.getOrigin().plus(distance);
		Vect newBound = selectedElement.getBound().plus(distance);
		if (detectEmptyArea(newOrigin, newBound)) {
			selectedElement.move(distance);
			return true;
		}
		return false;
	}

	/**
	 * Check if the area between origin and bound. 
	 * 
	 * @param origin
	 * @param bound
	 * @return
	 */
	public boolean detectEmptyArea(Vect origin, Vect bound) {
		for (double x = origin.x(); x < bound.x(); x += 1) {
			for (double y = origin.y(); y < bound.y(); y += 1) {
				if (!detectEmptyLocation(new Vect(x, y))) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Check if area at position is empty.
	 * 
	 * @param position
	 * @return
	 */
	public boolean detectEmptyLocation(Vect position) {
		if (position.x() >= 20 || position.x() < 0) {
			return false;
		}
		if (position.y() >= 20 || position.y() < 0) {
			return false;
		}
		for (IElement existingElement : elements) {
			if (existingElement.getOrigin().equals(position) && !(existingElement instanceof Wall)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Select the element at coordinate (x,y)
	 * 
	 * @param x
	 * @param y
	 */
	private void selectElement(double x, double y) {
		if (!highlight) {
			return;
		}
		if (selectedElement != null) {
			selectedElement.highlight(false);
		}
		
		if ((selectedElement = getElementAtLocation(x, y)) != null)
			selectedElement.highlight(true);
	}

	@Override
	public void clearSelection() {
		if (selectedElement != null) {
			selectedElement.highlight(false);
		}
		selectedElement = null;
	}

	@Override
	public void stopHighlighting() {
		highlight = false;
	}

	@Override
	public void startHighlighting() {
		highlight = true;
	}

	@Override
	public IElement getElementAtLocation(double x, double y) {

		for (Ball ball : balls) {
			Vect origin = ball.getOrigin();
			Vect bound = ball.getOrigin().plus(new Vect(0.5, 0.5));
			if (origin.x() <= x && bound.x() > x) {
				if (origin.y() <= y && bound.y() > y) {
					return ball;
				}
			}

		}
		for (IElement element : elements) {
			Vect origin = element.getOrigin();
			Vect bound = element.getBound();

			if (element instanceof Flipper) {

				if (((Flipper) element).getDirection() == Direction.RIGHT) {
					if (origin.x() - 1.5 <= x && bound.x() > x) {
						if (origin.y() <= y && bound.y() > y) {
							return element;
						}
					}
				} else {
					if (origin.x() <= x && bound.x() + 1.5 > x) {
						if (origin.y() <= y && bound.y() > y) {
							return element;
						}
					}
				}

			} else if (!(element instanceof Wall)) {
				if (origin.x() <= x && bound.x() > x) {
					if (origin.y() <= y && bound.y() > y) {
						return element;
					}
				}
			}
		}
		
		return null;
	}
	
	@Override
	public IElement getElementAtLocation(Vect location) {
		return getElementAtLocation(location.x(), location.y());
	}


	/*  --  --  --  Physics Loop --  --  --  */
	public void tick() {

		for (IElement element : getElements()) {
			if (element instanceof Flipper) {
				((Flipper) element).flip();
			}
		}
			Collection<Ball> newBalls = new ArrayList<>();
		for (Ball ball : getBalls()) {
			newBalls.add(moveBall(ball));
		}
		setBalls(newBalls);
	}

	/**
	 * move the ball ball for one tick
	 * 
	 * @param ball
	 * @return
	 */
	private Ball moveBall(Ball ball) {
		ball.applyForces(moveTime, getGravityConst(), getFrictionConst());
		Collision collision = getTimeTillCollision(ball);

		if (collision.getTime() >= moveTime) { // No Collision
			ball.moveForTime(moveTime);
		} else { // Collision
			ball.moveForTime(collision.getTime());
			collision.getHandler().handle(collision);
		}
		return ball;
	}
	
	@Override
	public void clear(){
		elements.clear();
		balls.clear();
		addWalls();
		setChanged();
		notifyObservers();
	}

	/**
	 * Get the time until this ball will have its next collision.
	 * 
	 * @param ball
	 * @return
	 */
	public Collision getTimeTillCollision(Ball ball) {
		closestCollision = new Collision(0, 0, Double.MAX_VALUE);
		for (IElement element : getElements()) {
			if (element instanceof Absorber && ball.inside(element))
				continue;

			for (Circle circle : element.getCircles()) {
				detectCircleCollision(circle, ball, element);
			}

			for (LineSegment line : element.getLines()) {
				detectLineCollision(line, ball, element);
			}

			if (element instanceof Flipper) {

				//((Flipper) element).flip();

				for (LineSegment line : element.getLines()) {
					detectFlipperCollision(line, ball, element);
				}

			}

		}
		for (Ball otherBall : getBalls()) {
			detectBallCollision(otherBall, ball);
		}
		return closestCollision;
	}

	/**
	 * Detect the time until the next collision between the circle and ball.
	 * 
	 * @param circle
	 * @param ball
	 * @param element
	 */
	private void detectCircleCollision(Circle circle, Ball ball, IElement element) {
		double time = Geometry.timeUntilCircleCollision(circle, ball.getCircle(), ball.getVelocity());
		if (time < closestCollision.getTime()) {
			Vect newV = Geometry.reflectCircle(circle.getCenter(), ball.getCenter(), ball.getVelocity());
			closestCollision = new Collision(newV, time, element, ball);
		}
	}

	/**
	 * Detect the time until the next collision between the line and ball.
	 * 
	 * @param line
	 * @param ball
	 * @param element
	 */
	private void detectLineCollision(LineSegment line, Ball ball, IElement element) {
		double time = Geometry.timeUntilWallCollision(line, ball.getCircle(), ball.getVelocity());
		if (time < closestCollision.getTime()) {
			Vect newV = Geometry.reflectWall(line, ball.getVelocity());
			closestCollision = new Collision(newV, time, element, ball);
		}
	}

	/**
	 * Detect the time until the next collision between the flipper line and ball.
	 * 
	 * @param line
	 * @param ball
	 * @param element
	 */
	private void detectFlipperCollision(LineSegment line, Ball ball, IElement element) {
		double time = Geometry.timeUntilRotatingWallCollision(line, ((Flipper) element).getPivotPoint(), ((Flipper) element).getAngularVelocity(),  ball.getCircle(), ball.getVelocity());
		if (time < closestCollision.getTime()) {
			Vect newV = Geometry.reflectRotatingWall(line, ((Flipper) element).getPivotPoint(), ((Flipper) element).getAngularVelocity(), ball.getCircle(), ball.getVelocity(), 0.95);
			closestCollision = new Collision(newV, time, element, ball);
		}
	}

	/**
	 * Detect the time until the next collision between the ball and otherBall.
	 * 
	 * @param otherBall
	 * @param ball
	 */
	private void detectBallCollision(Ball otherBall, Ball ball) {
		Circle ballC = ball.getCircle(), oBallC = otherBall.getCircle();
		Vect ballV = ball.getVelocity(), oBallV = otherBall.getVelocity();
		double time = Geometry.timeUntilBallBallCollision(ballC, ballV, oBallC, oBallV);
		if (time < closestCollision.getTime()) {
			Vect newV = Geometry.reflectCircle(otherBall.getCenter(), ball.getCenter(), ballV);
			closestCollision = new Collision(newV, time, otherBall, ball);
		}
	}

	@Override
	public int getNextElementID() {
		int i = 0;
		for (IElement elem : elements) {
			String name = elem.getName();
			String numberStr = name.replaceAll("[^0-9]", "");
			try {
				int num = Integer.parseInt(numberStr);
				if (num > i) {
					i = num;
				}
			} catch (Exception ignored) {
			}
		}
		return i;
	}

}
