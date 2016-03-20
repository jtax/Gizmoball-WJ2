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

	List<LineSegment> getLines();

	List<Circle> getCircles();

	List<Vect> getCoordinates();

	Vect getOrigin();

	Vect getBound();

	Color getColor();

	String getName();

	String getSaveInfo();

	int getRotation();

	void rotate();

	void highlight(boolean toggle);

	void move(Vect distance);

	void gizmoConnect(IElement secondElement);

	List<String> getConnections();

	void clearConnections();

	void addKeyConnect(int keyCode);

	List<String> returnKeyConnects();

	void clearKeyConnections();

	void removeKeyConnects();

}
