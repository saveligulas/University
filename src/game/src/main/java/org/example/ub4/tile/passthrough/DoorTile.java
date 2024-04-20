package org.example.ub4.tile.passthrough;

import org.example.ub4.player.Player;
import org.example.ub4.tile.Direction;
import org.example.ub4.tile.InteractionTile;
import org.example.ub4.tile.Tile;


public class DoorTile extends HorizontalTile {
    public DoorTile(int id) {
        super(id);
    }

    public DoorTile(int id, Direction sourceDirection, InteractionTile source, Direction destinationDirection, InteractionTile destination) {
        super(id, sourceDirection, source, destinationDirection, destination);
    }

    @Override
    protected void setDescription() {
        _description = "This Tile has a door between it.";
    }

    @Override
    public boolean passThrough(Player player) {
        Direction sourceDirection = this.getDirectionOfNeighbour(player.getTile()).orElseThrow(() -> new RuntimeException("Player accessing door is not on a neighbouring Tile"));
        Tile destination = this.getTileInDirection(Direction.getOppositeDirection(sourceDirection)).orElseThrow(() -> new RuntimeException("Door Tile does not have opposing Tile"));
        if (! (destination instanceof InteractionTile interactionTile)) {
            throw new RuntimeException("Destination Tile is not an InteractionTile");
        }
        return player.setTile(interactionTile);
    }
}
