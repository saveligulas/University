package org.example.ub4.tile.interaction;

import org.example.ub4.custom.LimitedSizeArrayList;
import org.example.ub4.excep.InteractionTileIsFullException;
import org.example.ub4.interactions.OnTileInteraction;
import org.example.ub4.interactions.OnTileInteractionResult;
import org.example.ub4.player.Player;
import org.example.ub4.tile.InteractionTile;
import org.example.ub4.tile.Tile;

public abstract class MultiPlayerTile extends InteractionTile {
    protected LimitedSizeArrayList<Player> _players;
    protected int _maxSize;

    // set size in this method
    protected abstract void initialize();

    public MultiPlayerTile(int id) {
        super(id);
        initialize();
    }

    public MultiPlayerTile(int id, Tile[] neighbours) {
        super(id, neighbours);
        initialize();
    }

    public MultiPlayerTile(int id, Tile north, Tile east, Tile south, Tile west) {
        super(id, north, east, south, west);
        initialize();
    }

    protected boolean isFull() {
        return _maxSize == _players.size();
    }

    public void setMaxSize(int maxSize) {
        _players = new LimitedSizeArrayList<>(_players, maxSize);
        _maxSize = maxSize;
    }

    @Override
    protected OnTileInteractionResult interactOnTileAdditionalOptions(Player player, OnTileInteraction interaction) {
        if (interaction == OnTileInteraction.SCOUT) {
            StringBuilder sb = new StringBuilder();
            sb.append("You have scouted this Tile and found the Players: ").append("\n");
            for (Player p : _players) {
                if (!p.equals(player)) {
                    sb.append(p.getUsername()).append("\n");
                }
            }
            return new OnTileInteractionResult(true, sb.toString());
        }

        return OnTileInteractionResult.emptyFalse();
    }

    @Override
    public boolean removePlayerFromTile(Player player) {
        return _players.remove(player);
    }

    @Override
    public boolean contains(Player player) {
        return _players.contains(player);
    }

    @Override
    public void addPlayerToTile(Player player) throws InteractionTileIsFullException {
        if (!_players.add(player)) {
            throw new InteractionTileIsFullException("The tile is already full.");
        }
    }
}
