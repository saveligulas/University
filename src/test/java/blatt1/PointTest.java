package blatt1;

import org.example.ub1.rect.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PointTest {
    private Point _a;
    private Point _b;

    @BeforeEach
    public void setUp() {
        _a = new Point(0, 2);
        _b = new Point(0, -2);
    }

    @Test
    public void testAddVector() {
        _a.addVector(_b);
        assertEquals(new Point(0, 0), _a);

        _a = new Point(1, 1);
        _b = new Point(1, -1);
        _a.addVector(_b);
        assertEquals(new Point(2, 0), _a);
    }

    @Test
    public void testDistance() {
        assertTrue(Point.distance(_a, _b) > 3.99 && Point.distance(_a, _b) < 4.01);
    }

    @Test
    public void testEquals() {
        assertFalse(_a.equals(_b));
        _b = new Point(0, 2);
        assertTrue(_a.equals(_b));
    }
}
