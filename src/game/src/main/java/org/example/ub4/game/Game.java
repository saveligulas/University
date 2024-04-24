package org.example.ub4.game;

import org.example.ub4.interactions.*;
import org.example.ub4.player.Player;
import org.example.ub4.tile.Direction;
import org.example.ub4.tile.Tile;

import java.util.List;

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

    protected Player getCurrentPlayer() {
        return _state.getCurrentPlayer();
    }

    protected void advancePlayerTurn() {
        _state.advancePlayerTurn();
    }

    protected Tile getNeighbourintTile(int indexFromOne) {
        return _state.getTileOfCurrentPlayer().getTileInDirection(Direction.getDirectionZeroIndexed(indexFromOne)).orElseThrow(() -> new IllegalArgumentException("Index out of bounds should not be passed to this method"));
    }

    // gives us the options the player has for this turn
    protected List<OnTileInteraction> getOnTileOptions() {
        return _state.getTileOfCurrentPlayer().getOnTileInteractions();

    }

//    protected NeighbourTileInteractionResult getNeighbouringTileOptions(Tile neighbourTile) {
//        try {
//            return _state.getTileOfCurrentPlayer().interactWithNeighbouringTile(neighbourTile, _state.getCurrentPlayer());
//        } catch (TileCanNotBeAccessedException e) {
//            System.out.println("Critical error occurred: Game interface allowed Player to select tile that is not its neighbour.");
//            System.exit(500);
//            return null;
//        }
//    }

    //TODO: Deprecate Legacy Code

    // gives us the result of the interaction and if it worked
    protected OnTileInteractionResult performAction(OnTileInteraction onTileInteraction) {
        return _state.getTileOfCurrentPlayer().interactOnTile(_state.getCurrentPlayer(), onTileInteraction);
    }

//    protected NeighbourTileInteractionResult performAction(Tile neighbourTile, NeighbourTileInteraction neighbourTileInteraction) {
//        try {
//            return _state.getTileOfCurrentPlayer().interactWithNeighbouringTile(neighbourTile, _state.getCurrentPlayer(), neighbourTileInteraction);
//        } catch (TileCanNotBeAccessedException e) {
//            StringBuilder sb = new StringBuilder();
//            sb.append("Unexpected error occurred while trying to interact with a Tile that is not a neighbour.\n");
//            sb.append("Cause: ").append(e.getCause().getMessage()).append("\n");
//            sb.append("Message: ").append(e.getMessage());
//
//            NeighbourTileInteractionResult.Builder builder = new NeighbourTileInteractionResult.Builder();
//            return builder.success(false)
//                    .message(sb.toString())
//                    .build();
//        }
//    }


    protected void endRound() {
        _state.advanceRound();
    }
}
