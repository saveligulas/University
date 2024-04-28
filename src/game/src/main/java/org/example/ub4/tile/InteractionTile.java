package org.example.ub4.tile;

import org.example.ub4.excep.InteractionTileIsFullException;
import org.example.ub4.interactions.*;
import org.example.ub4.player.Player;

import java.util.ArrayList;
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

    public List<OnTileInteraction> getOnTileInteractions() {
        List<OnTileInteraction> result = new ArrayList<>();
        result.add(OnTileInteraction.PASS);
        result.addAll(addAdditionalOnTileInteractions());
        return result;
    }

    public abstract List<OnTileInteraction> addAdditionalOnTileInteractions();

    public abstract void addPlayerToTile(Player player) throws InteractionTileIsFullException;

    public abstract boolean removePlayerFromTile(Player player);

    @Override
    public List<NeighbourTileInteraction> addMorePossibleInteractions() {
        return List.of(NeighbourTileInteraction.WALK_TO);
    }

    public OnTileInteractionResult interactOnTile(Player player, OnTileInteraction interaction) {
        if (interaction == OnTileInteraction.PASS) {
            return OnTileInteractionResult.emptyTrue();
        }

        return interactOnTileAdditionalOptions(player, interaction);
    }

    protected abstract OnTileInteractionResult interactOnTileAdditionalOptions(Player player, OnTileInteraction interaction);

    @Override
    public NeighbourTileInteractionResult interactFromNeighbourAdditionalOptions(Player player, NeighbourTileInteraction interaction) {
        if (interaction == NeighbourTileInteraction.WALK_TO) {
            if (!player.setTile(this)) {
                return new NeighbourTileInteractionResult(false, "You cannot walk to this Tile for mysterious reasons.");
            }
            return new NeighbourTileInteractionResult(true, "You walked to the Tile successfully.");
        }

        return NeighbourTileInteractionResult.emptyFalse();
    }
}
