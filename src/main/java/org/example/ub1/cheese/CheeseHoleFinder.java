package org.example.ub1.cheese;

public class CheeseHoleFinder {
    private final MyCollection<Point> _stars = new MyCollection<>();
    private final MyCollection<MyCollection<Point>> _holes = new MyCollection<>();

    public MyCollection<Integer> getHoles(char[][] grid) {
        _stars.clear();
        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid[row].length; column++) {
                if (grid[row][column] == '*') {
                    _stars.add(new Point(row, column));
                }
            }
        }

    }

    private void orderStars() {

    }
}
