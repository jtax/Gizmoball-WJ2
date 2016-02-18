package model;

import model.Gizmos.*;
import physics.Vect;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class GizmoParser
{
    private BufferedReader fileInput;



    public GizmoParser(File filename) throws IOException
    {
        //a buffered reader reads line by line, returning null when file is done
        fileInput = new BufferedReader(new FileReader(filename));
    }


    public Board getGizmosFromFile() throws IOException, BadFileException
    {

        String line = fileInput.readLine();
        StringTokenizer st;
        String gizmoType = "";
        List<IElement> loadedElements = new ArrayList<IElement>();
        List<Ball> balls = new ArrayList<Ball>();
        Double gravity = 0.0;
        double[] friction = new double[2];

        while(line != null)
        {
            st = new StringTokenizer(line, " ");
            if(!st.hasMoreTokens())
            {
                line = fileInput.readLine();
                continue;
            }


            gizmoType = st.nextToken();
            if(!st.hasMoreTokens())
            {
                throw new BadFileException("Incorrect file format");
            }


            if(gizmoType.equals("Circle") || gizmoType.equals("Triangle") || gizmoType.equals("Square")
                    || gizmoType.equals("RightFlipper") || gizmoType.equals("LeftFlipper")){

                loadedElements.add(shapeParser(gizmoType, st));

            }


            if(gizmoType.equals("Rotate")){
                String gizmoName = "";
                gizmoName = st.nextToken();
                System.out.println(gizmoType + gizmoName );
            }

            if(gizmoType.equals("Absorber")){

                loadedElements.add(parseAbsorber(gizmoType, st));


            }

            if(gizmoType.equals("KeyConnect")){

                parseKey(gizmoType,st);


            }

            if(gizmoType.equals("Connect")){

                parseConnect(gizmoType, st);

            }

            if(gizmoType.equals("Ball")){

                balls.add(parseBall(gizmoType, st));

            }

            if(gizmoType.equals("Gravity")){
                gravity = Double.valueOf(st.nextToken());
                System.out.println(gizmoType + gravity );
            }

            if(gizmoType.equals("Friction")){
                friction = parseFriction(gizmoType, st);
            }
            line = fileInput.readLine();
        }
        Board board = new Board(friction, gravity, 20, 20);
        board.setElements(loadedElements);
        if (!balls.isEmpty()) {
            for (int i = 0; i < balls.size(); i++) {
                board.addBall(balls.get(i));
            }
        }

        // boardManager.getBoard().setElements(loadedElements);
        return board;
    }


    private IElement shapeParser(String gizmo, StringTokenizer st) throws BadFileException {
        String gizmoName = "";
        Integer xCoord = 0;
        Integer yCoord = 0;
        Vect origin;

        gizmoName = st.nextToken();
        if(!st.hasMoreTokens())
        {
            throw new BadFileException("No xCoord");
        }


        xCoord = Integer.valueOf(st.nextToken());
        if(!st.hasMoreTokens())
        {
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
                return new Flipper(origin, gizmoName);
            case ("LeftFlipper"):
                return new Flipper(origin, gizmoName);
            default:
                return null;
        }

    }

    private double[] parseFriction(String gizmo, StringTokenizer st) throws BadFileException {
        String gizmoType = gizmo;
        double[] friction = new double[2];

        friction[0] = Double.valueOf(st.nextToken());
        if(!st.hasMoreTokens())
        {
            throw new BadFileException("No action");
        }

        friction[1] = Double.valueOf(st.nextToken());

        System.out.println(gizmoType + friction[0] + friction[1]);
        return friction;
    }

    private Absorber parseAbsorber(String gizmo, StringTokenizer st) throws BadFileException {
        String gizmoType = gizmo;
        String gizmoName;
        Integer xAbsorberTopLeft;
        Integer yAbsorberTopLeft;
        Integer xAbsorberBotRight;
        Integer yAbsorberBotRight;
        gizmoName = st.nextToken();
        Vect origin;
        if(!st.hasMoreTokens())
        {
            throw new BadFileException("No key linked");
        }

        xAbsorberTopLeft = Integer.valueOf(st.nextToken());
        if(!st.hasMoreTokens())
        {
            throw new BadFileException("No action");
        }

        yAbsorberTopLeft = Integer.valueOf(st.nextToken());
        if(!st.hasMoreTokens())
        {
            throw new BadFileException("No action");
        }

        xAbsorberBotRight = Integer.valueOf(st.nextToken());
        if(!st.hasMoreTokens())
        {
            throw new BadFileException("No action");
        }

        yAbsorberBotRight = Integer.valueOf(st.nextToken());
        System.out.println(gizmoType + gizmoName + xAbsorberTopLeft + yAbsorberTopLeft + xAbsorberBotRight + yAbsorberBotRight);
        origin = new Vect(xAbsorberTopLeft, yAbsorberTopLeft);
        Vect bound = new Vect(xAbsorberBotRight, yAbsorberBotRight);
        Absorber a = new Absorber( origin ,bound, gizmoName);
        return a;

    }

    private Ball parseBall(String gizmo, StringTokenizer st) throws BadFileException {
        String gizmoType = gizmo;
        String gizmoName;
        Double ballXCoord;
        Double ballYCoord;
        Double ballXVelocity;
        Double ballYVelocity;

        gizmoName = st.nextToken();
        if(!st.hasMoreTokens())
        {
            throw new BadFileException("No ball xCoords");
        }

        ballXCoord = Double.valueOf(st.nextToken());
        if(!st.hasMoreTokens())
        {
            throw new BadFileException("No ball yCoords");
        }

        ballYCoord = Double.valueOf(st.nextToken());
        if(!st.hasMoreTokens())
        {
            throw new BadFileException("No ball xVelocity");
        }

        ballXVelocity = Double.valueOf(st.nextToken());
        if(!st.hasMoreTokens())
        {
            throw new BadFileException("No ball yVelocity");
        }

        ballYVelocity = Double.valueOf(st.nextToken());
        Ball b = new Ball( gizmoName , ballXCoord , ballYCoord , ballXVelocity , ballYVelocity);
        System.out.println(gizmoType + gizmoName + ballXCoord + ballYCoord + ballXVelocity + ballYVelocity);
        return b;
    }

    private void parseKey(String gizmo, StringTokenizer st) throws BadFileException{

        String gizmoType = gizmo;
        String action;
        String linkedGizmo;
        String gizmoName;
        Integer key;
        gizmoName = st.nextToken();
        if(!st.hasMoreTokens())
        {
            throw new BadFileException("No key linked");
        }

        key = Integer.valueOf(st.nextToken());
        if(!st.hasMoreTokens())
        {
            throw new BadFileException("No action");
        }

        action = st.nextToken();
        if(!st.hasMoreTokens())
        {
            throw new BadFileException("No Linked Gizmo");
        }

        linkedGizmo = st.nextToken();
        System.out.println(gizmoType + gizmoName + key +action + linkedGizmo );

    }

    private void parseConnect(String gizmo, StringTokenizer st) throws BadFileException{

        String gizmoType = gizmo;
        String gizmoName;
        String linkedGizmo;
        String action = "";
        Integer key = 0;
        gizmoName = st.nextToken();
        if(!st.hasMoreTokens())
        {
            throw new BadFileException("No linked Gizmo");
        }

        linkedGizmo = st.nextToken();
        System.out.println(gizmoType + gizmoName + key +action + linkedGizmo );
    }



}