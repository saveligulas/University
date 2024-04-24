package org.example.ub4.player;

import java.util.concurrent.atomic.AtomicInteger;

public class Loot {
    private static final AtomicInteger ID_COUNTER = new AtomicInteger();
    private final int _id;
    private String _description;

    public Loot(String description) {
        _id = ID_COUNTER.getAndIncrement();
        _description = description;
    }

    public int getId() {
        return _id;
    }

    public String getDescription() {
        return _description;
    }

    protected void setDescription(String description) {
        _description = description;
    }

    public String toString() {
        return _description;
    }
}

