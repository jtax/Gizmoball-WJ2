package junit.model.gizmos;

import model.gizmos.Absorber;
import org.junit.Before;
import org.junit.Test;
import physics.Circle;
import physics.LineSegment;
import physics.Vect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by baird on 15/03/2016.
 */
public class AbsorberTest {
    Absorber absorber;
    @Before
    public void setUp() throws Exception {
        absorber = new Absorber(new Vect(0, 19),new Vect(20, 20), "Test");
    }

    @Test
    public void testAbsorber(){
        //See spreadsheet Test 56
        assert (absorber.getOrigin().equals(new Vect(0, 19)));
        assert (absorber.getName().equals("Test"));
        assert (absorber.getRotation() == 0);


    }

    @Test
    public void testCoordinates() throws Exception {
        //See spreadsheet Test 57
        List<LineSegment> actLines = absorber.getLines();
        LineSegment top = new LineSegment(0, 19, 20, 19);
        LineSegment right = new LineSegment(20, 19, 20, 20);
        LineSegment bottom = new LineSegment(20, 20, 0, 20);
        LineSegment left = new LineSegment(0, 20, 0, 19);
        List<LineSegment> expLines = Arrays.asList(top, right, bottom, left);
        assert actLines.size() == expLines.size();
        assert checkCoordinates(actLines, expLines);
    }

    private Boolean checkCoordinates(List<LineSegment> actLines, List<LineSegment> expLines) {
        Boolean status = true;
        for (int i = 0; i < expLines.size() - 1; i++) {
            status = status && actLines.contains(expLines.get(i));
        }
        return status;
    }

    @Test
    public void calculateCirclesTest() {
        //See spreadsheet Test 58
        List<Vect> expected = Arrays.asList(new Vect(0, 19), new Vect(20, 19), new Vect(20, 20),new Vect(0, 20));
        List<Vect> actual = new ArrayList<>();
        for (Circle circle : absorber.getCircles()) {
            actual.add(circle.getCenter());
        }
        assert (compareLists(actual, expected));
    }

    @Test
    public void calculateLinesTest(){
        List<LineSegment> actLines = absorber.getLines();
        LineSegment top = new LineSegment(0, 19, 20, 19);
        LineSegment right = new LineSegment(20, 19, 20, 20);
        LineSegment bottom = new LineSegment(20, 20, 0, 20);
        LineSegment left = new LineSegment(0, 20, 0, 19);
        List<LineSegment> expLines = Arrays.asList(top, right, bottom, left);
        assert compareLists(expLines,actLines);
    }

    private boolean compareLists(List actual, List expected) {
        if (actual.size() == expected.size()) {
            for (Object obj : actual) {
                if (!expected.contains(obj)) {
                    return false;
                }
            }
        }
        return true;
    }
}