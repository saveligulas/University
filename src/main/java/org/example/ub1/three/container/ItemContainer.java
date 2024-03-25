package org.example.ub1.three.container;

import org.example.ub1.three.Item;

public interface ItemContainer {
    public Item getItem(int index);
    public Item getFirstItem(int index);
    public Item getLastItem(int index);
    public Integer getIndexOfItem(Item item);
}
