package org.example.ub4.tile.passthrough;

import org.example.ub4.tile.PassThroughTile;
import org.example.ub4.tile.Tile;

public abstract class HorizontalTile extends PassThroughTile {
    public HorizontalTile(int id, Tile north, Tile east, Tile south, Tile west) {
        super(id, north, east, south, west);
    }

    public HorizontalTile(int id, Tile[] neighbours) {
        super(id, neighbours);
    }

    public HorizontalTile(int id) {
        super(id);
    }
}
