package org.example.ub1.rect;

import org.example.ub1.tuple.Quadruplet;
import org.example.ub1.tuple.Tuple;

public class Rectangle {
    //region <Fields>
    private final Point _topCorner;
    private final Point _bottomCorner;
    //endregion

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

    //region <Methods>

    //region <Private Methods>
    private boolean isRightwards() {
        return _topCorner.getX() < _bottomCorner.getX();
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

    //region <Dimension>
    public int getHorizontalLength() {
        return Math.abs(_topCorner.getX() - _bottomCorner.getX());
    }

    public int getVerticalLength() {
        return Math.abs(_topCorner.getY() - _bottomCorner.getY());
    }

    public boolean isInsideRectangle(Point point) {
        boolean x = isRightwards() ? point.getX() >= _topCorner.getX() && point.getX() <= _bottomCorner.getX() :
                point.getX() <= _topCorner.getX() && point.getX() >= _bottomCorner.getX();
        boolean y = point.getY() <= _topCorner.getY() && point.getY() >= _bottomCorner.getY();
        return x && y;
    }
    //endregion

    //region <Movement>
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
    //endregion

    //region <Circumference>
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
    //endregion

    public boolean isQuadratic() {
        return getHorizontalLength() == getVerticalLength();
    }

    public void zoomFromBottomCorner(double factor) {
        factor = factor - 1;
        Point vector = new Point((int) (getHorizontalLength() * factor * (isRightwards() ? 1 : -1)), (int) (getVerticalLength() * (factor * -1)));
        _bottomCorner.addVector(vector);
    }


    //region <Splitting>
    public Quadruplet<Rectangle> splitIntoFour() {
        if (getHorizontalLength() % 2 != 0 || getVerticalLength() % 2 != 0) {
            return null;
        }
        Point center = getCenter();
        Quadruplet<Rectangle> result = new Quadruplet<>();
        result.setFirst(new Rectangle(new Point(_topCorner), center));
        result.setSecond(new Rectangle(getOtherTopCorner(), new Point(center)));
        result.setThird(new Rectangle(new Point(center), getOtherBottomCorner()));
        result.setFourth(new Rectangle(new Point(center), new Point(_bottomCorner)));
        return result;
    }

    public Tuple<Triangle, Triangle> splitIntoTrianglePair() {
        Triangle a = new Triangle(_topCorner, _bottomCorner, getOtherTopCorner());
        Triangle b = new Triangle(_topCorner, _bottomCorner, getOtherBottomCorner());
        return new Tuple<>(a, b);
    }
    //endregion
    //endregion

    //region <Getters>
    public Point getTopCorner() {
        return _topCorner;
    }

    public Point getBottomCorner() {
        return _bottomCorner;
    }
    //endregion
}
