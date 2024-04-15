package org.example.coll.tuple;

public class TupleBidirectional<K> extends Tuple<K, K> {

    public TupleBidirectional(K husband, K spouse) {
        super(husband, spouse);
    }

    public boolean equalsOneValue(K k) {
        if (getFirst() == null || getSecond() == null) {
            return false;
        }
        return getFirst().equals(k) || getSecond().equals(k);
    }
}
