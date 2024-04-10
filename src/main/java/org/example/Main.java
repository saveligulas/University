package org.example;

import org.example.ub1.rect.Point;
import org.example.ub2.NameGenerator;
import org.example.ub2.PrimeToolV2;
import org.example.ub3.one.TrainJob;
import org.example.ub3.one.TrainNetwork;
import org.example.ub3.one.Trolley;
import org.example.ub3.one.pro.ProductType;

public class Main {
    public static void main(String[] args) {
//        PrimeToolV2 tool = new PrimeToolV2();
//        System.out.println(tool.expandPrimesTime(1000000) + "ms");
//        NameGenerator gen = new NameGenerator(5);
//        int count = 0;
//        while (true) {
//            count++;
//            try {
//                gen.generateUniqueName();
//            } catch (Exception e) {
//                System.out.println("Found duplicate at " + count);
//                break;
//            }
//        }
        TrainNetwork network = new TrainNetwork(100, 100);
        network.fillWithRandomProducts();
        Trolley trolley = new Trolley(network, new Point(0, 0), 500);
        TrainJob job = new TrainJob(ProductType.IRON, 400);
        trolley.moveTo(new Point(99, 99), job);
        System.out.println();
    }


}