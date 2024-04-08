package org.example;

import org.example.ub2.PrimeToolV2;

public class Main {
    public static void main(String[] args) {
        PrimeToolV2 tool = new PrimeToolV2();
        tool.expandPrimesMultithreaded(100);
    }


}