package model;

/**
 * An interface for elements that can handle collisions.
 * 
 * @author Team WJ2
 *
 */
public interface CollisionHandler {

	/**
	 * Handle the collision by changing the ball's movement.
	 * 
	 * @param collision
	 */
	void handle(Collision collision);
}
