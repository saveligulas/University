package org.example.ub1;

public class Triangle {
    private Point _a;
    private Point _b;
    private Point _c;

    public Triangle(Point a, Point b, Point c) {
        _a = a;
        _b = b;
        _c = c;
    }

    public double getCircumference() {
        return (Point.distance(_a, _b)) + Point.distance(_b, _c) + Point.distance(_c, _a);
    }
}
