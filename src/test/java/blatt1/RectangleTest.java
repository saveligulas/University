package blatt1;

import org.example.ub1.Point;
import org.example.ub1.Rectangle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

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
        Point a = new Point(0, 0);
        Point b = new Point(0, 0);
        assertTrue(checkTopAndBottom());
        for (int i = 0; i < 100; i++) {
            a = new Point(i, _random.nextInt(-100, 100));
            b = new Point(_random.nextInt(-100, 100), i);
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

    @Test
    public void testIsQuadratic() {
        for (int i = 1; i < 100; i++) {
            boolean sameLength = _random.nextBoolean();
            Point top = new Point(0, i);
            Point bot = new Point(sameLength ? i : i + 1, 0);
            _r = new Rectangle(top, bot);
            boolean isQuadratic = _r.isQuadratic();
            if (sameLength) {
                assertTrue(isQuadratic);

            } else {
                assertFalse(isQuadratic);
            }
        }
    }

    @Test
    public void testQuadraticCircumferences() {
        int circumference = _r.getCircumference();
        int length = 2;

        BigDecimal circle;
        BigDecimal catheter = BigDecimal.valueOf(1);
        BigDecimal radius;
        BigDecimal circleCalc;
        for (int i = 0; i < 100; i++) {
            assertEquals(length * 4, circumference);

            Point top = _r.getTopCorner();
            Point bot = Point.addVector(_r.getBottomCorner(), new Point(1, -1));
            _r = new Rectangle(top, bot);

            catheter = catheter.add(BigDecimal.valueOf(0.5));
            radius = BigDecimal.valueOf(Math.sqrt(catheter.pow(2).multiply(BigDecimal.TWO).doubleValue()));
            circleCalc = radius.multiply(BigDecimal.TWO).multiply(BigDecimal.valueOf(Math.PI));
            circle = BigDecimal.valueOf(_r.getCircleCircumference());
            assertEquals(circleCalc.round(MathContext.DECIMAL32), circle.round(MathContext.DECIMAL32));

            circumference = _r.getCircumference();
            length++;
        }
    }

    @Test
    public void testHorizontalCircumference() {
        int circumference = _r.getCircumference();
        int initialCircumference = circumference;
        for (int i = 1; i < 100; i++) {
            Point top = _r.getTopCorner();
            Point bot = Point.addVector(_r.getBottomCorner(), new Point(1, 0));
            _r = new Rectangle(top, bot);
            circumference = _r.getCircumference();
            assertEquals(initialCircumference + (i * 2), circumference);
        }
    }

    @Test
    public void testVerticalCircumference() {
        int circumference = _r.getCircumference();
        int initialCircumference = circumference;
        for (int i = 1; i < 100; i++) {
            Point top = _r.getTopCorner();
            Point bot = Point.addVector(_r.getBottomCorner(), new Point(0, -1));
            _r = new Rectangle(top, bot);
            circumference = _r.getCircumference();
            assertEquals(initialCircumference + (i * 2), circumference);
        }
    }
}
