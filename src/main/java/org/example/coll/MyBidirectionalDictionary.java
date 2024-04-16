package org.example.coll;

import java.util.Optional;

public class MyBidirectionalDictionary<K, V> extends MyDictionary<K, V> {
    protected MyCollection<Integer> findIndexInverted(V value) {
        return this.findIndexInverted(value, null);
    }

    protected MyCollection<Integer> findIndexInverted(V value, K exclude) {
        MyCollection<Integer> indices = new MyCollection<>();
        for (int i = 0; i < _values.size(); i++) {
            if (_values.get(i).equals(value) && !_keys.get(i).equals(exclude)) {
                indices.add(i);
            }
        }
        return indices;
    }

    public MyCollection<K> getKeys(V value) {
        MyCollection<Integer> indices = findIndexInverted(value);
        MyCollection<K> result = new MyCollection<>();

        for (Integer index : indices) {
            result.add(_keys.get(index));
        }

        return result;
    }

    public Optional<K> getFirstKeyExcludingKey(V value, K excludeKey) {
        Integer index;
        try {
            index = findIndexInverted(value, excludeKey).get(0);
        } catch (ArrayIndexOutOfBoundsException e) {
            return Optional.empty();
        }
        return Optional.of(_keys.get(index));
    }


    //TODO: move to other class that extends MyDictionary
//    public V get(K key) {
//        int index = super.findIndex(key);
//        if (index >= 0) {
//            return _values.get(index);
//        }
//        index = findIndexInverted(key);
//        if (index >= 0) {
//            return (V) _keys.get(index);
//        }
//        return null;
//    }

//    @Override
//    public boolean remove(K key) {
//        int index = super.findIndex(key);
//        if (index >= 0) {
//            super.remove(key);
//            return true;
//        } else {
//            index = findIndexInverted(key);
//            if (index >= 0) {
//                _values.removeIndex(index);
//                _keys.removeIndex(index);
//                return true;
//            }
//        }
//        return false;
//    }
}
