package model;

import model.gizmos.Absorber;
import model.gizmos.Flipper;
import model.gizmos.Wall;
import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Observable;

/**
 * Created by baird on 06/02/2016.
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

	private Vect mouseClick, mousePress, mouseRelease;
	private IElement selectedElement;

	public Board(double[] frictionConst, double gravityConst, int width, int height) {
		this.frictionConst = frictionConst;
		this.gravityConst = gravityConst;
		this.width = width;
		this.height = height;
		elements = new ArrayList<>();
		balls = new ArrayList<>();
		addWalls();
	}

	public Board() {
		this(new double[]{0.025, 0.025}, 25, 20, 20);
	}

	@Override
	public void addBall(Ball ball) {
		balls.add(ball);
		setChanged();
		notifyObservers();
	}

	private void addWalls() {
		Vect topLeft = new Vect(0, 0);
		Vect topRight = new Vect(20, 0);
		Vect bottomLeft = new Vect(0, 20);
		Vect bottomRight = new Vect(20, 20);
		Gizmo walls = new Wall(topLeft, bottomRight, "Wall");
		elements.add(walls);
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
	public void setElements(Collection<IElement> elements) {
		for (IElement element : elements) {
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
		elements.remove(element);
		setChanged();
		notifyObservers();
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
			System.out.println(selectedElement.getName() + "Moved");
			return true;
		}
		return false;
	}

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
	public boolean detectEmptyLocation(Vect position) {
		if (position.x() >= 20 || position.x() < 0) {
			System.out.println("DETECT EMPTY FAILED : X");
			return false;
		}
		if (position.y() >= 20 || position.y() < 0) {
			System.out.println("DETECT EMPTY FAILED : Y");
			return false;
		}
		for (IElement existingElement : elements) {
			if (existingElement.getOrigin().equals(position) && !(existingElement instanceof Wall)) {
				System.out.println("DETECT EMPTY FAILED : " + existingElement.getName());
				return false;
			}
		}
		return true;
	}

	public void selectElement(double x, double y) {
		if (selectedElement != null) {
			selectedElement.highlight();
		}
		
		if ((selectedElement = getElementAtLocation(x, y)) != null)
			selectedElement.highlight();
	}
	
	@Override
	public IElement getElementAtLocation(double x, double y) {
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
		Collection<Ball> newBalls = new ArrayList<>();
		for (Ball ball : getBalls()) {
			newBalls.add(moveBall(ball));
		}
		setBalls(newBalls);
	}

	private Ball moveBall(Ball ball) {
		ball.applyForces(moveTime, getGravityConst(), getFrictionConst());
		Collision collision = getTimeTillCollision(ball);

		if (collision.getTime() >= moveTime) // No Collision
			ball.moveForTime(moveTime);
		else // Collision
			collision.getHandler().handle(collision);

		return ball;

	}
	public void clear(){
		elements.clear();
		balls.clear();
		setChanged();
		notifyObservers();
	}

	private Collision getTimeTillCollision(Ball ball) {
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

				((Flipper) element).flip();

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

	private void detectCircleCollision(Circle circle, Ball ball, IElement element) {
		double time = Geometry.timeUntilCircleCollision(circle, ball.getCircle(), ball.getVelocity());
		if (time < closestCollision.getTime()) {
			Vect newV = Geometry.reflectCircle(circle.getCenter(), ball.getCenter(), ball.getVelocity());
			closestCollision = new Collision(newV, time, element, ball);
		}
	}

	private void detectLineCollision(LineSegment line, Ball ball, IElement element) {
		double time = Geometry.timeUntilWallCollision(line, ball.getCircle(), ball.getVelocity());
		if (time < closestCollision.getTime()) {
			Vect newV = Geometry.reflectWall(line, ball.getVelocity());
			closestCollision = new Collision(newV, time, element, ball);
		}
	}

	private void detectFlipperCollision(LineSegment line, Ball ball, IElement element) {
		double time = Geometry.timeUntilRotatingWallCollision(line, ((Flipper) element).getPivotPoint(), ((Flipper) element).getAngularVelocity(),  ball.getCircle(), ball.getVelocity());
		if (time < closestCollision.getTime()) {
			Vect newV = Geometry.reflectRotatingWall(line, ((Flipper) element).getPivotPoint(), ((Flipper) element).getAngularVelocity(), ball.getCircle(), ball.getVelocity(), 0.95);
			closestCollision = new Collision(newV, time, element, ball);
		}
	}

	private void detectBallCollision(Ball otherBall, Ball ball) {
		Circle ballC = ball.getCircle(), oBallC = otherBall.getCircle();
		Vect ballV = ball.getVelocity(), oBallV = otherBall.getVelocity();
		double time = Geometry.timeUntilBallBallCollision(ballC, ballV, oBallC, oBallV);
		if (time < closestCollision.getTime()) {
			Vect newV = Geometry.reflectCircle(otherBall.getCenter(), ball.getCenter(), ballV);
			closestCollision = new Collision(newV, time, otherBall, ball);
		}
	}

}
