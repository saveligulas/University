package org.example.ub4.game;

import org.example.ub4.interactions.InteractionResult;
import org.example.ub4.player.Player;

public class Game {
    private GameState _state;

    private void checkIfPlayerIsInGame(Player player) {
        if (!_state.hasPlayer(player)) {
            throw new RuntimeException("Player is not in game");
        }
    }

    // gives us the info of what round we are on and which players are still in the game
    public RoundInfo getRoundInfo() {
        return RoundInfo.buildRoundInfo(_state);
    }

    // gives us the options the player has for this turn
    public PlayerOptions getPlayerOptions() {
        Player player = _state.getCurrentPlayer();


    }

    // gives us the result of the interaction and if it worked
    public <T extends Enum<T>> InteractionResult<T> performAction(T interaction) {

    }

}
