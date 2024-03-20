package org.example;

import org.example.ub1.Point;
import org.example.ub1.Rectangle;

public class Main {
    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle(0, 2, 2, 0);
        rectangle.isQuadratic();
        rectangle.zoomByTopCorner(2);
        int circumference = rectangle.getCircumference();
        Rectangle rectangles[] = rectangle.splitIntoFour();
    }
}