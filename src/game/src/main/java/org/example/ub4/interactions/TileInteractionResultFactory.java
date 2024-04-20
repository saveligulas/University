package org.example.ub4.interactions;

import org.example.ub4.player.Player;
import org.example.ub4.tile.Tile;

public class TileInteractionResultFactory {
    public static InteractionResult<OnTileInteraction> createResult(Tile sourceTile, Tile targetTile, Player player, OnTileInteraction interaction) {
        return null;
    }

    public static InteractionResult<NeighbourTileInteraction> createResult(Tile sourceTile, Tile targetTile, Player player, NeighbourTileInteraction interaction) {
        return null;
    }
}
