package blatt1.custom;

import org.example.ub1.my.MyCollection;
import org.example.ub1.my.MyCollectionArray;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MyCollectionArrayTest {

    @Test
    public void testAdd() {
        MyCollectionArray<Integer> collection = new MyCollectionArray<>(2);
        collection.add(1);
        assertFalse(collection.isFull());
        collection.add(2);
        assertTrue(collection.isFull());
        assertTrue(collection.contains(1));
        assertTrue(collection.contains(2));
        collection.add(3);
        assertFalse(collection.contains(3));
        assertEquals(collection.SIZE, collection.size());
    }

    @Test
    public void testAddCollection() {
        MyCollectionArray<Integer> collection = new MyCollectionArray<>(3);
        collection.add(1);
        MyCollection<Integer> collection2 = new MyCollection<>();
        collection2.add(2);
        collection2.add(3);
        collection2.add(4);
        collection.add(collection2);
        assertEquals(1, collection.size());
        collection2.remove(4);
        collection.add(collection2);
        assertTrue(collection.isFull());
    }
}
