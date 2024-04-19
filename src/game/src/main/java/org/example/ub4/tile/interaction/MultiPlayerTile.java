package org.example.ub4.tile.interaction;

import org.example.ub4.custom.LimitedSizeArrayList;
import org.example.ub4.excep.InteractionTileIsFullException;
import org.example.ub4.player.Player;
import org.example.ub4.tile.InteractionTile;
import org.example.ub4.tile.Tile;

public abstract class MultiPlayerTile extends InteractionTile {
    protected LimitedSizeArrayList<Player> _players;
    protected int _maxSize;

    private void initialize(int size) {
        _players = new LimitedSizeArrayList<>(size);
        _maxSize = size;
    }

    public MultiPlayerTile(int id) {
        super(id);
        initialize(2);
    }

    public MultiPlayerTile(int id, Tile[] neighbours) {
        super(id, neighbours);
        initialize(2);
    }

    public MultiPlayerTile(int id, Tile north, Tile east, Tile south, Tile west) {
        super(id, north, east, south, west);
        initialize(2);
    }

    protected boolean isFull() {
        return _maxSize == _players.size();
    }

    public void setMaxSize(int maxSize) {
        _players = new LimitedSizeArrayList<>(_players, maxSize);
        _maxSize = maxSize;
    }

    @Override
    public void addPlayerToTile(Player player) throws InteractionTileIsFullException {
        if (!_players.add(player)) {
            throw new InteractionTileIsFullException("The tile is already full.");
        }
    }
}
