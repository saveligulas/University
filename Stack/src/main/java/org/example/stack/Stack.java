package org.example.stack;

public class Stack {
    private int[] _values;
    private int _pointer;

    public Stack() {
        this(10);
    }

    public Stack(int size) {
        _values = new int[size];
        _pointer = 0;
    }

    public void push(int value) throws ArrayIndexOutOfBoundsException {
        _values[_pointer] = value;
        _pointer++;
    }

    public int pop() throws ArrayIndexOutOfBoundsException {
        _pointer--;
        return _values[_pointer];
    }

    public boolean isEmpty() {
        return _pointer == 0;
    }

    public boolean isFull() {
        return _pointer == _values.length;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = _pointer - 1; i >= 0; i--) {
            sb.append(_values[i]).append(" ");
        }
        return sb.toString();
    }
}
