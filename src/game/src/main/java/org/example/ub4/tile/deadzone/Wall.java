package org.example.ub4.tile.deadzone;

import org.example.ub4.interactions.Interaction;
import org.example.ub4.interactions.InteractionResult;
import org.example.ub4.player.Player;
import org.example.ub4.tile.DeadZoneTile;
import org.example.ub4.tile.Tile;

public class Wall extends DeadZoneTile {
    public Wall(int id) {
        super(id);
    }

    public Wall(int id, Tile[] neighbours) {
        super(id, neighbours);
    }

    public Wall(int id, Tile north, Tile east, Tile south, Tile west) {
        super(id, north, east, south, west);
    }
}
