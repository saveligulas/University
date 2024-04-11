package org.example.coll.tuple;

public class TupleBidirectional<K, V extends K> extends Tuple<K, V> {

    public boolean equalsOneValue(K k) {
        if (getFirst() == null || getSecond() == null) {
            return false;
        }
        return getFirst().equals(k) || getSecond().equals(k);
    }
}
