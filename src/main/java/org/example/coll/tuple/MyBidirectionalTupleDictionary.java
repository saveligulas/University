package org.example.coll.tuple;

import org.example.coll.MyBidirectionalDictionary;
import org.example.coll.MyCollection;
import org.example.coll.MyDictionary;

public class MyBidirectionalTupleDictionary<T, K extends TupleBidirectional<T>,V > extends MyDictionary<K, V> {
    private MyCollection<Integer> findIndices(T genericKey) {
        MyCollection<Integer> indices = new MyCollection<>();
        for (int i = 0; i < _keys.size(); i++) {
            if (_keys.get(i).equalsOneValue(genericKey)) {
                indices.add(i);
            }
        }
        return indices;
    }

    public MyCollection<V> getBidirectional(T twoWayKey) {
        MyCollection<Integer> indices = findIndices(twoWayKey);
        MyCollection<V> result = new MyCollection<>();
        for (Integer index : indices) {
            result.add(_values.get(index));
        }
        return result;
    }

    public MyCollection<T> getRelatedKeys(T twoWayKey) {
        MyCollection<T> result = new MyCollection<>();
        for (Integer index : findIndices(twoWayKey)) {
            T otherKey;
            TupleBidirectional<T> tuple = _keys.get(index);
            if (tuple.getFirst().equals(twoWayKey)) {
                otherKey = tuple.getFirst();
            } else {
                otherKey = tuple.getSecond();
            }
            result.add(otherKey);
        }
        return result;
    }
}
