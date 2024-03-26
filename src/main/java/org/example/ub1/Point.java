package org.example.ub1;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

public class Point {

    private int _xCoordinate;
    private int _yCoordinate;


    public Point(int x, int y) {
        _xCoordinate = x;
        _yCoordinate = y;
    }

    public Point(Point p) {
        _xCoordinate = p.getX();
        _yCoordinate = p.getY();
    }

    public void addVector(Point point) {
        _xCoordinate += point.getX();
        _yCoordinate += point.getY();
    }

    public static Point addVector(Point point, Point vector) {
        return new Point(point.getX() + vector.getX(), point.getY() + vector.getY());
    }

    public int getX() {
        return _xCoordinate;
    }

    public int getY() {
        return _yCoordinate;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Point point)) {
            return false;
        }
        return point.getX() == _xCoordinate && point.getY() == _yCoordinate;
    }
}
