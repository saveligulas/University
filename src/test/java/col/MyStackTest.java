package col;

import org.example.stack.MyStack;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MyStackTest {
    @Test
    public void testPushAndPop() {
        MyStack<Integer> stack = new MyStack<>(5);
        assertTrue(stack.isEmpty());

        stack.push(1);
        assertFalse(stack.isEmpty());
        assertEquals(1, stack.size());

        stack.push(2);
        stack.push(3);
        assertEquals(3, stack.size());

        assertEquals(3, stack.pop());
        assertEquals(2, stack.pop());
        assertFalse(stack.isFull());
        assertEquals(1, stack.size());

        stack.push(4);
        stack.push(5);
        assertFalse(stack.isFull());
        assertEquals(3, stack.size());

        assertEquals(5, stack.pop());
        assertEquals(4, stack.pop());
        assertEquals(1, stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test
    public void testIsFull() {
        MyStack<String> stack = new MyStack<>(3);
        assertFalse(stack.isFull());

        stack.push("a");
        assertFalse(stack.isFull());

        stack.push("b");
        assertFalse(stack.isFull());

        stack.push("c");
        assertTrue(stack.isFull());
    }

    @Test
    public void testToString() {
        MyStack<Character> stack = new MyStack<>(5);
        assertEquals("", stack.toString());

        stack.push('a');
        assertEquals("a ", stack.toString());

        stack.push('b');
        assertEquals("b a ", stack.toString());

        stack.push('c');
        assertEquals("c b a ", stack.toString());
    }

    @Test
    public void testPopEmptyStack() {
        MyStack<Double> stack = new MyStack<>(3);
        assertThrows(ArrayIndexOutOfBoundsException.class, stack::pop);
    }
}
