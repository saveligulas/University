package org.example.ub4.tile.deadzone;

import org.example.ub4.interactions.NeighbourTileInteraction;
import org.example.ub4.interactions.NeighbourTileInteractionResult;
import org.example.ub4.player.Player;
import org.example.ub4.tile.DeadZoneTile;
import org.example.ub4.tile.Direction;
import org.example.ub4.tile.Tile;

import java.util.List;

public class Void extends DeadZoneTile {
    public Void() {
        super();
    }

    public Void(int id, Tile[] neighbours) {
        super(id, neighbours);
    }

    @Override
    protected void setDescription() {
        _description = "The Void is neither here nor there. It swallows anything it comes into contact with.";
    }

    @Override
    protected void setSpecification() {
        _specification = "Void";
    }

    @Override
    protected List<NeighbourTileInteraction> addMorePossibleInteractions() {
        return List.of();
    }
}
