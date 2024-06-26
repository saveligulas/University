package org.example.ub4.tile.passthrough;

import org.example.ub4.tile.Direction;
import org.example.ub4.tile.InteractionTile;
import org.example.ub4.tile.PassThroughTile;
import org.example.ub4.tile.Tile;

public abstract class HorizontalTile extends PassThroughTile {
    public HorizontalTile(int id) {
        super(id);
    }

    public HorizontalTile(int id, Direction sourceDirection, InteractionTile source, Direction destinationDirection, InteractionTile destination) {
        super(id, sourceDirection, source, destinationDirection, destination);
    }
}
