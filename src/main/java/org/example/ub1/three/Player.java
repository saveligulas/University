package org.example.ub1.three;


import org.example.ub1.rect.Point;
import org.example.ub1.three.app.Appearance;
import org.example.ub1.three.app.AppearanceUpdateChange;
import org.example.ub2.container.Backpack;
import org.example.ub2.container.Item;
import org.example.ub2.container.ItemContainer;
import org.example.ub2.container.ItemType;
import org.example.ub1.three.dmg.Damage;
import org.example.ub1.three.dmg.DamageType;
import org.example.ub1.three.dmg.HealthPool;

import java.util.Random;

public class Player {
    public final String USERNAME;
    private HealthPool _healthPool;
    private Appearance _appearance;
    private Backpack _backpack;
    private Item _equippedItem;
    private LocationPosition _locationPos;
    private Experience _experience;

    public Player(String username) {
        this(username, new HealthPool(100), new Appearance(), new Backpack(12, 12) , null, new LocationPosition(Location.HOME_ISLAND, new Point(0,0)), new Experience());
    }

    public Player(String username, HealthPool healthPool, Appearance appearance, Backpack backpack, Item item, LocationPosition location, Experience experience) {
        USERNAME = username;
        _healthPool = healthPool;
        _appearance = appearance;
        _backpack = backpack;
        _equippedItem = item;
        _locationPos = location;
        _experience = experience;
    }

    public void attackThisPlayer(Damage damage) {
        _healthPool.takeDamage(damage);
    }

    public void updateAppearance(AppearanceUpdateChange appearanceUpdate) {
        _appearance.update(appearanceUpdate);
    }

    public ItemContainer getBackpackItems() {
        return _backpack;
    }

    public void equip(Item item) {
        if (!_backpack.contains(item)) {
            throw new IllegalArgumentException("Item not in backpack");
        }
        _equippedItem = item;
    }

    public Damage getDamage() {
        if (_equippedItem == null || _equippedItem.TYPE != ItemType.WEAPON) {
            return new Damage(DamageType.PHYSICAL, 1);
        }
        Random random = new Random();
        return new Damage(DamageType.values()[random.nextInt(DamageType.values().length)], _equippedItem.WEIGHT);
    }

    public void updatePosition(Point position) {

    }

    public void updateLocation(Location location) {

    }

    public void addExperience(int amount) {

    }

}
