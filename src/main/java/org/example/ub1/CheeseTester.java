package org.example.ub1;

import java.util.ArrayList;
import java.util.List;

public class CheeseTester {
    private static char[][] _currentCheeseGrid;
    public static List<Integer> checkCheese(char[][] cheese) {
        _currentCheeseGrid = cheese;
        List<Integer> cheeseHoles = new ArrayList<>();
        for (int j = 0; j < cheese.length; j++) {
            for (int i = 0; i < cheese[j].length; i++) {
                if (cheese[j][i] == '*') {
                    int size = exploreCheeseHoleSize(j, i);
                    cheeseHoles.add(size);
                }
            }
        }
        return cheeseHoles;
    }

    private static int exploreCheeseHoleSize(int startX, int startY) {
        int currentX = startX;
        int currentY = startY;
        int[] yOffSets = {-1, 0, 1};
        int size = 1;
        do {
            for (int xOffSet = 1; xOffSet > -2; xOffSet--) {
                int xToCheck = currentX + xOffSet;
                for (int yOffSet : yOffSets) {
                    currentY += yOffSet;
                    int yToCheck = currentY + yOffSet;
                    if (!(xToCheck == currentX && yToCheck == currentY)) {
                        if (_currentCheeseGrid[currentX][yOffSet] == '*') {
                            size++;
                            currentX += xOffSet;
                            currentY += yOffSet;
                            break;
                        }
                    }
                }
            }
        } while (currentX != startX && currentY != startY);
        return size;
    }
}
