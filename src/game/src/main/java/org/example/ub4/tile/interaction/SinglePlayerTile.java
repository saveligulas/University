package org.example.ub4.tile.interaction;

import org.example.ub4.excep.InteractionTileIsFullException;
import org.example.ub4.player.Player;
import org.example.ub4.tile.InteractionTile;
import org.example.ub4.tile.Tile;

public abstract class SinglePlayerTile extends InteractionTile {
    private Player _player;

    public SinglePlayerTile(int id) {
        super(id);
    }

    public SinglePlayerTile(int id, Tile[] neighbours) {
        super(id, neighbours);
    }

    public SinglePlayerTile(int id, Tile north, Tile east, Tile south, Tile west) {
        super(id, north, east, south, west);
    }

    @Override
    public boolean contains(Player player) {
        return _player.equals(player);
    }

    @Override
    public void addPlayerToTile(Player player) throws InteractionTileIsFullException {
        _player = player;
    }
}
