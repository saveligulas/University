package org.example.ub4.tile;

import org.example.coll.tuple.TupleBidirectional;
import org.example.ub4.player.Player;

public abstract class PlayerDuelTile extends InteractionTile {
    protected TupleBidirectional<Player> _playerTuple;

    protected boolean isFull() {
        return _playerTuple.getFirst() != null && _playerTuple.getSecond() != null;
    }
}
