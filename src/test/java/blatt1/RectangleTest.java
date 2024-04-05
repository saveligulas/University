package blatt1;

import org.example.ub1.rect.Point;
import org.example.ub1.rect.Rectangle;
import org.example.ub1.rect.Triangle;
import org.example.ub1.tuple.Quadruplet;
import org.example.ub1.tuple.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class RectangleTest {
    private Rectangle _r;
    private final Point _top = new Point(0, 2);
    private final Point _bot = new Point(2, 0);

    @BeforeEach
    public void reset() {
        _r = new Rectangle();
    }

    private boolean checkTopAndBottom() {
        return _r.getTopCorner().getY() > _r.getBottomCorner().getY();
    }

    @Test
    public void testConstructor() {
        assertTrue(checkTopAndBottom());

        Point a = new Point(0, 0);
        Point b = new Point(0, 0);
        _r = new Rectangle(a, b);
        assertTrue(checkTopAndBottom());

        a = new Point(-2, 0);
        b = _top;
        _r = new Rectangle(a, b);
        assertTrue(checkTopAndBottom());
    }

    @Test
    public void testDimensions() {
        assertEquals(2, _r.getHorizontalLength());
        assertEquals(2, _r.getVerticalLength());
        _r = new Rectangle(_top, new Point(3, 0));
        assertEquals(3, _r.getHorizontalLength());
        assertEquals(2, _r.getVerticalLength());
    }

    @Test
    public void testIsInsideRectangle() {
        assertTrue(_r.isInsideRectangle(_top));
        assertTrue(_r.isInsideRectangle(_bot));
        assertTrue(_r.isInsideRectangle(new Point(1, 1)));
    }

    @Test
    public void testMovement() {
        Point vector = new Point(1, 1);
        _r.moveByTopCorner(vector);
        assertEquals(Point.addVector(vector, _top), _r.getTopCorner());
        assertEquals(Point.addVector(vector, _bot), _r.getBottomCorner());

        _r.moveTopCornerToPoint(new Point(0, 2));
        assertEquals(_top, _r.getTopCorner());
        assertEquals(_bot, _r.getBottomCorner());
    }

    @Test
    public void testIsQuadratic() {
        assertTrue(_r.isQuadratic());
        _r = new Rectangle(_top, new Point(3, 0));
        assertFalse(_r.isQuadratic());
    }

    @Test
    public void testCircumference() {
        assertEquals(8, _r.getCircumference());
        _r = new Rectangle(_top, new Point(3, 0));
        assertEquals(10, _r.getCircumference());
    }

    @Test
    public void testCircleCircumference() {
        double circumference = _r.getCircleCircumference();
        assertTrue(circumference > 8.8 && circumference < 8.9);

        _r = new Rectangle(_top, new Point(3, 0));
        //assertEquals(-1, _r.getCircleCircumference());
    }

    @Test
    public void testZoom() {
        _r.zoomFromBottomCorner(2);
        assertEquals(_top, _r.getTopCorner());
        assertEquals(new Point(4, -2), _r.getBottomCorner());
    }

    @Test
    public void testSplitIntoFour() {
        Quadruplet<Rectangle> result = _r.splitIntoFour();
        assertEquals(4, result.getFirst().getCircumference());
        assertEquals(4, result.getSecond().getCircumference());
        assertEquals(4, result.getThird().getCircumference());
        assertEquals(4, result.getFourth().getCircumference());

        _r = new Rectangle(_top, new Point(3, 0));
        assertNull(_r.splitIntoFour());
    }

    @Test
    public void testSplitIntoTrianglePair() {
        Tuple<Triangle, Triangle> result = _r.splitIntoTrianglePair();
        assertTrue(result.getFirst().getCircumference() > 6.8 && result.getFirst().getCircumference() < 6.9);
        assertTrue(result.getSecond().getCircumference() > 6.8 && result.getSecond().getCircumference() < 6.9);
    }
}
