package blatt1;

import org.example.ub1.cheese.MyCollection;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class MyCollectionTest {
    @Test
    public void testAddAndGet() {
        MyCollection<Integer> collection = new MyCollection<>();
        collection.add(1);
        collection.add(2);
        assertEquals(Integer.valueOf(1), collection.get(0));
        assertEquals(Integer.valueOf(2), collection.get(1));
    }

    @Test
    public void testContains() {
        MyCollection<String> collection = new MyCollection<>();
        collection.add("apple");
        collection.add("banana");
        assertTrue(collection.contains("apple"));
        assertFalse(collection.contains("orange"));
    }

    @Test
    public void testSize() {
        MyCollection<Double> collection = new MyCollection<>();
        collection.add(3.14);
        collection.add(2.71);
        assertEquals(2, collection.size());
    }

    @Test
    public void testRemove() {
        MyCollection<Character> collection = new MyCollection<>();
        collection.add('a');
        collection.add('b');
        collection.remove('a');
        assertFalse(collection.contains('a'));
        assertEquals(1, collection.size());
    }

    @Test
    public void testClear() {
        MyCollection<Integer> collection = new MyCollection<>();
        collection.add(1);
        collection.add(2);
        collection.clear();
        assertEquals(0, collection.size());
        assertFalse(collection.contains(1));
    }

    @Test
    public void testAddAll() {
        MyCollection<Integer> collection1 = new MyCollection<>();
        collection1.add(1);
        collection1.add(2);

        MyCollection<Integer> collection2 = new MyCollection<>();
        collection2.add(3);
        collection2.add(4);

        collection1.add(collection2);
        assertEquals(4, collection1.size());
        assertTrue(collection1.contains(3));
        assertTrue(collection1.contains(4));
    }

    @Test
    public void testIterator() {
        MyCollection<String> collection = new MyCollection<>();
        collection.add("Item1");
        collection.add("Item2");
        collection.add("Item3");
        Iterator<String> iterator = collection.iterator();
        assertEquals("Item1", iterator.next());
        assertEquals("Item2", iterator.next());
        assertEquals("Item3", iterator.next());
        assertFalse(iterator.hasNext());
    }
}
