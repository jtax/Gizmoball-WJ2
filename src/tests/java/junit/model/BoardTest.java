package junit.model;

import model.*;
import model.gizmos.Absorber;
import model.gizmos.Square;
import model.gizmos.Wall;
import org.junit.Before;
import org.junit.Test;
import physics.Vect;

import java.sql.Array;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by baird on 15/03/2016.
 */
public class BoardTest {
    Board board;

    @Before
    public void setUp() throws Exception {
        board = new Board();
    }

    //See spreadsheet Test 1
    @Test
    public void makeBoardWithDeafultFrictionGravityWidthandHeight() {
        Board b = new Board();
        assertTrue(25 == b.getGravityConst());
        assertTrue(20 == b.getWidth());
        assertTrue(20 == b.getHeight());
        assertTrue(0.025 == b.getFrictionConst()[0]);
        assertTrue(0.025 == b.getFrictionConst()[1]);
    }

    @Test
    public void makeBoardWithParams() {
        //See spreadsheet Test 2
        Board b = new Board(new double[]{0.001,0.02}, 19, 24,26);
        assertTrue(19 == b.getGravityConst());
        assertTrue(24 == b.getWidth());
        assertTrue(26 == b.getHeight());
        assertTrue(0.001 == b.getFrictionConst()[0]);
        assertTrue(0.02 == b.getFrictionConst()[1]);
    }


    @Test
    public void checkWallsAreAdded() {
        //See spreadsheet Test 3
        Board b = new Board();
        assertTrue(b.getAllElements().size() > 0);
    }

    @Test
    public void checkThatTheOnlyIElementOnTheBoardIsWalls() {
        //See spreadsheet Test 4 & 6
        Board b = new Board();
        b.getAllElements().contains(Wall.class);
    }

    @Test
    public void checkThatWallCoordsAreCorrect() {
        //See spreadsheet Test 5
        Board b = new Board();
        IElement wall = (IElement)b.getElements().toArray()[0];
        Vect topLeft = new Vect(0, 0);
        Vect bottomRight = new Vect(20, 20);

        assertEquals(topLeft, wall.getCoordinates().get(0));
        assertEquals(bottomRight, wall.getCoordinates().get(1));
    }

    @Test
    public void testAddBallNum() {
        //See spreadsheet Test 8
    }

    @Test
    public void testSetBall() {
        //See spreadsheet Test  9
    }

    @Test
    public void getAllElementsTypes() {
        //See spreadsheet Test 10
    }

    @Test
    public void getAllElements() {
        //See spreadsheet Test 11
    }

    @Test
    public void setElements1() {
        //See spreadsheet Test 12
    }


    @Test
    public void setElements2() {
        //See spreadsheet Test 13
    }

    @Test
    public void setElements3() {
        //See spreadsheet Test 14
    }

    @Test
    public void addElement1() {
        //See spreadsheet Test 15
    }

    @Test
    public void addElement2() {
        //See spreadsheet Test 16
    }

    @Test
    public void addElement3() {
        //See spreadsheet Test 17
    }

    @Test
    public void removeElement1() {
        //See spreadsheet Test 18
    }

    @Test
    public void removeElement2() {
        //See spreadsheet Test 19
    }

    @Test
    public void getsetFriction() {
        //See spreadsheet Test 20
    }

    @Test
    public void getsetGravity() {
        //See spreadsheet Test 21
    }

    @Test
    public void getsetWidth() {
        //See spreadsheet Test 22
    }

    @Test
    public void getsetHeight() {
        //See spreadsheet Test 23
    }

    @Test
    public void moveGizmo1() {
        //See spreadsheet Test 24
    }

    @Test
    public void moveGizmo2() {
        //See spreadsheet Test 25
    }

    @Test
    public void moveGizmo3() {
        //See spreadsheet Test 26
    }

    @Test
    public void detectEmptyArea1() {
        //See spreadsheet Test 27
    }

    @Test
    public void detectEmptyArea2() {
        //See spreadsheet Test 28
    }

    @Test
    public void detectEmptyArea3() {
        //See spreadsheet Test 29
    }

    @Test
    public void detectEmptySquare1() {
        //See spreadsheet Test 30
    }

    @Test
    public void detectEmptySquare2() {
        //See spreadsheet Test 31
    }

    @Test
    public void detectEmptySquare3() {
        //See spreadsheet Test 32
    }

    @Test
    public void getElementAtLocation1() {
        //See spreadsheet Test33
    }

    @Test
    public void getElementAtLocation2() {
        //See spreadsheet Test 34
    }

