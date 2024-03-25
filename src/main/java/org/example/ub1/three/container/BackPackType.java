package org.example.ub1.three.container;

public enum BackPackType {
    STANDARD(10), LARGE(20);
    private int _size;

    BackPackType(int size) {
        _size = size;
    }

    public int getSize() {
        return _size;
    }
}
