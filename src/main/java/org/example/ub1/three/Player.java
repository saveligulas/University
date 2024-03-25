package org.example.ub1.three;

import org.example.ub1.three.container.BackPack;
import org.example.ub1.three.container.ItemContainer;

public class Player {
    private String _name;
    private double _health;
    private Outfit _outfit;
    private ItemContainer _container;
    private Item _equippedItem;

    public Player() {

    }

    public Player(String name) {

    }

    public Player(String name, Outfit outfit) {

    }

    public Player(String name, Outfit outfit, ItemContainer itemContainer) {

    }

    public Player(String name, double health, Outfit outfit, ItemContainer itemContainer) {
        _name = name;
        _health = health;
        _outfit = outfit;
        _container = itemContainer;
        _equippedItem = null;
    }


}
