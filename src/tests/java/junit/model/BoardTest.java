package junit.model;

import model.*;
import model.gizmos.Absorber;
import model.gizmos.Square;
import org.junit.Before;
import org.junit.Test;
import physics.Vect;

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

    @Test
    public void testAddBall() throws Exception {

    }

    @Test
    public void testSetBalls() throws Exception {

    }

    @Test
    public void testGetBalls() throws Exception {

    }

    @Test
    public void testGetElements() throws Exception {

    }

    @Test
    public void testGetAllElements() throws Exception {

    }

    @Test
    public void testSetElements() throws Exception {

    }

    @Test
    public void testAddElement() throws Exception {

    }

    @Test
    public void testRemoveElement() throws Exception {

    }

    @Test
    public void testGetFrictionConst() throws Exception {

    }

    @Test
    public void testSetFrictionConst() throws Exception {

    }

    @Test
    public void testGetGravityConst() throws Exception {

    }

    @Test
    public void testSetGravityConst() throws Exception {

    }

    @Test
    public void testGetWidth() throws Exception {

    }

    @Test
    public void testSetWidth() throws Exception {

    }

    @Test
    public void testGetHeight() throws Exception {

    }

    @Test
    public void testSetHeight() throws Exception {

    }

    @Test
    public void testChanged() throws Exception {

    }

    @Test
    public void testGetMouseClick() throws Exception {

    }

    @Test
    public void testSetMouseClick() throws Exception {

    }

    @Test
    public void testGetMousePress() throws Exception {

    }

    @Test
    public void testSetMousePress() throws Exception {

    }

    @Test
    public void testGetMouseRelease() throws Exception {

    }

    @Test
    public void testSetMouseRelease() throws Exception {

    }

    @Test
    public void testGetSelectedElement() throws Exception {

    }

    @Test
    public void testSetSelectedElement() throws Exception {

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

    @Test
    public void testDetectEmptyArea() throws Exception {

    }

    @Test
    public void testDetectEmptyLocation() throws Exception {

    }

    @Test
    public void testSelectElement() throws Exception {

    }

    @Test
    public void testGetElementAtLocation() throws Exception {

    }

    @Test
    public void testGetElementAtLocation1() throws Exception {

    }
}