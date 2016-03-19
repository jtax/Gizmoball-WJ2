package junit.model;

import model.Board;
import model.Direction;
import model.Gizmo;
import model.GizmoParser;
import model.gizmos.Flipper;
import org.junit.Before;
import org.junit.Test;
import physics.Vect;

import java.io.File;
import java.util.StringTokenizer;

import static org.junit.Assert.*;

/**
 * Gizmoball - GizmoParser Test
 * Created by Group WJ2 on 15/03/2016.
 * Authors: J Baird, C Bean, N Stannage, U Akhtar, L Sakalauskas
 */
public class GizmoParserTest {
    Board b;
    GizmoParser gp;

    @Before
    public void setUp() throws Exception {
        b = new Board();
        gp = new GizmoParser(new File("inputFile.txt"));
    }

    @Test
    public void createSquare() throws Exception {
        StringTokenizer st = new StringTokenizer("S12 1 2");
        Gizmo square = gp.shapeParser("Square", st);
        assert square.getOrigin().equals(new Vect(1, 2));
        assert square.getName().equals("S12");
        assert square.getBound().equals(new Vect(2, 3));
    }

    @Test
    public void createTriangle() throws Exception {
        StringTokenizer st = new StringTokenizer("T 19 0");
        Gizmo triangle = gp.shapeParser("Triangle", st);
        assert triangle.getOrigin().equals(new Vect(19, 0));
        assert triangle.getName().equals("T");
        assert triangle.getBound().equals(new Vect(20, 1));
    }

    @Test
    public void createCircle() throws Exception {
        StringTokenizer st = new StringTokenizer("C43 4 3");
        Gizmo circle = gp.shapeParser("Circle", st);
        assert circle.getOrigin().equals(new Vect(4, 3));
        assert circle.getName().equals("C43");
        assert circle.getBound().equals(new Vect(5, 4));
    }

    @Test
    public void createLeftFlipper() throws Exception {
        StringTokenizer st = new StringTokenizer("LF92 9 2");
        Gizmo lf = gp.shapeParser("LeftFlipper", st);
        assert lf.getOrigin().equals(new Vect(9, 2));
        assert lf.getName().equals("LF92");
        assert ((Flipper) lf).getDirection().equals(Direction.LEFT);
    }

    @Test
    public void createRightFlipper() throws Exception {
        StringTokenizer st = new StringTokenizer("RF92 9 2");
        Gizmo rf = gp.shapeParser("RightFlipper", st);
        assert rf.getOrigin().equals(new Vect(9, 2));
        assert rf.getName().equals("RF92");
        assert ((Flipper) rf).getDirection().equals(Direction.RIGHT);
    }
}
