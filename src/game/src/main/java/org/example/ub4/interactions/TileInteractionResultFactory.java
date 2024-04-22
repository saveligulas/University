package org.example.ub4.interactions;

import org.example.ub4.player.Player;
import org.example.ub4.tile.Tile;

public class TileInteractionResultFactory {
    // TODO: implement these methods with null for interaction as well
    public static OnTileInteractionResult createResult(Tile sourceTile, Tile targetTile, Player player, OnTileInteraction interaction) {
        return null;
    }

    public static NeighbourTileInteractionResult createResult(Tile sourceTile, Tile targetTile, Player player, NeighbourTileInteraction interaction) {
        return null;
    }
}
