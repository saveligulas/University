package org.example.ub4.tile.interaction;

import org.example.ub4.tile.InteractionTile;
import org.example.ub4.tile.Tile;

public abstract class SinglePlayerTile extends InteractionTile {
    public SinglePlayerTile(int id) {
        super(id);
    }

    public SinglePlayerTile(int id, Tile[] neighbours) {
        super(id, neighbours);
    }

    public SinglePlayerTile(int id, Tile north, Tile east, Tile south, Tile west) {
        super(id, north, east, south, west);
    }
}
