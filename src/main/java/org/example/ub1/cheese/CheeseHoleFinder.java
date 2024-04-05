package org.example.ub1.cheese;

import org.example.ub1.my.MyCollection;

public class CheeseHoleFinder {
    private final MyCollection<Point> _stars = new MyCollection<>();
    private final MyCollection<MyCollection<Point>> _holes = new MyCollection<>();

    public void clear() {
        _stars.clear();
        _holes.clear();
    }

    public MyCollection<Integer> getHoles(char[][] grid) {
        clear();
        findStars(grid);
        orderStars();
        return convertHolesToIntegerCollection();
    }

    private MyCollection<Integer> convertHolesToIntegerCollection() {
        MyCollection<Integer> holeSizes = new MyCollection<>();
        for (MyCollection<Point> hole : _holes) {
            holeSizes.add(hole.size());
        }
        return holeSizes;
    }

    private void findStars(char[][] grid) {
        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid[row].length; column++) {
                if (grid[row][column] == '*') {
                    _stars.add(new Point(row, column));
                }
            }
        }
    }

    private void orderStars() {
        for (Point point : _stars) {
            boolean wasAdded = false;
            for (MyCollection<Point> pointCollection : _holes) {
                if (isNeighbour(pointCollection, point)) {
                    pointCollection.add(point);
                    wasAdded = true;
                }
            }
            if (!wasAdded) {
                MyCollection<Point> newHole = new MyCollection<>();
                newHole.add(point);
                _holes.add(newHole);
            }
        }
    }

    private boolean isNeighbour(MyCollection<Point> points, Point p) {
        for (Point p1 : points) {
            if (Math.abs(p1.row() - p.row()) <= 1 && Math.abs(p1.column() - p.column()) <= 1) {
                return true;
            }
        }
        return false;
    }
}
