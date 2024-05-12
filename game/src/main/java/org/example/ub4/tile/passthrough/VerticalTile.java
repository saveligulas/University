package org.example.ub4.tile.passthrough;

import org.example.ub4.tile.Direction;
import org.example.ub4.tile.PassThroughTile;
import org.example.ub4.tile.Tile;

public abstract class VerticalTile extends PassThroughTile {
    private Tile _up;
    private Tile _down;

    public VerticalTile(int id) {
        super(id);
    }

    public VerticalTile(int id, Tile topTile, Tile bottomTile) {
        super(id);
        setUp(topTile);
        setDown(bottomTile);
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
