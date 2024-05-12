package org.example.ub4.game;

import org.example.ub4.player.Player;
import org.example.ub4.tile.InteractionTile;
import org.example.ub4.tile.Tile;

import java.util.List;

public class GameState {
    private int _currentRound;
    private final int _maxRound;
    private final List<Player> _players;
    private int _playerTurnPointer;
    private final InteractionTile _sourceTile;

    public GameState(int roundsToPlay, InteractionTile sourceTile, Player... players) {
        this(roundsToPlay, sourceTile, List.of(players));
    }

    public GameState(int roundsToPlay, InteractionTile sourceTile, List<Player> players) {
        _currentRound = 1;
        _maxRound = roundsToPlay;
        _players = players;
        _sourceTile = sourceTile;
    }

    public void advancePlayerTurn() {
        _playerTurnPointer++;
    }

    public void advanceRound() {
        _currentRound++;
        _playerTurnPointer = 0;
    }

    public boolean isOver() {
        return _currentRound == _maxRound;
    }

    public boolean hasPlayer(Player player) {
        return _players.contains(player);
    }

    public boolean removePlayer(Player player) {
        return _players.remove(player);
    }

    public Player getCurrentPlayer() {
        return _players.get(_playerTurnPointer);
    }

    public InteractionTile getTileOfCurrentPlayer() {
        return _players.get(_playerTurnPointer).getTile();
    }

    public List<Player> getPlayers() {
        return _players;
    }

}
