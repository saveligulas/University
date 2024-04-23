package org.example.ub4.player;

import org.example.ub4.excep.InteractionTileIsFullException;
import org.example.ub4.tile.InteractionTile;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String _username;
    private final List<Loot> _inventory;
    private final List<String> _answers;
    private InteractionTile _interactionTile;
    private final List<Integer> _discoveredTiles;

    public Player(String username) {
        _username = username;
        _inventory = new ArrayList<>();
        _answers = new ArrayList<>();
        _discoveredTiles = new ArrayList<>();
    }

    public boolean setTile(InteractionTile tile) {
        try {
            tile.addPlayerToTile(this);
        } catch (InteractionTileIsFullException e) {
            return false;
        }

        if (_interactionTile != null) {
            _interactionTile.removePlayerFromTile(this);
        }

        _interactionTile = tile;
        return true;
    }

    public void addLoot(List<Loot> loot) {
        _inventory.addAll(loot);
    }

    public InteractionTile getTile() {
        return _interactionTile;
    }

    public String getUsername() {
        return _username;
    }
}
