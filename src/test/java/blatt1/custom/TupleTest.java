package blatt1.custom;

import org.example.ub1.tuple.Tuple;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TupleTest {
    @Test
    public void testTupleCreation() {
        Tuple<Integer, String> tuple = new Tuple<>(1, "test");
        assertEquals(1, tuple.getFirst());
        assertEquals("test", tuple.getSecond());
    }

    @Test
    public void testSettersAndGetters() {
        Tuple<Integer, String> tuple = new Tuple<>();
        tuple.setFirst(10);
        tuple.setSecond("new value");
        assertEquals(10, tuple.getFirst());
        assertEquals("new value", tuple.getSecond());
    }

    @Test
    public void testNullValues() {
        Tuple<Integer, String> tuple = new Tuple<>();
        assertNull(tuple.getFirst());
        assertNull(tuple.getSecond());

        tuple.setFirst(5);
        assertNotNull(tuple.getFirst());
        assertNull(tuple.getSecond());

        tuple.setSecond("value");
        assertNotNull(tuple.getFirst());
        assertNotNull(tuple.getSecond());
    }
}
