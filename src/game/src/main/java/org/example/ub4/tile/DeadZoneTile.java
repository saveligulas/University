package org.example.ub4.tile;

import org.example.ub4.player.Player;

public abstract class DeadZoneTile extends Tile {
    public DeadZoneTile(int id) {
        super(id);
    }

    public DeadZoneTile(int id, Tile[] neighbours) {
        super(id, neighbours);
    }

    public DeadZoneTile(int id, Tile north, Tile east, Tile south, Tile west) {
        super(id, north, east, south, west);
    }

    @Override
    public boolean contains(Player player) {
        return false;
    }
}
