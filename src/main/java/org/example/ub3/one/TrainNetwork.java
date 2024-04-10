package org.example.ub3.one;

import org.example.coll.MyDictionary;
import org.example.ub1.rect.Point;
import org.example.ub1.rect.Rectangle;
import org.example.ub3.one.pro.Product;
import org.example.ub3.one.pro.ProductType;

import java.util.Optional;
import java.util.Random;

public class TrainNetwork {
    public final Rectangle DIMENSIONS;
    private final MyDictionary<Point, Optional<Product>> _pointProductMap;

    public TrainNetwork(int height, int width) {
        DIMENSIONS = new Rectangle(new Point(width - 1, height - 1), new Point(0, 0));
        _pointProductMap = new MyDictionary<>();
        fillMap(height, width);
    }

    private void fillMap(int height, int width) {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                Point p = new Point(row, col);
                _pointProductMap.put(p, Optional.empty());
            }
        }
    }

    public void fillWithRandomProducts() {
        Random r = new Random();
        for (int row = 0; row < DIMENSIONS.getVerticalLength() + 1; row++) {
            for (int col = 0; col < DIMENSIONS.getHorizontalLength() + 1; col++) {
                Point p = new Point(row, col);
                if (r.nextBoolean()) {
                    ProductType t = ProductType.values()[r.nextInt(ProductType.values().length)];
                    _pointProductMap.put(p, Optional.of(new Product(t)));
                } else {
                    _pointProductMap.put(p, Optional.empty());
                }
            }
        }
    }

    public Optional<Product> getProduct(Point point) {
        if (!DIMENSIONS.isInsideRectangle(point)) {
            return Optional.empty();
        }
        return _pointProductMap.get(point);
    }

    public void setPosition(Point point, Product product) {
        _pointProductMap.put(new Point(point), Optional.of(product));
    }

    public void clearPosition(Point point) {
        _pointProductMap.put(new Point(point), Optional.empty());
    }


}
