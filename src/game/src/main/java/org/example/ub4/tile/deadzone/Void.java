package org.example.ub4.tile.deadzone;

import org.example.ub4.interactions.Interaction;
import org.example.ub4.interactions.InteractionResult;
import org.example.ub4.player.Player;
import org.example.ub4.tile.DeadZoneTile;
import org.example.ub4.tile.Direction;
import org.example.ub4.tile.Tile;

public class Void extends DeadZoneTile {
    public Void(int id) {
        super(id);
    }

    public Void(int id, Direction direction, Tile edgeTile) {
        super(id);
        setTile(direction, edgeTile);
    }

    public Void(int id, Tile[] neighbours) {
        super(id, neighbours);
    }

    public Void(int id, Tile north, Tile east, Tile south, Tile west) {
        super(id, north, east, south, west);
    }

    @Override
    public InteractionResult interactFromNeighbouringTile(Player player, Interaction interaction) {
        return null;
    }
}
