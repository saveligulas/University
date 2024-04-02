package org.example.ub1;

public class Rectangle {
    private final Point _topCorner;
    private final Point _bottomCorner;

    //region <Constructor>

    public Rectangle() {
        this(0, 2, 2, 0);
    }

    public Rectangle(int xTop, int yTop, int xBot, int yBot) {
        this(new Point(xTop, yTop), new Point(xBot, yBot));
    }

    public Rectangle(Point top, Point bottom) {
        if (top.getY() < bottom.getY()) {
            Point temp = top;
            top = bottom;
            bottom = temp;
        }
        if (top.getY() == bottom.getY()) {
            top = new Point(top.getX(), top.getY() + 1);
        }
        if (top.getX() == bottom.getX()) {
            bottom = new Point(bottom.getX() + 1, bottom.getY());
        }
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

    public void moveTopCornerToPoint(int x, int y) {
        moveByTopCorner(new Point(x - _topCorner.getX(), y - _topCorner.getY()));
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

    public double getCircleCircumference() {
        if (!isQuadratic()) {
            return -1;
        }
        Point center = getCenter();
        Point vector = new Point(center.getX() - _topCorner.getX(), center.getY() - _topCorner.getY());
        double length = Math.sqrt(vector.getX() * vector.getX() + vector.getY() * vector.getY());
        return 2 * length * Math.PI;
    }

    public boolean isQuadratic() {
        return getHorizontalLength() == getVerticalLength();
    }

    public void zoomByTopCorner(int factor) {
        _bottomCorner.addVector(new Point(factor, -1 * factor));
    }

    private Point getCenter() {
        return new Point(_topCorner.getX() + (isRightwards() ? 1 : -1) * getHorizontalLength(), _topCorner.getY() - getVerticalLength() / 2);
    }

    private Point getOtherTopCorner() {
        return Point.addVector(_topCorner, new Point(getHorizontalLength(), 0));
    }

    private Point getOtherBottomCorner() {
        return Point.addVector(_topCorner, new Point(0, -getVerticalLength()));
    }

    public Tuple<Tuple<Rectangle, Rectangle>, Tuple<Rectangle, Rectangle>> splitIntoFour() {
        if (getHorizontalLength() % 2 != 0 || getVerticalLength() % 2 != 0) {
            return null;
        }
        Point center = getCenter();
        Rectangle[] rectangles = new Rectangle[4];
        rectangles[0] = new Rectangle(new Point(_topCorner), center);
        rectangles[1] = new Rectangle(getOtherTopCorner(), new Point(center));
        rectangles[2] = new Rectangle(new Point(center), getOtherBottomCorner());
        rectangles[3] = new Rectangle(new Point(center), new Point(_bottomCorner));
        return new Tuple<>(new Tuple<>(rectangles[0], rectangles[1]), new Tuple<>(rectangles[2], rectangles[3]));
    }

    public Tuple<Triangle, Triangle> splitIntoTrianglePair() {
        Triangle a = new Triangle(_topCorner, _bottomCorner, getOtherTopCorner());
        Triangle b = new Triangle(_topCorner, _bottomCorner, getOtherBottomCorner());
        return new Tuple<>(a, b);
    }

    public Point getTopCorner() {
        return _topCorner;
    }

    public Point getBottomCorner() {
        return _bottomCorner;
    }
}
