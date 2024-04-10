package org.example.ub2.container;

import java.util.concurrent.atomic.AtomicInteger;

public class Item {
    private static final AtomicInteger ID_COUNT = new AtomicInteger(100000);
    public final int ID;
    public final ItemType TYPE;
    public final int WEIGHT;

    public Item(Item item) {
        this(item.TYPE, item.WEIGHT);
    }

    public Item(ItemType type, int weight) {
        ID = ID_COUNT.getAndIncrement();
        TYPE = type;
        WEIGHT = weight;
    }



    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Item item)) {
            return false;
        }
        return (this.ID == item.ID);
    }
}
