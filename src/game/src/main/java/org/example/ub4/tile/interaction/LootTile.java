package org.example.ub4.tile.interaction;

import org.example.ub4.interactions.InteractionResult;
import org.example.ub4.interactions.NeighbourTileInteraction;
import org.example.ub4.interactions.OnTileInteraction;
import org.example.ub4.interactions.OnTileInteractionResult;
import org.example.ub4.player.Loot;
import org.example.ub4.player.Player;
import org.example.ub4.tile.Tile;

import java.util.ArrayList;
import java.util.List;

public class LootTile extends SinglePlayerTile {
    private final List<Loot> _loot = new ArrayList<>();

    public LootTile(int id) {
        super(id);
    }

    public LootTile(int id, Tile[] neighbours) {
        super(id, neighbours);
    }

    public LootTile(int id, Tile north, Tile east, Tile south, Tile west) {
        super(id, north, east, south, west);
    }

    public void lootAndClear() {
        List<Loot> result = new ArrayList<>(_loot);
        _loot.clear();
        _player.addLoot(result);
    }

    @Override
    protected void setDescription() {

    }

    @Override
    public OnTileInteractionResult interactOnTile(Player player, OnTileInteraction interaction) {
        return null;
    }

    @Override
    public List<NeighbourTileInteraction> getInitialPossibleInteractions() {
        return null;
    }
}
