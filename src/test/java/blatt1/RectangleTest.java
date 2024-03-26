package blatt1;

import org.example.ub1.Point;
import org.example.ub1.Rectangle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RectangleTest {
    private Rectangle _r;
    private Random _random;

    @BeforeEach
    public void reset() {
        _r = new Rectangle();
        _random = new Random();
    }

    private boolean checkTopAndBottom() {
        return _r.getTopCorner().getY() > _r.getBottomCorner().getY();
    }

    @Test
    public void testConstructor() {
        assertTrue(checkTopAndBottom());
        for (int i = 0; i < 100; i++) {
            Point a = new Point(i, _random.nextInt(-100, 100));
            Point b = new Point(_random.nextInt(-100, 100), i);
            _r = new Rectangle(a, b);
            assertTrue(checkTopAndBottom());
        }
    }

    @Test
    public void testMovement() {
        for (int i = 0; i < 100; i++) {
            Point vector = new Point(_random.nextInt(-100, 100), _random.nextInt(-100, 100));
            Point top = new Point(_r.getTopCorner());
            Point bot = new Point(_r.getBottomCorner());
            _r.moveByTopCorner(vector);
            assertEquals(_r.getTopCorner(), Point.addVector(top, vector));
            assertEquals(_r.getBottomCorner(), Point.addVector(bot, vector));
        }
    }
}
