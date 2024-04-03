package org.example;

import org.example.ub1.rect.Rectangle;
import org.example.ub1.cheese.CheeseHoleFinder;
import org.example.ub1.my.MyCollection;
import org.example.ub1.three.container.Item;
import org.example.ub1.three.container.ItemContainer;
import org.example.ub1.three.container.ItemType;

public class Main {
    public static void main(String[] args) {

        char[][] grid = {
                {'+', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '+'},
                {'|', ' ', '*', '*', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '*', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
                {'|', ' ', '*', ' ', '*', ' ', '*', ' ', '*', ' ', '*', ' ', '*', ' ', '*', ' ', ' ', ' ', '|'},
                {'|', ' ', '*', '*', '*', ' ', ' ', ' ', ' ', ' ', '*', '*', '*', ' ', ' ', ' ', ' ', ' ', '|'},
                {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
                {'|', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '|'},
                {'+', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '+'}
        };

        CheeseHoleFinder tester = new CheeseHoleFinder();
        MyCollection<Integer> holes = tester.getHoles(grid);
        for (Integer hole : holes) {
            System.out.println(hole);
        }
        Rectangle r = new Rectangle();
        System.out.println(r.getCircleCircumference());
        ItemContainer container = new ItemContainer(3, 500);
        for (int i = 0; i < 5; i++) {
            container.add(new Item(ItemType.LAPTOP, 100));
        }
        System.out.println(container.isFull());
    }

}