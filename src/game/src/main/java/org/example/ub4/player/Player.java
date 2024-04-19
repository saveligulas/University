package org.example.ub4.player;

import org.example.coll.MyCollection;
import org.example.ub4.excep.InteractionTileIsFullException;
import org.example.ub4.tile.InteractionTile;
import org.example.ub4.tile.Tile;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String _username;
    private final List<Consumable> _consumables;
    private final List<String> _answers;
    private InteractionTile _interactionTile;

    public Player(String username) {
        _username = username;
        _consumables = new ArrayList<>();
        _answers = new ArrayList<>();
    }

    public boolean setTile(InteractionTile tile) {
        try {
            _interactionTile.addPlayerToTile(this);
        } catch (InteractionTileIsFullException e) {
            return false;
        }
        _interactionTile = tile;
        return true;
    }

    public InteractionTile getTile() {
        return _interactionTile;
    }
}
