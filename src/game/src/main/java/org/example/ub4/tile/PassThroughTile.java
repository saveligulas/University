package org.example.ub4.tile;

public abstract class PassThroughTile extends Tile {
    public PassThroughTile(int id) {
        super(id);
    }

    public PassThroughTile(int id, Tile[] neighbours) {
        super(id, neighbours);
    }

    public PassThroughTile(int id, Tile north, Tile east, Tile south, Tile west) {
        super(id, north, east, south, west);
    }
}
