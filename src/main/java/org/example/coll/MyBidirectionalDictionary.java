package org.example.coll;

public class MyBidirectionalDictionary<K, V> extends MyDictionary<K, V> {
    protected int findIndexInverted(K key) {
        for (int i = 0; i < _values.size(); i++) {
            if (_values.get(i).equals(key)) {
                return i;
            }
        }
        return -1;
    }

    public V get(K key) {
        int index = super.findIndex(key);
        if (index >= 0) {
            return _values.get(index);
        }
        index = findIndexInverted(key);
        if (index >= 0) {
            return (V) _keys.get(index);
        }
        return null;
    }

    @Override
    public boolean remove(K key) {
        int index = super.findIndex(key);
        if (index >= 0) {
            super.remove(key);
            return true;
        } else {
            index = findIndexInverted(key);
            if (index >= 0) {
                _values.removeIndex(index);
                _keys.removeIndex(index);
                return true;
            }
        }
        return false;
    }
}
