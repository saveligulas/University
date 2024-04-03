package org.example.ub1.my;

import java.util.Iterator;

public class MyCollection<T> implements Iterable<T> {
    private Object[] _data;
    private int _pointer;

    public MyCollection() {
        this(1);
    }

    public MyCollection(int size) {
        _data = new Object[size];
        _pointer = 0;
    }

    private void increaseSize() {
        int size;
        if (_data.length < 100) {
            size = _data.length * 2;
        } else {
            size = _data.length + (int) Math.sqrt(_data.length) + 1;
        }
        Object[] newData = new Object[size];
        System.arraycopy(_data, 0, newData, 0, _data.length);
        _data = newData;
    }

    public void add(T e) {
        if (_pointer == _data.length) {
            increaseSize();
        }
        _data[_pointer] = e;
        _pointer++;
    }

    public void add(MyCollection<T> collection) {
        for (T e : collection) {
            this.add(e);
        }
    }

    public T get(int index) throws ArrayIndexOutOfBoundsException {
        return (T) _data[index];
    }

    public int size() {
        return _pointer;
    }

    public void remove(T e) {
        for (int i = 0; i < _pointer; i++) {
            if (_data[i].equals(e)) {
                if (i != _pointer - 1) {
                    for (int j = i; j < _pointer - 1; j++) {
                        _data[j] = _data[j + 1];
                    }
                }
                _pointer--;
            }
        }
    }

    public boolean contains(T e) {
        for (int i = 0; i < _pointer; i++) {
            if (_data[i].equals(e)) {
                return true;
            }
        }
        return false;
    }

    public void clear() {
        _data = new Object[1];
        _pointer = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < _pointer;
            }

            @Override
            public T next() {
                return (T) _data[currentIndex++];
            }
        };
    }
}
