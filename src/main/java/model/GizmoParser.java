package model;

import model.Gizmos.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

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

        String gizmoType = gizmo;;
        String gizmoName = "";
        Integer xCoord = 0;
        Integer yCoord = 0;
        Coordinate c;


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
             c = new Coordinate(xCoord,yCoord);
            Circle c = new Circle(c);
        }

        else if(gizmoType.equals("Square")){
            c = new Coordinate(xCoord,yCoord);
            Square s = new Square(c);
        }

        else if(gizmoType.equals("Triangle")){
            c = new Coordinate(xCoord,yCoord);
            Triangle t = new Triangle(c);
        }

        else if(gizmoType.equals("RightFlipper")){
            c = new Coordinate(xCoord,yCoord);
            Flipper t = new Flipper(c);
        }

        else if(gizmoType.equals("LeftFlipper")){
            c = new Coordinate(xCoord,yCoord);
            Flipper t = new Flipper(c);
        }
        System.out.println(gizmoType + gizmoName + xCoord + yCoord);
    }

    private void parseAbsorber(String gizmo,StringTokenizer st) throws BadFileException{
        String gizmoType = gizmo;
        String gizmoName = "";
        Integer xAbsorberTopLeft = 0;
        Integer yAbsorberTopLeft = 0;
        Integer xAbsorberBotLeft = 0;
        Integer yAbsorberBotLeft = 0;
        gizmoName = st.nextToken();
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

        xAbsorberBotLeft = Integer.valueOf(st.nextToken());
        if(!st.hasMoreTokens())
        {
            throw new BadFileException("No action");
        }

        yAbsorberBotLeft = Integer.valueOf(st.nextToken());
        System.out.println(gizmoType + gizmoName + xAbsorberTopLeft + yAbsorberTopLeft + xAbsorberBotLeft + yAbsorberBotLeft);
        Absorber a = new Absorber(gizmoName, xAbsorberTopLeft, yAbsorberTopLeft, xAbsorberBotLeft, yAbsorberBotLeft);

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