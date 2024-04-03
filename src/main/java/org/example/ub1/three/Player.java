package org.example.ub1.three;


import org.example.ub1.three.app.Appearance;

public class Player {
    public final String USERNAME;
    private HealthPool _healthPool;
    private Appearance _appearance;
    private ItemContainer _container;
    private Item _equippedItem;
    private LocationPosition _locationPos;
    private Experience _experience;

    public Player(String username) {
        USERNAME = username;
    }

    public Player(String username, HealthPool healthPool, Appearance appearance, ItemContainer container, Item item, LocationPosition location, Experience experience) {
        USERNAME = username;
        _healthPool = healthPool;
        _appearance = appearance;
        _container = container;
        _equippedItem = item;
        _locationPos = location;
        _experience = experience;
    }
}
