package col;

import org.example.coll.MyBidirectionalDictionary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MyBidirectionalDictionaryTest {
    private MyBidirectionalDictionary<String, String> dictionary;

    @BeforeEach
    public void setUp() {
        dictionary = new MyBidirectionalDictionary<>();
        dictionary.put("key1", "value1");
        dictionary.put("key2", "value2");
    }

    @Test
    public void testGet_KeyFoundInForwardDirection() {
        String value = dictionary.get("key1");
        assertEquals("value1", value);
    }

    @Test
    public void testGet_KeyFoundInInvertedDirection() {
        String value = dictionary.get("value2");
        assertEquals("key2", value);
    }

    @Test
    public void testGet_KeyNotFound() {
        String value = dictionary.get("key3");
        assertNull(value);
    }

    @Test
    public void testRemove() {
        dictionary.remove("key1");

        assertNull(dictionary.get("key1"));
        assertNull(dictionary.get("value1"));

        assertEquals("value2", dictionary.get("key2"));
        assertEquals("key2", dictionary.get("value2"));
    }

    @Test
    public void testRemoveNonExistent() {
        MyBidirectionalDictionary<String, String> dictionary = new MyBidirectionalDictionary<>();
        dictionary.put("key1", "value1");
        dictionary.put("key2", "value2");

        dictionary.remove("nonexistent");

        assertEquals("value1", dictionary.get("key1"));
        assertEquals("key1", dictionary.get("value1"));
        assertEquals("value2", dictionary.get("key2"));
        assertEquals("key2", dictionary.get("value2"));
    }

    @Test
    public void testRemoveInverted() {
        MyBidirectionalDictionary<String, String> dictionary = new MyBidirectionalDictionary<>();
        dictionary.put("key1", "value1");
        dictionary.put("key2", "value2");

        dictionary.remove("value1");

        assertNull(dictionary.get("key1"));
        assertNull(dictionary.get("value1"));

        assertEquals("key2", dictionary.get("value2"));
        assertEquals("value2", dictionary.get("key2"));
    }
}

