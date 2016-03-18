package junit.model;

import model.*;
import model.gizmos.*;
import org.junit.Before;
import org.junit.Test;
import physics.Vect;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
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
        assertTrue(b.getAllElements().contains(Wall.class));
    }

    @Test
    public void checkThatWallCoordsAreCorrect() {
        //See spreadsheet Test 5
        Board b = new Board();
        IElement wall = (IElement)b.getElements().toArray()[0];
        Vect topLeft = new Vect(0, 0);
        Vect bottomRight = new Vect(20, 20);

        assertEquals(topLeft, wall.getCoordinates().get(0));
        assertEquals(bottomRight, wall.getCoordinates().get(2));
    }

    @Test
    public void checkThatWhenABallIsAddedTheBallsListContainsThatBall() {
        //See spreadsheet Test 7 & 8
        Board b = new Board();
        IElement ball = new Ball("B", 1.0, 11.0, 0.0, 0.0);
        int ballCount = b.getBalls().size();
        b.addBall((Ball) ball);

        assertTrue(b.getBalls().contains(ball));
        assertTrue(ballCount+1 == b.getBalls().size());
    }

    @Test
    public void testSetBall() {
        //See spreadsheet Test  9
        Board b = new Board();
        IElement baw = new Ball("B", 2.0, 13.0, 0.0, 0.0);
        b.addBall((Ball) baw);
        IElement ball = new Ball("B", 1.0, 11.0, 0.0, 0.0);
        Collection<Ball> nb = new LinkedList<>();
        nb.add((Ball)ball);
        b.setBalls(nb);
        Collection<Ball> otherBawList = new LinkedList<>();
        otherBawList.add((Ball)baw);

        assertEquals(nb, (LinkedList)b.getBalls());
        assertNotEquals(otherBawList, (LinkedList)b.getBalls());
    }

    @Test
    public void checkThatYouCanReturnBallsAndGizmos() {
        //See spreadsheet Test 10 && 11 && 12
        Board b = new Board();

        IElement ball = new Ball("B", 1.0, 11.0, 0.0, 0.0);
        b.addBall((Ball)ball);

        Vect origin = new Vect(10, 10);

        IElement square = new Square(origin, "pablo");
        assertTrue(b.addElement(square));

        assertTrue(b.getAllElements().contains(ball));
        assertTrue(b.getAllElements().contains(square));
        assertEquals(3, b.getAllElements().size());
    }

    @Test
    public void checkAllElementsAreAdded_setElements() {
        //See spreadsheet Test 12
        Board b = new Board();
        List<IElement> elements= new ArrayList<>();

        Vect pos1 = new Vect(3,17);
        IElement cir = new Circle(pos1, "c1");
        elements.add(cir);

        Vect pos2 = new Vect(1,17);
        IElement sqr = new Square(pos2, "s1");
        elements.add(sqr);

        Vect pos3 = new Vect(2,16);
        IElement tri = new Triangle(pos3, "t1");
        elements.add(tri);

        Vect pos4 = new Vect(2,18);
        IElement fli = new Flipper(pos4, "f1");
        elements.add(fli);

        b.setElements(elements);

        for(int i = 0; i < elements.size(); i++){
            IElement someElement =  elements.get(i);
            assertTrue(b.getElements().contains(someElement));
        }
    }


    @Test
    public void checkAllElementsFromBeforeSetAreRemoved_setElements() {
        //See spreadsheet Test 13
        Board b = new Board();

        Vect pos = new Vect(3,17);
        IElement cir = new Circle(pos, "c1");
        b.addElement(cir);

        List<IElement> elements= new ArrayList<>();

        Vect pos3 = new Vect(2,16);
        IElement tri = new Triangle(pos3, "t1");
        elements.add(tri);

        Vect pos4 = new Vect(2,18);
        IElement fli = new Flipper(pos4, "f1");
        elements.add(fli);

        b.setElements(elements);

        assertFalse(b.getElements().contains(cir));
    }

    @Test
    public void checkWallsAreReAdded_setElements() {
        //See spreadsheet Test 14
        Board b = new Board();
        List<IElement> elements= new ArrayList<>();

        Vect pos1 = new Vect(3,17);
        IElement cir = new Circle(pos1, "c1");
        elements.add(cir);

        Vect pos2 = new Vect(1,17);
        IElement sqr = new Square(pos2, "s1");
        elements.add(sqr);

        Vect pos3 = new Vect(2,16);
        IElement tri = new Triangle(pos3, "t1");
        elements.add(tri);

        Vect pos4 = new Vect(2,18);
        IElement fli = new Flipper(pos4, "f1");
        elements.add(fli);

        b.setElements(elements);
        boolean found = false;
        for (IElement elem : b.getAllElements()) {
            if (elem instanceof Wall) {
                found = true;
            }
        }
        assertTrue(found);
    }

    @Test
    public void addElementToEmptySquare() {
        //See spreadsheet Test 15
        Board b = new Board();
        Vect origin = new Vect(10, 10);
        IElement square = new Square(origin, "S1");

        b.addElement(square);

        assertEquals(square,b.getElementAtLocation(origin));
    }

    @Test
    public void addElementOnTopOfAnotherGizmo() {
        //See spreadsheet Test 16
        Board b = new Board();
        Vect origin = new Vect(10, 10);
        IElement square = new Square(origin, "S1");
        b.addElement(square);

        IElement triangle = new Triangle(origin, "T1");
        assertFalse(b.addElement(triangle));
    }

    @Test
    public void addElementOutOfBoardBoundsLeft() {
        //See spreadsheet Test 17
        Board b = new Board();
        Vect origin = new Vect(-10, 10);
        IElement square = new Square(origin, "S1");
        assertFalse(b.addElement(square));
    }

    @Test
    public void addElementOutOfBoardBoundsRight() {
        //See spreadsheet Test 17
        Board b = new Board();
        Vect origin = new Vect(21, 10);
        IElement square = new Square(origin, "S1");
        assertFalse(b.addElement(square));
    }

    @Test
    public void addElementOutOfBoardBoundsBottom() {
        //See spreadsheet Test 17
        Board b = new Board();
        Vect origin = new Vect(10, 22);
        IElement square = new Square(origin, "S1");
        assertFalse(b.addElement(square));
    }

    @Test
    public void addElementOutOfBoardBoundsTop() {
        //See spreadsheet Test 17
        Board b = new Board();
        Vect origin = new Vect(10, -10);
        IElement square = new Square(origin, "S1");
        assertFalse(b.addElement(square));
    }

    @Test
    public void removeElementAndCheckForExistencePSItShouldnt() {
        //See spreadsheet Test 18
        Board b = new Board();
        Vect origin = new Vect(10, 10);
        IElement square = new Square(origin, "S1");
        b.addElement(square);
        b.removeElement(square);
        assertFalse(square == b.getElementAtLocation(origin));

    }

    @Test
    public void removeElementAndMakeSureYouCanNowAddOneOnItsExLocation() {
        //See spreadsheet Test 19
        //See spreadsheet Test 18
        Board b = new Board();
        Vect origin = new Vect(10, 10);

        IElement square = new Square(origin, "S1");
        b.addElement(square);
        b.removeElement(square);

        IElement circle = new Circle(origin, "C1");
        assertTrue(b.addElement(circle));
        assertTrue(circle == b.getElementAtLocation(origin));
    }

    @Test
    public void setFrictionAndSeeIfYouGetWhatYouSet() {
        //See spreadsheet Test 20
        Board b = new Board();
        double[] friction = new double[]{0.01,0.02};
        b.setFrictionConst(new double[]{0.01,0.02});
        assertTrue(friction[0] == b.getFrictionConst()[0]);
        assertTrue(friction[1] == b.getFrictionConst()[1]);
    }

    @Test
    public void setGravityAndSeeIfYouGetWhatYouSet() {
        //See spreadsheet Test 21
        Board b = new Board();
        double gravity = 20;
        b.setGravityConst(20);
        assertTrue(gravity == b.getGravityConst());

    }

    @Test
    public void setWidthAndSeeIfYouGetWhatYouSet() {
        //See spreadsheet Test 22
        Board b = new Board();
        double width = 10;
        b.setWidth(10);
        assertTrue(width == b.getWidth());
    }

    @Test
    public void getsetHeight() {
        //See spreadsheet Test 23
        Board b = new Board();
        double height = 11;
        b.setHeight(11);
        assertTrue(height == b.getHeight());
    }

    @Test
    public void moveAGizmoFromOneLocationToAnother() {
        //See spreadsheet Test 24
        Board b = new Board();
        Vect origin = new Vect(10, 10);

        IElement tri = new Triangle(origin, "T1");
        b.addElement(tri);
        Vect moveDistance = new Vect(-1,-3);
        b.moveGizmo(tri, moveDistance);
        Vect newLocation = new Vect(9,7);
        assertTrue(tri == b.getElementAtLocation(newLocation));
    }

    @Test
    public void moveGizmoOnTopOfAnotherGizmo() {
        //See spreadsheet Test 25
        Board b = new Board();

        Vect originTri = new Vect(10, 10);
        IElement tri = new Triangle(originTri, "T1");
        b.addElement(tri);

        Vect originCircle = new Vect(12, 13);
        IElement circle = new Circle(originCircle, "C1");
        b.addElement(circle);

        Vect moveDistance = new Vect(-2,-3);
        assertFalse(b.moveGizmo(circle, moveDistance));
    }

    @Test
    public void canYouMoveGizmoOffTheGameBoard() {
        //See spreadsheet Test 26
        Board b = new Board();

        Vect originTri = new Vect(10, 10);
        IElement tri = new Triangle(originTri, "T1");
        b.addElement(tri);

        Vect originCircle = new Vect(12, 13);
        IElement circle = new Circle(originCircle, "C1");
        b.addElement(circle);

        Vect moveDistance = new Vect(-22,-33);
        assertFalse(b.moveGizmo(circle, moveDistance));
    }

    @Test
    public void detectToSeeIfWeCanPickUpEmptyAreas() {
        //See spreadsheet Test 27
        Board b = new Board();

        Vect origin = new Vect(2,2);
        Vect bound = new Vect(18,18);

        assertTrue(b.detectEmptyArea(origin, bound));
    }

    @Test
    public void detectToSeeIfWeCanPickUpFullAreas() {
        //See spreadsheet Test 28
        Board b = new Board();

        Vect origin = new Vect(2,2);
        Vect bound = new Vect(18,18);
        Vect originCircle = new Vect(4,4);

        IElement circle = new Circle(originCircle, "C1");
        b.addElement(circle);
        assertFalse(b.detectEmptyArea(origin, bound));
    }

    @Test
    public void detectEmptyAreaOutsideOfBoard() {
        //See spreadsheet Test 29
        Board b = new Board();

        Vect bound = new Vect(-2, -2);
        Vect origin = new Vect(-4, -4);

        assertFalse(b.detectEmptyArea(origin, bound));
    }

    @Test
    public void detectEmptySquareOnBoard() {
        //See spreadsheet Test 30
        //See spreadsheet Test 27
        Board b = new Board();

        Vect origin = new Vect(2,2);

        assertTrue(b.detectEmptyLocation(origin));
    }

    @Test
    public void detectEmptySquareWhenTheSquareHasAGizmoInIt() {
        //See spreadsheet Test 31
        Board b = new Board();

        Vect origin = new Vect(2,2);

        IElement flipper = new Flipper(origin, "F1");
        b.addElement(flipper);

        assertFalse(b.detectEmptyLocation(origin));
    }

    @Test
    public void detectEmptySquareWhenTheSquareIsOutsideTheBoard() {
        //See spreadsheet Test 32
        Board b = new Board();

        Vect origin = new Vect(22,22);

        assertFalse(b.detectEmptyLocation(origin));
    }

    @Test
    public void getElementAtLocationWithXY_WhenElementIsSquare() {
        //See spreadsheet Test33
        Board b = new Board();

        Vect origin = new Vect(12,2);

        IElement square = new Square(origin, "s1");
        b.addElement(square);
        IElement squareComp = new Square(origin, "s3");

        assertTrue(squareComp.equals(b.getElementAtLocation(12,2)));
    }

    @Test
    public void getElementAtLocationWithXY_WhenElementIsFlipper() {
        //See spreadsheet Test 34
        Board b = new Board();

        Vect origin = new Vect(2,16);
        Vect originX = new Vect(2,17);

        IElement flip = new Flipper(origin, "F1");
        b.addElement(flip);
        IElement flipComp = new Flipper(origin, "F2");

        assertTrue(flipComp.equals(b.getElementAtLocation(2,17)));
    }

    @Test
    public void getElementAtLocationWithXY_WhenElementIsAbsorber() {
        //See spreadsheet Test 35
        Board b = new Board();

        Vect origin = new Vect(6,18);
        Vect originX = new Vect(18,19);

        IElement absorber = new Absorber(origin,originX, "A1");
        b.addElement(absorber);

        assertTrue(absorber.equals(b.getElementAtLocation(6,18)));
    }

    @Test
    public void getElementAtLocationWithXY_WhenElementIsBall() {
        //See spreadsheet Test 36
        Board b = new Board();

        Vect centre = new Vect(2,10);
        Vect velocity = new Vect(0.025, 0.025);

        IElement ball = new Ball("ball", centre, velocity);
        b.addBall((Ball)ball);
       // assertTrue(ball.equals(b.getElementAtLocation(2,10)));
    }

    @Test
    public void getElementAtLocationWithXY_WhenItsAWallSoShouldNotWork() {
        //See spreadsheet Test 37
        Board b = new Board();

        //you can't get a wall as it's not
        // physically at a location on board
        assertEquals(null,b.getElementAtLocation(20,20));
    }

    @Test
    public void getElementAtLocationWithXY_WhenThereIsNoElementThere() {
        //See spreadsheet Test 38
        Board b = new Board();
        assertTrue(null == b.getElementAtLocation(2,17));
    }

    @Test
    public void getElementAtLocationWithXY_WhenThereIsNoElementThereButThereAreSomeOnBoard() {
        //See spreadsheet Test 38
        Board b = new Board();

        Vect posRight = new Vect(3,17);
        IElement cir = new Circle(posRight, "c1");
        b.addElement(cir);

        Vect posLeft = new Vect(1,17);
        IElement sqr = new Square(posLeft, "s1");
        b.addElement(sqr);

        Vect posAbove = new Vect(2,16);
        IElement tri = new Triangle(posAbove, "t1");
        b.addElement(tri);

        Vect posBelow = new Vect(2,18);
        IElement fli = new Flipper(posBelow, "f1");
        b.addElement(fli);

        assertTrue(null == b.getElementAtLocation(2,17));
    }

    @Test
    public void getElementAtLocation7() {
        //See spreadsheet Test 39
        Vect position = new Vect(5, 5);
        IElement square = new Square(position, "Square");
        board.addElement(square);
        assertTrue(board.getElementAtLocation(position).equals(square));
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
        Board b = new Board();
        Ball b1 = new Ball("ball",1,1,1,1);
        Square s = new Square(5,5,"t");
        b.addBall(b1);
        b.addElement(s);
        b.clear();
        assert(b.getElements().size() == 1);
        assert(b.getBalls().isEmpty());
    }

    @Test
    public void clear2() {
        //See spreadsheet Test 46
        Board b = new Board();
        Ball b1 = new Ball("ball",1,1,1,1);
        Square s = new Square(5,5,"t");
        b.addBall(b1);
        b.addElement(s);
        b.clear();
        assert(b.getElements().size() == 1);

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