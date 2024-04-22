package org.example.ub4.tile;

import org.example.ub4.excep.InteractionTileIsFullException;
import org.example.ub4.excep.TileCanNotBeAccessedException;
import org.example.ub4.interactions.*;
import org.example.ub4.player.Player;

import java.util.List;

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

    /**
     * Call this method implicitly by using Player.setTile as that also includes this method. If this method is called, make sure that the player also has the tile set.
     * @param player
     * @throws InteractionTileIsFullException
     */
    public abstract void addPlayerToTile(Player player) throws InteractionTileIsFullException;

    public abstract void removePlayerFromTile(Player player);

    public OnTileInteractionResult interactOnTile(Player player) {
        return interactOnTile(player, null);
    }

    public abstract OnTileInteractionResult interactOnTile(Player player, OnTileInteraction interaction);

    public abstract List<NeighbourTileInteraction> getInitialPossibleInteractions();

    public NeighbourTileInteractionResult interactWithNeighbouringTile(Tile tile, Player player) throws TileCanNotBeAccessedException {
        return interactWithNeighbouringTile(tile, player, null);
    }

    public NeighbourTileInteractionResult interactWithNeighbouringTile(Tile tile, Player player, NeighbourTileInteraction interaction) throws TileCanNotBeAccessedException {
        if (!isNeighbour(tile)) {
            throw new TileCanNotBeAccessedException("Tile " + tile + " is not a neighbour of " + this);
        }
        return TileInteractionResultFactory.createResult(this, tile, player, interaction);
    }
}
