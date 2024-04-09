package org.example;

import org.example.ub2.NameGenerator;
import org.example.ub2.PrimeToolV2;

public class Main {
    public static void main(String[] args) {
//        PrimeToolV2 tool = new PrimeToolV2();
//        System.out.println(tool.expandPrimesTime(1000000) + "ms");
        NameGenerator gen = new NameGenerator(5);
        int count = 0;
        while (true) {
            count++;
            try {
                gen.generateUniqueName();
            } catch (Exception e) {
                System.out.println("Found duplicate at " + count);
                break;
            }
        }
    }


}