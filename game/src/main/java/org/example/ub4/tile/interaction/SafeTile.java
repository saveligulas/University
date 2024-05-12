package org.example.ub4.tile.interaction;

import org.example.ub4.custom.LimitedSizeArrayList;
import org.example.ub4.interactions.OnTileInteraction;
import org.example.ub4.interactions.OnTileInteractionResult;
import org.example.ub4.player.Player;
import org.example.ub4.tile.Tile;

import java.util.List;

public class SafeTile extends MultiPlayerTile {
    @Override
    protected void initialize() {
        _players = new LimitedSizeArrayList<>(100);
        _maxSize = 100;
    }

    public SafeTile(int id) {
        super(id);
    }

    public SafeTile(int id, Tile[] neighbours) {
        super(id, neighbours);
    }

    public SafeTile(int id, Tile north, Tile east, Tile south, Tile west) {
        super(id, north, east, south, west);
    }

    @Override
    public List<OnTileInteraction> addAdditionalOnTileInteractions() {
        return List.of(OnTileInteraction.SCOUT);
    }

    @Override
    protected void setDescription() {
        _description = "This Tile is safe for all travellers, that may pass through.";
    }

    @Override
    protected void setSpecification() {
        _specification = "Safe Zone";
    }
}
