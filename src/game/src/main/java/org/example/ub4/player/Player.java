package org.example.ub4.player;

import org.example.ub4.excep.InteractionTileIsFullException;
import org.example.ub4.tile.Direction;
import org.example.ub4.tile.InteractionTile;
import org.example.ub4.tile.Tile;
import org.example.ub4.tile.deadzone.Void;

import java.util.*;

public class Player {
    private final String _username;
    private final List<Loot> _inventory;
    //TODO: implement this
    private final List<String> _answers;
    private InteractionTile _interactionTile;
    private final Set<Integer> _discoveredTiles;

    public Player(String username) {
        _username = username;
        _inventory = new ArrayList<>();
        _answers = new ArrayList<>();
        _discoveredTiles = new HashSet<>();
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
        _discoveredTiles.add(tile.getId());
        return true;
    }

    public void addLoot(List<Loot> loot) {
        _inventory.addAll(loot);
    }

    public void addDiscoveredTile(Tile tile) {
        _discoveredTiles.add(tile.getId());
    }

    public InteractionTile getTile() {
        return _interactionTile;
    }

    public String getUsername() {
        return _username;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Player player)) {
            return false;
        }
        return player._username.equals(this._username);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_username).append("\n");
        sb.append("Inventory:\n");
        for (Loot loot : _inventory) {
            sb.append(loot).append("\n");
        }
        sb.append("Tiles:\n");
        sb.append("Current - ").append(_interactionTile.shortToString()).append("\n");
        for (Direction direction : Direction.values()) {
            sb.append(direction.toString().charAt(0)).append(": ");
            Optional<Tile> optionalTile = _interactionTile.getTileInDirection(direction);
            if (optionalTile.isPresent()) {
                Tile directionTile = optionalTile.get();
                if (directionTile instanceof Void v) {
                    sb.append(v.shortToString());
                } else if(_discoveredTiles.contains(directionTile.getId())) {
                    sb.append(directionTile.shortToString());
                } else {
                    sb.append("Unknown");
                }
            } else {
                sb.append("Unknown");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
