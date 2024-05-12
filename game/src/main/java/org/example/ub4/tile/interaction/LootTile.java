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

    @Override
    public List<OnTileInteraction> addAdditionalOnTileInteractions() {
        return List.of(OnTileInteraction.LOOT);
    }

    @Override
    protected OnTileInteractionResult interactOnTileAdditionalOptions(Player player, OnTileInteraction interaction) {
        if (interaction == OnTileInteraction.LOOT) {
            String message = "You looted: " + _loot;
            lootAndClear();
            return new OnTileInteractionResult(true, message);
        }

        return OnTileInteractionResult.emptyFalse();
    }

    public void lootAndClear() {
        _player.addLoot(_loot);
        _loot.clear();
    }

    public void addLoot(Loot... loot) {
        _loot.addAll(List.of(loot));
    }

    @Override
    protected void setDescription() {
        _description = "Lucky you are, as this is all yours. Great Riches await you.";
    }

    @Override
    protected void setSpecification() {
        _specification = "Loot";
    }


}