    @Test
    public void getElementAtLocation3() {
        //See spreadsheet Test 35
    }

    @Test
    public void getElementAtLocation4() {
        //See spreadsheet Test 36
    }

    @Test
    public void getElementAtLocation5() {
        //See spreadsheet Test 37
    }

    @Test
    public void getElementAtLocation6() {
        //See spreadsheet Test 38
    }

    @Test
    public void getElementAtLocation7() {
        //See spreadsheet Test 39
    }

    @Test
    public void getElementAtLocation8() {
        //See spreadsheet Test 40
    }

    @Test
    public void getElementAtLocation9() {
        //See spreadsheet Test 41
    }

    @Test
    public void getElementAtLocation10() {
        //See spreadsheet Test 42
    }

    @Test
    public void getElementAtLocation11() {
        //See spreadsheet Test 43
    }

    @Test
    public void getElementAtLocation12() {
        //See spreadsheet Test 44
    }

    @Test
    public void clear1() {
        //See spreadsheet Test 45
    }

    @Test
    public void clear2() {
        //See spreadsheet Test 46
    }

    @Test
    public void tick() {
        //See spreadsheet Test 47
    }

    @Test
    public void moveBall() {
        //See spreadsheet Test 48
    }


    @Test
    public void testSimpleMoveGizmo() throws Exception {
        IElement originalSquare = new Square(5, 5, "TestSquare");
        board.addElement(originalSquare);
        assertTrue(board.moveGizmo(originalSquare, new Vect(1, 1)));
        IElement movedSquare = board.getElementAtLocation(6, 6);
        assertEquals(originalSquare, movedSquare);
    }

    @Test
    public void testMoveGizmoOutsideBoard() throws Exception {
        IElement originalSquare = new Square(5, 5, "TestSquare");
        board.addElement(originalSquare);
        assertTrue(board.moveGizmo(originalSquare, new Vect(14, 0)));
        assertTrue(board.moveGizmo(originalSquare, new Vect(-5, 0)));
        assertTrue(board.moveGizmo(originalSquare, new Vect(0, 14)));
        assertTrue(board.moveGizmo(originalSquare, new Vect(0, -5)));
    }

    @Test
    public void testMoveGizmoOnTopOfAnotherGizmo() throws Exception {
        IElement originalSquare = new Square(5, 5, "TestSquare");
        IElement anotherSquare = new Square(6, 6, "OtherSquare");
        board.addElement(originalSquare);
        board.addElement(anotherSquare);
        assertFalse(board.moveGizmo(originalSquare, new Vect(1, 1)));
        assertFalse(originalSquare.getOrigin().equals(anotherSquare.getOrigin()));
        assertTrue(board.moveGizmo(originalSquare, new Vect(2, 2)));
    }

    @Test
    public void testMoveAbsorberOff() throws Exception {
        testMoveAbsorberOffRight();
        testMoveAbsorberOffLeft();
        testMoveAbsorberOffTop();
        testMoveAbsorberOffBottom();
    }

    public void testMoveAbsorberOffRight() throws Exception {
        IElement originalAbsorber = new Absorber(5, 5, 6, 6, "TestSquare");
        board.addElement(originalAbsorber);
        //Right Side
        assertFalse(board.moveGizmo(originalAbsorber, new Vect(15, 0)));
        assertTrue(board.moveGizmo(originalAbsorber, new Vect(14, 0)));
    }

    public void testMoveAbsorberOffLeft() throws Exception {
        IElement originalAbsorber = new Absorber(5, 5, 6, 6, "TestSquare");
        board.addElement(originalAbsorber);
        //Left Side
        assertFalse(board.moveGizmo(originalAbsorber, new Vect(-6, 0)));
        assertTrue(board.moveGizmo(originalAbsorber, new Vect(-5, 0)));
    }

    public void testMoveAbsorberOffTop() throws Exception {
        IElement originalAbsorber = new Absorber(5, 5, 6, 6, "TestSquare");
        board.addElement(originalAbsorber);
        //Top Side
        assertFalse(board.moveGizmo(originalAbsorber, new Vect(0, 15)));
        assertTrue(board.moveGizmo(originalAbsorber, new Vect(0, 14)));
    }

    public void testMoveAbsorberOffBottom() throws Exception {
        IElement originalAbsorber = new Absorber(5, 5, 6, 6, "TestSquare");
        board.addElement(originalAbsorber);
        //Bottom Side
        assertFalse(board.moveGizmo(originalAbsorber, new Vect(0, -6)));
        assertTrue(board.moveGizmo(originalAbsorber, new Vect(0, -5)));

    }
}