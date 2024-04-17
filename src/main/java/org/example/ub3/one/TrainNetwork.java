package org.example.ub3.one;

import org.example.coll.MyCollection;
import org.example.coll.MyDictionary;
import org.example.ub1.rect.Point;
import org.example.ub1.rect.Rectangle;
import org.example.ub3.one.pro.Product;
import org.example.ub3.one.pro.ProductType;

import java.util.Optional;
import java.util.Random;

public class TrainNetwork {
    public final Rectangle DIMENSIONS;
    private final MyDictionary<Point, MyCollection<Product>> _pointProductMap;

    public TrainNetwork(int height, int width) {
        DIMENSIONS = new Rectangle(new Point(width - 1, height - 1), new Point(0, 0));
        _pointProductMap = new MyDictionary<>();
        fillMap(height, width);
    }

    private void fillMap(int height, int width) {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                Point p = new Point(row, col);
                _pointProductMap.put(p, new MyCollection<>());
            }
        }
    }

    public MyCollection<Product> getProducts(Point point) {
        if (!DIMENSIONS.isInsideRectangle(point)) {
            return new MyCollection<>();
        }
        return _pointProductMap.get(point);
    }

    public boolean addProduct(Point point, Product product) {
        if (!DIMENSIONS.isInsideRectangle(point)) {
            return false;
        }
        _pointProductMap.get(point).add(product);
        return true;
    }

    public void clearPosition(Point point) {
        _pointProductMap.get(point).clear();
    }


}
