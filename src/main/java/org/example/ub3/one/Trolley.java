package org.example.ub3.one;

import org.example.coll.MyCollection;
import org.example.ub1.rect.Point;
import org.example.coll.MyCollectionArray;
import org.example.ub3.one.pro.Product;
import org.example.ub3.one.pro.ProductType;

import java.util.Optional;

public class Trolley {
    private Point _position;
    private final MyCollectionArray<Product> _inventory;

    public Trolley(TrainNetwork network, Point position, int size) {
        if (network.DIMENSIONS.getCircumference() < 8) {
            network = new TrainNetwork(100, 100);
        }

        if (!network.DIMENSIONS.isInsideRectangle(position)) {
            position = network.DIMENSIONS.getCenter();
        }
        _position = position;

        _inventory = new MyCollectionArray<Product>(size);
    }

    public void pickupProducts(MyCollection<Product> products, TrainJob job) {
        for (Product product : products) {
            if (job.types.contains(product.type())) {
                _inventory.add(product);
                products.remove(product);
                job.found();
            }
        }
    }

    public void addVector(Point vector) {
        _position.addVector(vector);
    }

    public void moveTo(Point position) {
        _position = position;
    }

    public MyCollection<Product> getInventory() {
        return _inventory;
    }

    public MyCollection<Product> getInventoryAndClear() {
        MyCollection<Product> inventory = new MyCollection<>(_inventory);
        _inventory.clear();
        return inventory;
    }

    public MyCollection<Product> getInventoryAndClearWithTarget(Point target) {
        MyCollection<Product> productsWithTarget = new MyCollection<>();
        for (Product product : _inventory) {
            if (product.destination().equals(target)) {
                productsWithTarget.add(product);
                _inventory.remove(product);
            }
        }
        return productsWithTarget;
    }

    public Point getPosition() {
        return _position;
    }
}
