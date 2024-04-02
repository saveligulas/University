package org.example.ub1;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

public class Point {
    //region <Fields>
    private int _xCoordinate;
    private int _yCoordinate;
    //endregion

    //region <Constructors>
    public Point(int x, int y) {
        _xCoordinate = x;
        _yCoordinate = y;
    }

    public Point(Point p) {
        _xCoordinate = p.getX();
        _yCoordinate = p.getY();
    }
    //endregion

    public void addVector(Point point) {
        _xCoordinate += point.getX();
        _yCoordinate += point.getY();
    }

    //region <Static Methods>
    public static Point addVector(Point point, Point vector) {
        return new Point(point.getX() + vector.getX(), point.getY() + vector.getY());
    }

    public static double distance(Point a, Point b) {
        return Math.sqrt(Math.pow(a.getX() - b.getX(), 2) + Math.pow(a.getY() - b.getY(), 2));
    }
    //endregion

    //region <Getters>
    public int getX() {
        return _xCoordinate;
    }

    public int getY() {
        return _yCoordinate;
    }
    //endregion

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Point point)) {
            return false;
        }
        return point.getX() == _xCoordinate && point.getY() == _yCoordinate;
    }
}
