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
    public List<OnTileInteraction> getOnTileInitialInteractions() {
        return null;
    }

    @Override
    public OnTileInteractionResult interactOnTile(Player player, OnTileInteraction interaction) {
        return null;
    }

    @Override
    protected void setDescription() {

    }
}
