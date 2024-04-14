package org.example.ub3.one;

import org.example.coll.MyCollection;
import org.example.coll.MyDictionary;
import org.example.ub1.rect.Point;
import org.example.ub1.rect.Rectangle;
import org.example.ub3.one.pro.Product;
import org.example.ub3.one.pro.ProductType;

import java.util.Random;

public class TrainManagerFactory {
    public TrainManager createTrainManager(int height, int width, int trolleyCount, int hubCount, int productAmount) {
        TrainNetwork network = new TrainNetwork(height, width);
        MyCollection<Trolley> trolleys = new MyCollection<>();
        MyDictionary<Point, TrainManager.TrainHub> pointHubs = new MyDictionary<>();
        Random rand = new Random();

        for (int i = 0; i < trolleyCount; i++) {
            trolleys.add(new Trolley(network, new Point(0, 0), (int) Math.ceil(productAmount / 10)));
        }

        for (int i = 0; i < hubCount; i++) {
            int x = rand.nextInt(width);
            int y = rand.nextInt(height);
            pointHubs.put(new Point(x, y), new TrainManager.TrainHub());
        }

        for (int i = 0; i < productAmount; i++) {
            int x = rand.nextInt(width);
            int y = rand.nextInt(height);
            network.addProduct(new Point(x, y),
                    new Product(ProductType.values()[rand.nextInt(ProductType.values().length)],
                            pointHubs.getKeys().get(rand.nextInt(pointHubs.getKeys().size()))));
        }

        return null;
    }
}
