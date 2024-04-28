package org.example;

public class Circle extends Shape {
    private int _centerX;
    private int _centerY;
    private int _radius;
    @Override
    public void move(int deltaX, int deltaY) throws ShapeOutOfBoundsException {
        int newX = _centerX + deltaX;
        int newY = _centerY + deltaY;
        if ((newX + _radius < 0 || newX + _radius >= DIMENSION) || newY + _radius < 0 || newY + _radius >= DIMENSION) {
            throw new ShapeOutOfBoundsException();
        }
        _centerX = newX;
        _centerY = newY;
    }
}
