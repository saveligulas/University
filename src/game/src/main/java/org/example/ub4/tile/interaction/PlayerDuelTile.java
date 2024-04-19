package org.example.ub4.tile.interaction;

import org.example.coll.tuple.TupleBidirectional;
import org.example.ub4.player.Player;
import org.example.ub4.tile.InteractionTile;
import org.example.ub4.tile.Tile;

public abstract class PlayerDuelTile extends InteractionTile {
    protected TupleBidirectional<Player> _playerTuple;

    public PlayerDuelTile(int id) {
        super(id);
    }

    public PlayerDuelTile(int id, Tile[] neighbours) {
        super(id, neighbours);
    }

    public PlayerDuelTile(int id, Tile north, Tile east, Tile south, Tile west) {
        super(id, north, east, south, west);
    }

    protected boolean isFull() {
        return _playerTuple.getFirst() != null && _playerTuple.getSecond() != null;
    }
}
