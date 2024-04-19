package org.example.ub4.tile.passthrough;

import org.example.ub4.tile.PassThroughTile;
import org.example.ub4.tile.Tile;

public abstract class VerticalTile extends PassThroughTile {
    private Tile _up;
    private Tile _down;

    public VerticalTile(int id) {
        super(id);
    }

    public VerticalTile(int id, Tile[] neighbours, Tile up, Tile down) {
        super(id, neighbours);
        this._up = up;
        this._down = down;
    }

    public VerticalTile(int id, Tile north, Tile east, Tile south, Tile west, Tile up, Tile down) {
        super(id, north, east, south, west);
        this._up = up;
        this._down = down;
    }

    protected void setUp(Tile tile) {
        _up = tile;
    }

    protected void setDown(Tile tile) {
        _down = tile;
    }

    Tile getUp() { return _up;}

    Tile getDown() { return _down;}
}
