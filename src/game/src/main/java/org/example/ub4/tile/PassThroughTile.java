package org.example.ub4.tile;

import org.example.ub4.interactions.NeighbourTileInteraction;
import org.example.ub4.player.Player;

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
    public List<NeighbourTileInteraction> getPossibleInteractions() {
        List<NeighbourTileInteraction> possibleInteractions = super.getPossibleInteractions();
        possibleInteractions.add(NeighbourTileInteraction.PASS_THROUGH);
        return possibleInteractions;
    }

    @Override
    public boolean contains(Player player) {
        return false;
    }

    public abstract boolean passThrough(Player player);
}
