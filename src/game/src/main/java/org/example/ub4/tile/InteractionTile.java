package org.example.ub4.tile;

import org.example.ub4.excep.InteractionTileIsFullException;
import org.example.ub4.interactions.Interaction;
import org.example.ub4.interactions.InteractionResult;
import org.example.ub4.player.Player;

public abstract class InteractionTile extends Tile {

    public InteractionTile(int id) {
        super(id);
    }

    public InteractionTile(int id, Tile[] neighbours) {
        super(id, neighbours);
    }

    public InteractionTile(int id, Tile north, Tile east, Tile south, Tile west) {
        super(id, north, east, south, west);
    }

    public abstract void addPlayerToTile(Player player) throws InteractionTileIsFullException;

    public abstract InteractionResult interactOnTile(Player player, Interaction interaction);
}
