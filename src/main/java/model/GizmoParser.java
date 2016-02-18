package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import model.Gizmos.Absorber;
import model.Gizmos.Circle;
import model.Gizmos.Flipper;
import model.Gizmos.Square;
import model.Gizmos.Triangle;
import physics.Vect;

public class GizmoParser
{
    private BufferedReader fileInput;



    public GizmoParser(File filename) throws IOException
    {
        //a buffered reader reads line by line, returning null when file is done
        fileInput = new BufferedReader(new FileReader(filename));
    }


    public void getGizmosFromFile() throws IOException, BadFileException
    {

        String line = fileInput.readLine();
        StringTokenizer st;
        String gizmoType = "";

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

                shapeParser(gizmoType,st);

            }


            if(gizmoType.equals("Rotate")){
                String gizmoName = "";
                gizmoName = st.nextToken();
                System.out.println(gizmoType + gizmoName );
            }

            if(gizmoType.equals("Absorber")){

                parseAbsorber(gizmoType,st);


            }

            if(gizmoType.equals("KeyConnect")){

                parseKey(gizmoType,st);


            }

            if(gizmoType.equals("Connect")){

                parseConnect(gizmoType, st);

            }

            if(gizmoType.equals("Ball")){

                parseBall(gizmoType,st);

            }
            line = fileInput.readLine();
        }

    }


    private void shapeParser(String gizmo, StringTokenizer st) throws BadFileException{

        String gizmoType = gizmo;
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
        if(gizmoType.equals("Circle")){
            origin = new Vect(xCoord, yCoord);
            Circle circle = new Circle(origin,gizmoName);
        }

        else if(gizmoType.equals("Square")){
            origin = new Vect(xCoord, yCoord);
            Square s = new Square(origin,gizmoName);
        }

        else if(gizmoType.equals("Triangle")){
            origin = new Vect(xCoord, yCoord);
            Triangle t = new Triangle(origin,gizmoName);
        }

        else if(gizmoType.equals("RightFlipper")){
            origin = new Vect(xCoord, yCoord);
            Flipper t = new Flipper(origin,gizmoName);
        }

        else if(gizmoType.equals("LeftFlipper")){
            origin = new Vect(xCoord, yCoord);
            Flipper t = new Flipper(origin,gizmoName);
        }
        System.out.println(gizmoType + gizmoName + xCoord + yCoord);
    }

    private void parseAbsorber(String gizmo,StringTokenizer st) throws BadFileException{
        String gizmoType = gizmo;
        String gizmoName = "";
        Integer xAbsorberTopLeft = 0;
        Integer yAbsorberTopLeft = 0;
        Integer xAbsorberBotRight = 0;
        Integer yAbsorberBotRight = 0;
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

    }

    private void parseBall(String gizmo,StringTokenizer st) throws BadFileException{
        String gizmoType = gizmo;
        String gizmoName = "";
        Double ballXCoord = 0.0;
        Double ballYCoord = 0.0;
        Double ballXVelocity = 0.0;
        Double ballYVelocity = 0.0;

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
    }

    private void parseKey(String gizmo, StringTokenizer st) throws BadFileException{

        String gizmoType = gizmo;
        String action = "";
        String linkedGizmo = "";
        String gizmoName = "";
        Integer key = 0;
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
        String gizmoName = "";
        String linkedGizmo = "";
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