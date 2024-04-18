package org.example;

import org.example.coll.MyBidirectionalDictionary;
import org.example.coll.MyCollection;
import org.example.coll.MyDictionary;
import org.example.stack.StackCalculator;
import org.example.ub1.rect.Point;
import org.example.ub1.three.app.Gender;
import org.example.ub2.NameGenerator;
import org.example.ub2.PrimeToolV2;
import org.example.ub3.one.*;
import org.example.ub3.one.pro.ProductType;
import org.example.ub3.two.MarriageBureau;
import org.example.ub3.two.Person;
import org.example.ub3.twov2.Human;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        TrainManager manager = TrainManagerFactory.createTrainManager(3, 3, 2, 2, 1000);
        System.out.println(manager);




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
//        TrainNetwork network = new TrainNetwork(100, 100);
//        network.fillWithRandomProducts();
//        Trolley trolley = new Trolley(network, new Point(0, 0), 500);
//        TrainJob job = new TrainJob(ProductType.IRON, 400);
//        trolley.moveTo(new Point(99, 99), job);
//        System.out.println();
    }


}