package org.example.ub2.container;

import org.example.ub1.my.MyCollection;

import java.util.Iterator;

public class ItemContainer extends MyCollectionArray<Item> {
    private final int MAX_WEIGHT;

    public ItemContainer(int size, int weight) {
        super(size);
        MAX_WEIGHT = weight;
    }

    public int getWeight() {
        int weight = 0;
        for (Iterator<Item> it = super.iterator(); it.hasNext();) {
            Item item = it.next();
            weight += item.WEIGHT;
        }
        return weight;
    }

    public boolean isFull() {
        return super.SIZE == super.size() || getWeight() == MAX_WEIGHT;
    }

    public boolean isEmpty() {
        return super.size() == 0;
    }

    public Item get(ItemType type) {
        return null; //TODO
    }

    @Override
    public void add(Item item) {
        if (!super.contains(item) && getWeight() + item.WEIGHT <= MAX_WEIGHT) {
            super.add(item);
        }
    }

    @Override
    public void add(MyCollection<Item> myCollection) {
        int weight = 0;
        for (Item item : myCollection) {
            weight += item.WEIGHT;
        }
        if (weight + getWeight() <= MAX_WEIGHT) {
            super.add(myCollection);
        }
    }
}

