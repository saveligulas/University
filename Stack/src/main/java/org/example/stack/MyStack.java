package org.example.stack;

public class MyStack<T> {
    private Object[] _data;
    private int _pointer;

    public MyStack(int size) {
        _data = new Object[size];
        _pointer = 0;
    }

    public int size() {
        return _pointer;
    }

    public void push(T item) {
        if (_pointer < _data.length) {
            _data[_pointer] = item;
            _pointer++;
        }
    }

    public boolean isEmpty() {
        return _pointer == 0;
    }

    public boolean isFull() {
        return _pointer == _data.length;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = _pointer - 1; i >= 0; i--) {
            sb.append(_data[i]).append(" ");
        }
        return sb.toString();
    }

    @SuppressWarnings("unchecked")
    public T pop() {
        _pointer--;
        if (_pointer == -1) {
            throw new NullPointerException();
        }
        return (T) _data[_pointer];
    }
}
