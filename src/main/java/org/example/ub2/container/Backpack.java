package org.example.ub2.container;

import org.example.ub2.container.Item;
import org.example.ub2.container.ItemContainer;

public class Backpack extends ItemContainer {
    public Backpack(int size, int weight) {
        super(size, weight);
    }

    public Item getTopItem() {
        return super.get(super.size() - 1);
    }

    public Item getBottomItem() {
        return super.get(0);
    }
}
