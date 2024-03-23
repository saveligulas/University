package org.example;

import org.example.stack.Stack;
import org.example.ub1.CheeseTester;
import org.example.ub1.Point;
import org.example.ub1.Rectangle;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle(-1, 2, 1, 0);
        rectangle.isQuadratic();
        int circumference = rectangle.getCircumference();
        Rectangle[] rectangles = rectangle.splitIntoFour();
        int circumferenceSmaller = rectangles[2].getCircumference();

        Stack stack = new Stack();
        System.out.println(stack.isEmpty());
        stack.push(1314);
        stack.push(2312);
        System.out.println(stack.isEmpty());
        System.out.println(stack.isFull());
        for (int i = 0; i < 8; i++) {
            stack.push(1);
        }
        System.out.println(stack.isFull());
        System.out.println(stack);
        for (int j = 0; j < 10; j++) {
            System.out.println(stack.pop());
        }
        try {
            System.out.println(stack.pop());
        } catch(Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        System.out.println(stack.isEmpty());
    }
}