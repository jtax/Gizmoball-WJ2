package model;

import java.awt.Color;
import java.util.List;

import physics.Circle;
import physics.LineSegment;
import physics.Vect;

/**
 * Gizmoball - IElement
 * Created by Group WJ2 on 06/02/2016.
 * Authors: J Baird, C Bean, N Stannage, U Akhtar, L Sakalauskas
 */
public interface IElement extends CollisionHandler {

	/**
	 * Get the element's lines
	 * @return the element's lines
	 */
	List<LineSegment> getLines();

	/**
	 * get the element's circles
	 * @return the element's circles
	 */
	List<Circle> getCircles();

	/**
	 * get the element's coordinates
	 * @return the element's coordinates
	 */
	List<Vect> getCoordinates();

	/**
	 * get the element's origin
	 * @return the element's origin
	 */
	Vect getOrigin();

	/**
	 * 
	 * @return the element's bound
	 */
	Vect getBound();

	/**
	 * 
	 * @return the element's colour
	 */
	Color getColor();

	/**
	 * @return the element's name
	 */
	String getName();

	/**
	 * @return the element's save info
	 */
	String getSaveInfo();

	/**
	 * @return the element's rotation
	 */
	int getRotation();

	/**
	 * rotate the element
	 */
	void rotate();

	/**
	 * highlight the element
	 * @param toggle
	 */
	void highlight(boolean toggle);

	/**
	 * move the element by distance
	 * @param distance the distance by which to move the element
	 */
	void move(Vect distance);

	/**
	 * connect the element secondElement to this element
	 * @param secondElement the element to connect to this element
	 */
	void gizmoConnect(IElement secondElement);

	/**
	 * get the the save info representing connections to this element
	 * @return
	 */
	List<String> getConnections();

	/**
	 * clear all connections to the element
	 */
	void clearConnections();

	/**
	 * connect key keyCode to this element
	 * @param keyCode the keyCode to connect to this element
	 */
	void addKeyConnect(int keyCode);

	/**
	 * the save info representing the key connections to this element
	 * @return
	 */
	List<String> returnKeyConnects();

	/**
	 * clear all key connections to this element
	 */
	void clearKeyConnections();

	/**
	 * remove the connection between element and this
	 * @param element the element to disconnect
	 */
	void removeConnection(IElement element);
}
