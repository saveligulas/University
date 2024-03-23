package org.example.ub1;

public class Rectangle {
    private Point _topCorner;
    private Point _bottomCorner;

    //region <Constructor>

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

    //endregion

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
        int xTop = _topCorner.getX();
        int xBot = _bottomCorner.getX();
        return xTop > 0 ? xTop + (-1 * xBot) : Math.abs(xTop) + xBot;
    }

    private int getVerticalLength() {
        int yTop = _topCorner.getY();
        int yBot = _bottomCorner.getY();
        return yTop >= 0 ? yTop + (-1 * yBot) : Math.abs(yTop) + yBot;
    }

    public int getCircumference() {
        return (getHorizontalLength() * 2 + getVerticalLength() * 2);
    }

    public boolean isQuadratic() {
        return getHorizontalLength() == getVerticalLength();
    }

    public void zoomByTopCorner(int factor) {
        _bottomCorner.addVector(new Point(factor, -1 * factor));
    }

    private Point getCenter() {
        return new Point(getHorizontalLength() / 2 + (isRightwards() ? 1 : -1) * _topCorner.getX(), getVerticalLength() / 2 + _bottomCorner.getY());
    }

    private Point getOtherTopCorner() {
        return Point.addVector(_topCorner, new Point(getHorizontalLength(), 0));
    }

    private Point getOtherBottomCorner() {
        return Point.addVector(_topCorner, new Point(0, -getVerticalLength()));
    }

    public Rectangle[] splitIntoFour() {
        if (getHorizontalLength() % 2 != 0 || getVerticalLength() % 2 != 0) {
            return null;
        }
        Point center = getCenter();
        Rectangle[] result = new Rectangle[3];
        result[0] = new Rectangle(Point.addVector(_topCorner, new Point(getHorizontalLength(), 0)), new Point(center));
        result[1] = new Rectangle(new Point(center), Point.addVector(_topCorner, new Point(0, -getVerticalLength())));
        result[2] = new Rectangle(new Point(center), new Point(_bottomCorner));
        _bottomCorner = center;
        return result;
    }

    public Triangle[] splitIntoTrianglePair() {
        return null;
    }
}
