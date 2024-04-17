package org.example.ub3.one;

import org.example.coll.MyBidirectionalDictionary;
import org.example.coll.MyCollection;
import org.example.coll.MyCollectionArray;
import org.example.coll.MyDictionary;
import org.example.ub1.rect.Point;
import org.example.ub3.one.pro.Product;
import org.example.ub3.one.pro.ProductType;

public class TrainManager {
    private final TrainNetwork _network;
    private final MyCollection<Trolley> _trolleys;
    private final MyBidirectionalDictionary<Point, TrainHub> _pointHubs;

    TrainManager(TrainNetwork network, MyCollection<Trolley> trolleys, MyBidirectionalDictionary<Point, TrainHub> pointHubs) {
        _network = network;
        _trolleys = trolleys;
        _pointHubs = pointHubs;
    }

    public void driveToNearestHub(int trolleyIndex) {
        this.driveToNearestHub(_trolleys.get(trolleyIndex));
    }

    public void driveToNearestHub(Trolley trolley) {

    }

    public void completeJob(Point pointOfTrainHub, TrainJob job) {
        this.completeJob(_pointHubs.get(pointOfTrainHub), job);
    }

    public void completeJob(TrainHub hub, TrainJob job) {

    }

    private MyCollection<Product> moveTrolleyAndGetProducts(int index, Point target, TrainJob job) {
        Trolley trolley = _trolleys.get(index);

        int originX = trolley.getPosition().getX();
        int originY = trolley.getPosition().getY();
        boolean rightwards = originX < target.getX();
        boolean upwards = originY < target.getY();

        checkPosition(trolley, job);
        while (!trolley.getPosition().equals(target)) {
            int x = 0;
            int y = 0;
            if (trolley.getPosition().getX() != target.getX()) {
                x = rightwards ? 1 : -1;
            }
            if (trolley.getPosition().getY() != target.getY()) {
                y = upwards ? 1 : -1;
            }

            trolley.addVector(new Point(x, y));
            checkPosition(trolley, job);
        }
        return trolley.getInventoryAndClear();
    }

    private void checkPosition(Trolley trolley, TrainJob job) {
        trolley.pickupProducts(_network.getProducts(trolley.getPosition()), job);
    }

    @Override
    public String toString() {
        return "TrainManager{" +
                "_network=" + _network +
                ", _trolleys=" + _trolleys +
                ", _pointHubs=" + _pointHubs +
                '}';
    }

    static class TrainHub {
        private final MyCollection<Product> _inventory = new MyCollection<>();
        private final MyCollection<CompletedTrainJob> _jobHistory = new MyCollection<>();

        TrainHub() {}

        void addProduct(Product product) {
            _inventory.add(product);
        }

        void addProducts(MyCollection<Product> products) {
            _inventory.add(products);
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof TrainHub hub)) {
                return false;
            }
            return this._inventory.equals(hub._inventory) && this._jobHistory.equals(hub._jobHistory);
        }
    }
}
