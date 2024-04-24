package org.example.ub4.tile.deadzone;

import org.example.ub4.interactions.NeighbourTileInteraction;
import org.example.ub4.interactions.NeighbourTileInteractionResult;
import org.example.ub4.player.Player;
import org.example.ub4.tile.DeadZoneTile;
import org.example.ub4.tile.Tile;

import java.util.List;

public class Wall extends DeadZoneTile {
    public Wall() {super();};

    public Wall(int id) {
        super(id);
    }

    public Wall(int id, Tile[] neighbours) {
        super(id, neighbours);
    }

    public Wall(int id, Tile north, Tile east, Tile south, Tile west) {
        super(id, north, east, south, west);
    }

    @Override
    protected void setDescription() {
        _description = "This is a wall.";
    }

    @Override
    protected void setSpecification() {
        _specification = "Wall";
    }

    @Override
    protected List<NeighbourTileInteraction> addMorePossibleInteractions() {
        return List.of();
    }
}
