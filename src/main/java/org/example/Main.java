package org.example;

import org.example.print.*;
import org.example.ub1.rect.Rectangle;
import org.example.ub1.cheese.CheeseHoleFinder;
import org.example.ub1.my.MyCollection;
import org.example.ub1.three.container.Item;
import org.example.ub1.three.container.ItemContainer;
import org.example.ub1.three.container.ItemType;
import org.example.ub2.PrimeTool;
import org.example.ub2.PrimeToolV2;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        PrimeToolV2 tool = new PrimeToolV2();
        tool.getPrimes(2, 100);
    }


}