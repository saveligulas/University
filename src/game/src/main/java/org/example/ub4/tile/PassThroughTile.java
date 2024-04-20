package org.example.ub4.tile;

import org.example.ub4.player.Player;

public abstract class PassThroughTile extends Tile {
    public PassThroughTile(int id) {
        super(id);
    }

    public PassThroughTile(int id, Direction sourceDirection, Tile source, Direction destinationDirection, Tile destination) {
        super(id);
        if (Math.abs(sourceDirection.ordinal() - destinationDirection.ordinal()) % 2 != 0) {
            throw new IllegalArgumentException("Directions of source and destination Tile must be opposite");
        }
        setTile(destinationDirection, destination);
        setTile(sourceDirection, source);
    }

    @Override
    public boolean contains(Player player) {
        return false;
    }

    public abstract void passThrough(Player player);
}
