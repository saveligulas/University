package org.example.ub4.game;

import org.example.ub4.excep.TileCanNotBeAccessedException;
import org.example.ub4.interactions.*;
import org.example.ub4.player.Player;
import org.example.ub4.tile.Tile;

public class Game {
    private GameState _state;

    Game(GameState state) {
        _state = state;
    }

    protected void checkIfPlayerIsInGame(Player player) {
        if (!_state.hasPlayer(player)) {
            throw new RuntimeException("Player is not in game");
        }
    }

    // gives us the info of what round we are on and which players are still in the game
    public RoundInfo getRoundInfo() {
        return RoundInfo.buildRoundInfo(_state);
    }

    // gives us the options the player has for this turn
    protected OnTileInteractionResult getOnTileOptions() {
        return _state.getTileOfCurrentPlayer().interactOnTile(_state.getCurrentPlayer());

    }

    protected NeighbourTileInteractionResult getNeighbouringTileOptions(Tile neighbourTile) throws TileCanNotBeAccessedException {
        return _state.getTileOfCurrentPlayer().interactWithNeighbouringTile(neighbourTile, _state.getCurrentPlayer());
    }

    // gives us the result of the interaction and if it worked
    protected OnTileInteractionResult performAction(OnTileInteraction onTileInteraction) {
        return _state.getTileOfCurrentPlayer().interactOnTile(_state.getCurrentPlayer(), onTileInteraction);
    }

    protected NeighbourTileInteractionResult performAction(Tile neighbourTile, NeighbourTileInteraction neighbourTileInteraction) {
        try {
            return _state.getTileOfCurrentPlayer().interactWithNeighbouringTile(neighbourTile, _state.getCurrentPlayer(), neighbourTileInteraction);
        } catch (TileCanNotBeAccessedException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Unexpected error occurred while trying to interact with a Tile that is not a neighbour.\n");
            sb.append("Cause: ").append(e.getCause().getMessage()).append("\n");
            sb.append("Message: ").append(e.getMessage());

            NeighbourTileInteractionResult.Builder builder = new NeighbourTileInteractionResult.Builder();
            return builder.success(false)
                    .message(sb.toString())
                    .build();
        }
    }

}
