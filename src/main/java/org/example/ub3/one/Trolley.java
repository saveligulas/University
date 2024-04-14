package org.example.ub3.one;

import org.example.coll.MyCollection;
import org.example.ub1.rect.Point;
import org.example.coll.MyCollectionArray;
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

    private int getProductAmount(ProductType type) {
        int amount = 0;
        for (Product product : _inventory) {
            if (product.type() == type) {
                amount++;
            }
        }
        return amount;
    }

    private void checkPosition(TrainJob job) {
        Optional<Product> product = _network.getProducts(_position);
        if (product.isPresent() && product.get().type() == job.type() && getProductAmount(job.type()) < job.target()) {
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

        checkPosition(job);
        while (!_position.equals(target)) {
            int x = 0;
            int y = 0;
            if (_position.getX() != target.getX()) {
                x = rightwards ? 1 : -1;
            }
            if (_position.getY() != target.getY()) {
                y = upwards ? 1 : -1;
            }
            _position.addVector(new Point(x, y));
            checkPosition(job);
        }
    }

    public MyCollection<Product> getInventory() {
        return _inventory;
    }

    public Point getPosition() {
        return _position;
    }
}
