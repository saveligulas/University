package org.example.ub1;

public class Rectangle {
    private Point _topCorner;
    private Point _bottomCorner;

    public Rectangle() {
        this(0, 2, 2, 0);
    }

    public Rectangle(int xTop, int yTop, int xBot, int yBot) {
        this(new Point(xTop, yTop), new Point(xBot, yBot));
    }

    public Rectangle(Point top, Point bottom) {
        _topCorner = top;
        _bottomCorner = bottom;
    }

    private boolean isRightwards() {
        return _topCorner.getX() < _bottomCorner.getX();
    }

    public void moveByTopCorner(int x, int y) {
        moveByTopCorner(new Point(x, y));
    }

    public void moveByTopCorner(Point moveVector) {
        _topCorner.addVector(moveVector);
        _bottomCorner.addVector(moveVector);
    }

    private int getHorizontalLength() {
        return Math.abs(_topCorner.getX()) + Math.abs(_bottomCorner.getX());
    }

    private int getVerticalLength() {
        return Math.abs(_topCorner.getY()) + Math.abs(_bottomCorner.getY());
    }

    public int getCircumference() {
        return (getHorizontalLength() * 2 + getVerticalLength() * 2);
    }

    public boolean isQuadratic() {
        return getHorizontalLength() == getVerticalLength();
    }

    public void zoomByTopCorner(int factor) {
        _bottomCorner.addVector(new Point(1 * factor, -1 * factor));
    }

    private Point getCenter() {
        return new Point(getHorizontalLength() / 2 + (isRightwards() ? 1 : -1) * _topCorner.getX(), getVerticalLength() / 2 + _bottomCorner.getY());
    }

    public Rectangle[] splitIntoFour() {
        if (getHorizontalLength() % 2 != 0 || getVerticalLength() % 2 != 0) {
            return null;
        }
        Point center = getCenter();
        Rectangle result[] = new Rectangle[3];
        result[0] = new Rectangle(Point.addVector(_topCorner, new Point(getHorizontalLength(), 0)), new Point(center));
        result[1] = new Rectangle(new Point(center), Point.addVector(_topCorner, new Point(0, -getVerticalLength())));
        result[2] = new Rectangle(new Point(center), new Point(_bottomCorner));
        _bottomCorner = center;
        return result;
    }
}
