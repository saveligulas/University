package blatt3;

import org.example.coll.MyBidirectionalDictionary;
import org.example.coll.MyCollection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class MyBidirectionalDictionaryTest {
    @Test
    void testGetKeys() {
        MyBidirectionalDictionary<String, Integer> dictionary = new MyBidirectionalDictionary<>();
        dictionary.put("apple", 1);
        dictionary.put("banana", 2);
        dictionary.put("cherry", 1);

        MyCollection<String> keys = dictionary.getKeys(1);
        Assertions.assertEquals(2, keys.size());
        Assertions.assertTrue(keys.contains("apple"));
        Assertions.assertTrue(keys.contains("cherry"));
    }

    @Test
    void testGetFirstKeyExcludingKey() {
        MyBidirectionalDictionary<String, Integer> dictionary = new MyBidirectionalDictionary<>();
        dictionary.put("apple", 1);
        dictionary.put("banana", 2);
        dictionary.put("cherry", 1);

        Optional<String> firstKey = dictionary.getFirstKeyExcludingKey(1, "apple");
        Assertions.assertTrue(firstKey.isPresent());
        Assertions.assertEquals("cherry", firstKey.get());

        // Testing case where excludeKey is not present
        Optional<String> getsFirstKey = dictionary.getFirstKeyExcludingKey(1, "grape");
        Assertions.assertEquals("apple", getsFirstKey.get());
    }
}
