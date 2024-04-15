package org.example.coll;

import org.example.coll.MyCollection;

public class MyDictionary<K, V> {
    protected final MyCollection<K> _keys;
    protected final MyCollection<V> _values;

    public MyDictionary() {
        _keys = new MyCollection<K>();
        _values = new MyCollection<V>();
    }

    protected int findIndex(K key) {
        for (int i = 0; i < _keys.size(); i++) {
            if (_keys.get(i).equals(key)) {
                return i;
            }
        }
        return -1;
    }

    public void update(K key, V value) {
        int index = findIndex(key);
        if (index >= 0) {
            _values.set(index, value);
        }
    }

    public void put(K key, V value) {
        if (_keys.contains(key)) {
            update(key, value);
        } else {
            _keys.add(key);
            _values.add(value);
        }
    }

    public V get(K key) {
        int index = findIndex(key);
        if (index >= 0) {
            return _values.get(index);
        }
        return null;
    }

    public boolean remove(K key) {
        int index = findIndex(key);
        if (index >= 0) {
            _keys.remove(key);
            _values.remove(_values.get(index));
            return true;
        }
        return false;
    }

    public int size() {
        return _keys.size();
    }

    public void clear() {
        for (K key : _keys) {
            this.remove(key);
        }
    }

    public MyCollection<K> getKeys() {
        return new MyCollection<>(_keys);
    }

    public MyCollection<V> getValues() {
        return new MyCollection<>(_values);
    }
}
