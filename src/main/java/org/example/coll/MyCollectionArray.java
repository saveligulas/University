package org.example.coll;

import org.example.coll.MyCollection;

public class MyCollectionArray<T> extends MyCollection<T> {
    public final int SIZE;

    public MyCollectionArray(int size) {
        super(size);
        SIZE = size;
    }

    public boolean isFull() {
        return super.size() == SIZE;
    }

    @Override
    public void add(T e) {
        if (!isFull()) {
            super.add(e);
        }
    }

    @Override
    public void add(MyCollection<T> collection) {
        if (!isFull() && collection.size() + super.size() <= SIZE) {
            super.add(collection);
        }
    }
}
