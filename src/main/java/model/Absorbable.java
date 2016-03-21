package model;

/**
 * An interface for elements that can be absorbed by absorbers.
 * 
 * @author Team WJ2
 */
public interface Absorbable {

	/**
	 * 
	 * @return true if the element is absorbed, otherwise false
	 */
	boolean isAbsorbed();

	/**
	 * Absorb the element
	 */
	void absorb();

	/**
	 * Release the ball
	 */
	void release();
}
