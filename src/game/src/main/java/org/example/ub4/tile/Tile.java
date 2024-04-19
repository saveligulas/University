package org.example.ub4.tile;

import org.example.coll.MyDictionary;
import org.example.ub4.interactions.Interaction;
import org.example.ub4.interactions.InteractionResult;
import org.example.ub4.player.Player;

import java.util.*;


public abstract class Tile {
    private int _id;
    private Tile _north;
    private Tile _east;
    private Tile _south;
    private Tile _west;

    public Tile(int id) {
        _id = id;
    }

    public Tile(int id, Tile[] neighbours) {
        this(id, neighbours[0], neighbours[1], neighbours[2], neighbours[3]);
    }

    public Tile(int id, Tile north, Tile east, Tile south, Tile west) {
        _id = id;
        _north = north;
        _east = east;
        _south = south;
        _west = west;
    }

    public HashMap<Direction, Tile> getTileMap() {
        HashMap<Direction, Tile> tileMap = new HashMap<>();
        for (Direction direction : Direction.values()) {
            Optional<Tile> tileInDirection = getTileInDirection(direction);
            tileInDirection.ifPresent(tile -> tileMap.put(direction, tile));
        }
        return tileMap;
    }

    public Optional<Tile> getTileInDirection(Direction direction) {
        return switch (direction) {
            case NORTH -> getNorth();
            case EAST -> getEast();
            case SOUTH -> getSouth();
            case WEST -> getWest();
            default -> Optional.empty();
        };
    }

    public List<Tile> getAllConnectedTiles() {
        List<Tile> connectedTiles = new ArrayList<>();
        Set<Tile> visited = new HashSet<>();
        Queue<Tile> queue = new LinkedList<>();

        queue.add(this);
        visited.add(this);

        while (!queue.isEmpty()) {
            Tile current = queue.poll();
            connectedTiles.add(current);

            if (current._north != null && !visited.contains(current._north)) {
                queue.add(current._north);
                visited.add(current._north);
            }
            if (current._east != null && !visited.contains(current._east)) {
                queue.add(current._east);
                visited.add(current._east);
            }
            if (current._south != null && !visited.contains(current._south)) {
                queue.add(current._south);
                visited.add(current._south);
            }
            if (current._west != null && !visited.contains(current._west)) {
                queue.add(current._west);
                visited.add(current._west);
            }
        }

        connectedTiles.sort(Comparator.comparingInt(Tile::getId));

        return connectedTiles;
    }

    public int getId() {
        return _id;
    }

    public abstract InteractionResult interactFromNeighbouringTile(Player player, Interaction interaction);

    protected Optional<Tile> getNorth() {
        return Optional.ofNullable(_north);
    }

    protected Optional<Tile> getEast() {
        return Optional.ofNullable(_east);
    }

    protected Optional<Tile> getSouth() {
        return Optional.ofNullable(_south);
    }

    protected Optional<Tile> getWest() {
        return Optional.ofNullable(_west);
    }

    void setTiles(Tile[] tilesOrderedByDirection) {
        for (int i = 0; i < 4; i++) {
            Tile tile = tilesOrderedByDirection[i];
            if (tile != null) {
                switch (i) {
                    case 0 -> setNorth(tile);
                    case 1 -> setEast(tile);
                    case 2 -> setSouth(tile);
                    case 3 -> setWest(tile);
                }
            }
        }
    }

    protected void setTile(Direction direction, Tile tile) {
        switch (direction.ordinal()) {
            case 0 -> setNorth(tile);
            case 1 -> setEast(tile);
            case 2 -> setSouth(tile);
            case 3 -> setWest(tile);
        }
    }

    protected void setNorth(Tile tile) {
        _north = tile;
    }

    protected void setEast(Tile tile) {
        _east = tile;
    }

    protected void setSouth(Tile tile) {
        _south = tile;
    }

    protected void setWest(Tile tile) {
        _west = tile;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Tile tile)) {
            return false;
        }
        return (this._id == tile._id);
    }
}