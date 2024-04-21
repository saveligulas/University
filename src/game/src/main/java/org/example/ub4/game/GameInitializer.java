package org.example.ub4.game;

import org.example.ub4.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GameInitializer {
    public static Game initialize(int roundsToPlay, String... usernames) {
        return initialize(roundsToPlay, Set.copyOf(List.of(usernames)));
    }

    public static Game initialize(int roundsToPlay, Set<String> usernames) {
        List<Player> players = new ArrayList<>();
        for (String username : usernames) {
            players.add(new Player(username));
        }
        GameState state = new GameState(roundsToPlay, players);
        return new Game(state);
    }
}
