package org.example.ub4.interactions;

import org.example.ub4.excep.TileCanNotBeAccessedException;
import org.example.ub4.player.Player;
import org.example.ub4.tile.InteractionTile;
import org.example.ub4.tile.Tile;

import java.util.ArrayList;
import java.util.List;

public class TileInteractionResultManager {
    // TODO: implement these methods with null for interaction as well
    public static OnTileInteractionResult createResult(Player player, OnTileInteraction interaction) {
        if (interaction == OnTileInteraction.PASS) {
            OnTileInteractionResult.Builder builder = new OnTileInteractionResult.Builder();
            builder.success(true);
            builder.message("You have passed your interaction turn.");
            return builder.build();
        }

        if (interaction == null) {
            return new OnTileInteractionResult(true, "These are the actions you can perform on this tile:", player.getTile().getOnTileInitialInteractions());
        }

        return player.getTile().interactOnTile(player, interaction);
    }


    public static NeighbourTileInteractionResult createResult(Tile targetTile, Player player, NeighbourTileInteraction interaction) throws TileCanNotBeAccessedException {
        if (interaction == NeighbourTileInteraction.PASS) {
            NeighbourTileInteractionResult.Builder builder = new NeighbourTileInteractionResult.Builder();
            builder.success(true);
            builder.message("You have passed your interaction turn.");
            return builder.build();
        }

        if (interaction == null) {
            return createOptionsNeighbour(targetTile);
        }

        return player.getTile().interactWithNeighbouringTile(targetTile, player, interaction);
    }

    private static NeighbourTileInteractionResult createOptionsNeighbour(Tile targetTile) {
        List<NeighbourTileInteraction> result = targetTile.getPossibleInteractions();
        result.add(NeighbourTileInteraction.PASS);
        return new NeighbourTileInteractionResult(true, "These are the options you have for this tile", result);
    }
}
