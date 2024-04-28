package org.example;

public interface CanBeMoved {
    int DIMENSION = 100;

    void move(int deltaX, int deltaY) throws ShapeOutOfBoundsException;
}
