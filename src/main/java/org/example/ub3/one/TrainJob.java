package org.example.ub3.one;

import org.example.coll.MyCollection;
import org.example.ub3.one.pro.ProductType;

public class TrainJob {
    public final MyCollection<ProductType> types;
    public final int target;
    private int _found;

    public TrainJob(int target) {
        this(target, new MyCollection<>(ProductType.values()));
    }

    public TrainJob(int target, MyCollection<ProductType> types) {
        this.target = target;
        this.types = types;
    }

    public void found() {
        _found++;
    }

    public int getFound() {
        return _found;
    }
}
