package blatt3;

import org.example.ub1.my.MyCollection;
import org.example.ub3.one.MyDictionary;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MyDictionaryTest {

    @Test
    public void testPutAndGet() {
        MyDictionary<String, Integer> dictionary = new MyDictionary<>();
        dictionary.put("key1", 1);
        dictionary.put("key2", 2);

        assertEquals(1, dictionary.get("key1"));
        assertEquals(2, dictionary.get("key2"));
    }

    @Test
    public void testUpdate() {
        MyDictionary<String, Integer> dictionary = new MyDictionary<>();
        dictionary.put("key1", 1);
        dictionary.put("key1", 2); // update value for existing key

        assertEquals(2, dictionary.get("key1"));
    }

    @Test
    public void testRemove() {
        MyDictionary<String, Integer> dictionary = new MyDictionary<>();
        dictionary.put("key1", 1);
        dictionary.put("key2", 2);
        dictionary.remove("key1");

        assertNull(dictionary.get("key1"));
        assertEquals(2, dictionary.get("key2"));
    }

    @Test
    public void testGetKeys() {
        MyDictionary<String, Integer> dictionary = new MyDictionary<>();
        dictionary.put("key1", 1);
        dictionary.put("key2", 2);

        MyCollection<String> keys = dictionary.getKeys();
        assertTrue(keys.contains("key1"));
        assertTrue(keys.contains("key2"));
    }

    @Test
    public void testGetValues() {
        MyDictionary<String, Integer> dictionary = new MyDictionary<>();
        dictionary.put("key1", 1);
        dictionary.put("key2", 2);

        MyCollection<Integer> values = dictionary.getValues();
        assertTrue(values.contains(1));
        assertTrue(values.contains(2));
    }
}

