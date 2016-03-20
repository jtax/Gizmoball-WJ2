package model;

import model.gizmos.*;
import physics.Vect;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class GizmoParser {
	private BufferedReader fileInput;

	public GizmoParser(File filename) throws IOException {
		// a buffered reader reads line by line, returning null when file is
		// done
		fileInput = new BufferedReader(new FileReader(filename));
	}



	public Board getGizmosFromFile() throws IOException, BadFileException {

		String line = fileInput.readLine();
		StringTokenizer st;
		String gizmoType;
		List<String> rotates = new ArrayList<>();
		List<IElement> loadedElements = new ArrayList<>();
		List<Ball> balls = new ArrayList<>();
		List<String> keyConnects = new ArrayList<>();
		List<String> gizmoConnects = new ArrayList<>();
		double gravity = 0.0;
		double[] friction = new double[2];

		while (line != null) {
			st = new StringTokenizer(line, " ");
			if (!st.hasMoreTokens()) {
				line = fileInput.readLine();
				continue;
			}

			gizmoType = st.nextToken();
			if (!st.hasMoreTokens()) {
				throw new BadFileException("Incorrect file format");
			}

			if (gizmoType.equals("Circle") || gizmoType.equals("Triangle") || gizmoType.equals("Square")
					|| gizmoType.equals("RightFlipper") || gizmoType.equals("LeftFlipper")) {

				loadedElements.add(shapeParser(gizmoType, st));

			}

			if (gizmoType.equals("Rotate")) {
				String gizmoName = st.nextToken();
				rotates.add(gizmoName);
			}

			if (gizmoType.equals("Absorber")) {

				loadedElements.add(parseAbsorber(gizmoType, st));

			}

			if (gizmoType.equals("KeyConnect")) {

				parseKey(gizmoType, st,keyConnects);

			}

			if (gizmoType.equals("Connect")) {

				parseConnect(gizmoType, st,gizmoConnects);

			}

			if (gizmoType.equals("Ball")) {

				balls.add(parseBall(gizmoType, st));

			}

			if (gizmoType.equals("Gravity")) {
				gravity = Double.valueOf(st.nextToken());
				System.out.println(gizmoType + gravity);
			}

			if (gizmoType.equals("Friction")) {
				friction = parseFriction(gizmoType, st);
			}
			line = fileInput.readLine();
		}
		Board board = new Board(friction, gravity, 20, 20);
		board.setElements(loadedElements);
		if (!balls.isEmpty()) {
			for (Ball ball : balls) {
				board.addBall(ball);
			}
		}
		if (!rotates.isEmpty()) {
			for (IElement e : board.getElements()) {
				for (String rotate : rotates) {
					if (e.getName().equals(rotate)) {
						e.rotate();
					}
				}
			}
		}
		if(!keyConnects.isEmpty()){
			for (IElement e : board.getElements()) {
				for (String key : keyConnects) {
					StringTokenizer keytoken;
					keytoken = new StringTokenizer(key, " ");
					String name = keytoken.nextToken();
					int keyCode = Integer.valueOf(keytoken.nextToken());

					if (e.getName().equals(name)) {
						System.out.println(name +" "+ keyCode);
						e.addKeyConnect(keyCode);
						//((Gizmo) e).addKeyPressTrigger(keycode);
					}
				}
			}
		}


		if(!gizmoConnects.isEmpty()){
			IElement firstElement = null;
			IElement secondElement = null;
			for (String connection : gizmoConnects) {
				StringTokenizer connectToken;
				connectToken = new StringTokenizer(connection, " ");
				if (!connectToken.hasMoreTokens()) {
					continue;
				}
				String first = connectToken.nextToken();
				String second = connectToken.nextToken();

				for (IElement e : board.getElements()) {
					if (e.getName().equals("Wall")) {
						continue;
					}
					if (e.getName().equals(first)) {
						firstElement = e;
					}
				}
					for (IElement e2 : board.getElements()) {
						if (e2.getName().equals(second)){
							secondElement = e2;
						}
					}
				//System.out.println(firstElement.getName() + " " + secondElement.getName());
					firstElement.gizmoConnect(secondElement);
				}
		}




		return board;
	}

	public Gizmo shapeParser(String gizmo, StringTokenizer st) throws BadFileException {
		String gizmoName;
		int xCoord;
		int yCoord;
		Vect origin;

		gizmoName = st.nextToken();
		if (!st.hasMoreTokens()) {
			throw new BadFileException("No xCoord");
		}

		xCoord = Integer.valueOf(st.nextToken());
		if (!st.hasMoreTokens()) {
			throw new BadFileException("No yCoord");
		}

		yCoord = Integer.valueOf(st.nextToken());
		origin = new Vect(xCoord, yCoord);

		switch (gizmo) {
		case ("Circle"):
			return new Circle(origin, gizmoName);
		case ("Square"):
			return new Square(origin, gizmoName);
		case ("Triangle"):
			return new Triangle(origin, gizmoName);
		case ("RightFlipper"):
			Flipper r = new Flipper(origin, gizmoName);
			r.setDirection(Direction.RIGHT);
			return r;
		case ("LeftFlipper"):
			return new Flipper(origin, gizmoName);
		default:
			return null;
		}

	}

	private double[] parseFriction(String gizmo, StringTokenizer st) throws BadFileException {
		double[] friction = new double[2];

		friction[0] = Double.valueOf(st.nextToken());
		if (!st.hasMoreTokens()) {
			throw new BadFileException("No action");
		}

		friction[1] = Double.valueOf(st.nextToken());

		System.out.println(gizmo + friction[0] + friction[1]);
		return friction;
	}

	private Absorber parseAbsorber(String gizmo, StringTokenizer st) throws BadFileException {
		String gizmoName;
		int xAbsorberTopLeft;
		int yAbsorberTopLeft;
		int xAbsorberBotRight;
		int yAbsorberBotRight;
		gizmoName = st.nextToken();
		Vect origin;
		if (!st.hasMoreTokens()) {
			throw new BadFileException("No key linked");
		}

		xAbsorberTopLeft = Integer.valueOf(st.nextToken());
		if (!st.hasMoreTokens()) {
			throw new BadFileException("No action");
		}

		yAbsorberTopLeft = Integer.valueOf(st.nextToken());
		if (!st.hasMoreTokens()) {
			throw new BadFileException("No action");
		}

		xAbsorberBotRight = Integer.valueOf(st.nextToken());
		if (!st.hasMoreTokens()) {
			throw new BadFileException("No action");
		}

		yAbsorberBotRight = Integer.valueOf(st.nextToken());
		System.out.println(
				gizmo + gizmoName + xAbsorberTopLeft + yAbsorberTopLeft + xAbsorberBotRight + yAbsorberBotRight);
		origin = new Vect(xAbsorberTopLeft, yAbsorberTopLeft);
		Vect bound = new Vect(xAbsorberBotRight, yAbsorberBotRight);
		return new Absorber(origin, bound, gizmoName);

	}

	private Ball parseBall(String gizmo, StringTokenizer st) throws BadFileException {
		String gizmoName;
		double ballXCoord;
		double ballYCoord;
		double ballXVelocity;
		double ballYVelocity;

		gizmoName = st.nextToken();
		if (!st.hasMoreTokens()) {
			throw new BadFileException("No ball xCoords");
		}

		ballXCoord = Double.valueOf(st.nextToken());
		if (!st.hasMoreTokens()) {
			throw new BadFileException("No ball yCoords");
		}

		ballYCoord = Double.valueOf(st.nextToken());
		if (!st.hasMoreTokens()) {
			throw new BadFileException("No ball xVelocity");
		}

		ballXVelocity = Double.valueOf(st.nextToken());
		if (!st.hasMoreTokens()) {
			throw new BadFileException("No ball yVelocity");
		}

		ballYVelocity = Double.valueOf(st.nextToken());
		Ball b = new Ball(gizmoName, ballXCoord, ballYCoord, ballXVelocity, ballYVelocity);
		System.out.println(gizmo + gizmoName + ballXCoord + ballYCoord + ballXVelocity + ballYVelocity);
		return b;
	}

	private void parseKey(String gizmo, StringTokenizer st, List<String> keyConnects) throws BadFileException {
		String action;
		String linkedGizmo;
		String gizmoName;
		int keycode;
		gizmoName = st.nextToken();
		if (!st.hasMoreTokens()) {
			throw new BadFileException("No key linked");
		}

		keycode = Integer.valueOf(st.nextToken());

		if (!st.hasMoreTokens()) {
			throw new BadFileException("No action");
		}

		action = st.nextToken();
		if (!st.hasMoreTokens()) {
			throw new BadFileException("No Linked Gizmo");
		}

		linkedGizmo = st.nextToken();
		System.out.println(gizmo + gizmoName + keycode + action + linkedGizmo);
		keyConnects.add(linkedGizmo + " " + keycode);
	}

	private void parseConnect(String gizmo, StringTokenizer st,List<String> gizmoConnects) throws BadFileException {
		String gizmoName;
		String linkedGizmo;
		String action = "";
		gizmoName = st.nextToken();
		if (!st.hasMoreTokens()) {
			throw new BadFileException("No linked Gizmo");
		}

		linkedGizmo = st.nextToken();
		System.out.println(gizmo + gizmoName  + action + linkedGizmo);
		gizmoConnects.add(gizmoName +" " + linkedGizmo);
	}



}
