package org.example.ub4.tile;

import org.example.ub4.interactions.InteractionResult;
import org.example.ub4.interactions.NeighbourTileInteraction;
import org.example.ub4.interactions.NeighbourTileInteractionResult;
import org.example.ub4.player.Player;
import org.example.ub4.tile.deadzone.Wall;

import java.util.ArrayList;
import java.util.List;

public abstract class PassThroughTile extends Tile {
    public PassThroughTile(int id) {
        super(id);
    }

    public <T extends InteractionTile> PassThroughTile(int id, Direction sourceDirection, T source, Direction destinationDirection, T destination) {
        super(id);
        if (Direction.getOppositeDirection(sourceDirection) == destinationDirection) {
            throw new IllegalArgumentException("Directions of source and destination Tile must be opposite");
        }
        setTile(destinationDirection, destination);
        setTile(sourceDirection, source);
    }

    @Override
    public List<NeighbourTileInteraction> addMorePossibleInteractions() {
        return List.of(NeighbourTileInteraction.PASS_THROUGH);
    }

    @Override
    public boolean contains(Player player) {
        return false;
    }

    @Override
    public NeighbourTileInteractionResult interactFromNeighbourAdditionalOptions(Player player, NeighbourTileInteraction interaction) {
        if (interaction == NeighbourTileInteraction.PASS_THROUGH) {
            if (passThrough(player)) {
                return new NeighbourTileInteractionResult(true, "You successfully passed through: " + _specification + " -\n" + _description);
            } else {
                return new NeighbourTileInteractionResult(false, "You can not pass through: " + _specification);
            }
        }

        return NeighbourTileInteractionResult.emptyFalse();
    }

    public abstract boolean passThrough(Player player);
}
