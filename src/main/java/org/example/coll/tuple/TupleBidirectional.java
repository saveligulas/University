package org.example.coll.tuple;

public class TupleBidirectional<K> extends Tuple<K, K> {

    public TupleBidirectional(K k1, K k2) {
        super(k1, k2);
    }

    public boolean equalsOneValue(K k) {
        if (getFirst() == null || getSecond() == null) {
            return false;
        }
        return getFirst().equals(k) || getSecond().equals(k);
    }
}
