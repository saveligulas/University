package org.example.ub3.one;

import org.apache.commons.math3.util.MathArrays;
import org.example.ub1.rect.Point;
import org.example.ub1.rect.Rectangle;
import org.example.ub2.container.MyCollectionArray;
import org.example.ub3.one.pro.Product;
import org.example.ub3.one.pro.ProductType;

import java.util.Optional;

public class Trolley {
    private TrainNetwork _network;
    private Point _position;
    private final MyCollectionArray<Product> _inventory;

    public Trolley(TrainNetwork network, Point position, int size) {
        if (network.DIMENSIONS.getCircumference() < 8) {
            network = new TrainNetwork(100, 100);
        }
        _network = network;

        if (!_network.DIMENSIONS.isInsideRectangle(position)) {
            position = _network.DIMENSIONS.getCenter();
        }
        _position = position;

        _inventory = new MyCollectionArray<Product>(size);
    }

    private void checkPosition(ProductType type) {
        Optional<Product> product = _network.getProduct(_position);
        if (product.isPresent() && product.get().TYPE == type) {
            _inventory.add(product.get());
            _network.clearPosition(_position);
        }
    }

    public void moveTo(Point position) {
        _position = position;
    }

    public void moveTo(Point target, TrainJob job) {
        int originX = _position.getX();
        int originY = _position.getY();
        boolean rightwards = originX < target.getX();
        boolean upwards = originY < target.getY();
        for (int i = 0; i < Math.abs(originX - target.getX()); i++) {
            int x = rightwards ? 1 : -1;
            _position.addVector(new Point(x, 0));
            checkPosition(job.type());
        }
        for (int i = 0; i < Math.abs(originY - target.getY()); i++) {
            int y = upwards ? 1 : -1;
            _position.addVector(new Point(0, y));
            checkPosition(job.type());
        }
    }
}
