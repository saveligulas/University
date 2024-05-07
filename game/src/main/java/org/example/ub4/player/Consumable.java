package org.example.ub4.player;

public abstract class Consumable extends Loot {
    public Consumable(String description) {
        super(description);
    }

    public abstract void consume(Player player);
}
