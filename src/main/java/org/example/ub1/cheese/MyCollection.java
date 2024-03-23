package org.example.ub1.cheese;

public class MyCollection<T> {
    private Object[] _data;
    private int _pointer;

    public MyCollection() {
        _data = new Object[1];
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

    public void clear() {
        _data = new Object[1];
        _pointer = 0;
    }

    public T[] toArray() {
        T[] array = (T[]) new Object[_pointer];
        System.arraycopy(_data, 0, array, 0, _pointer);
        return array;
    }
}
