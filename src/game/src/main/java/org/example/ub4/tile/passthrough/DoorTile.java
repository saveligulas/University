package org.example.ub4.tile.passthrough;

import org.example.ub4.interactions.Interaction;
import org.example.ub4.interactions.InteractionResult;
import org.example.ub4.player.Player;
import org.example.ub4.tile.Direction;
import org.example.ub4.tile.Tile;

import java.util.List;

public class DoorTile extends HorizontalTile {
    public DoorTile(int id) {
        super(id);
    }



    public DoorTile(int id, Direction sourceDirection, Tile source, Direction destinationDirection, Tile destination) {
        super(id, sourceDirection, source, destinationDirection, destination);
    }

    @Override
    protected void setDescription() {
        _description = "This Tile has a door between it.";
    }

    @Override
    public void passThrough(Player player) {

    }
}
