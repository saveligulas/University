package org.example.ub4.game;

import org.example.ub4.player.Player;
import org.example.ub4.tile.InteractionTile;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GameInitializer {
    public static Game initialize(int roundsToPlay, InteractionTile sourceTile,  String... usernames) {
        return initialize(roundsToPlay, sourceTile, Set.copyOf(List.of(usernames)));
    }

    public static Game initialize(int roundsToPlay, InteractionTile sourceTile, Set<String> usernames) {
        List<Player> players = new ArrayList<>();
        for (String username : usernames) {
            players.add(new Player(username));
        }
        for (Player player : players) {
            player.setTile(sourceTile);
        }
        GameState state = new GameState(roundsToPlay, sourceTile, players);
        return new Game(state);
    }
}
