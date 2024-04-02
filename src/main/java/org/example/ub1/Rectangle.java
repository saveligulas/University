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

    //region <Private Methods>

    private boolean isRightwards() {
        return _topCorner.getX() < _bottomCorner.getX();
    }

    private int getHorizontalLength() {
        return Math.abs(_topCorner.getX() - _bottomCorner.getX());
    }

    private int getVerticalLength() {
        return Math.abs(_topCorner.getY() - _bottomCorner.getY());
    }

    private Point getCenter() {
        return new Point(_topCorner.getX() + (isRightwards() ? 1 : -1) * (getHorizontalLength() / 2), _topCorner.getY() - (getVerticalLength() / 2));
    }

    private Point getOtherTopCorner() {
        return Point.addVector(_topCorner, new Point(getHorizontalLength() * (isRightwards() ? 1 : -1), 0));
    }

    private Point getOtherBottomCorner() {
        return Point.addVector(_bottomCorner, new Point(getHorizontalLength() * (isRightwards() ? -1 : 1), 0));
    }

    //endregion

    public void moveTopCornerToPoint(int x, int y) {
        moveTopCornerToPoint(new Point(x, y));
    }

    public void moveTopCornerToPoint(Point point) {
        moveByTopCorner(new Point(point.getX() - _topCorner.getX(), point.getY() - _topCorner.getY()));
    }

    public void moveByTopCorner(int x, int y) {
        moveByTopCorner(new Point(x, y));
    }

    public void moveByTopCorner(Point moveVector) {
        _topCorner.addVector(moveVector);
        _bottomCorner.addVector(moveVector);
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
        double length = Math.sqrt((vector.getX() * vector.getX()) + (vector.getY() * vector.getY()));
        return 2 * length * Math.PI;
    }

    public boolean isQuadratic() {
        return getHorizontalLength() == getVerticalLength();
    }

    public void zoomFromBottomCorner(double factor) {
        factor = factor - 1;
        Point vector = new Point((int) (getHorizontalLength() * factor * (isRightwards() ? 1 : -1)), (int) (getVerticalLength() * (factor * -1)));
        _bottomCorner.addVector(vector);
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
