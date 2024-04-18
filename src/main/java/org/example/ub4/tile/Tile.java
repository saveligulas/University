package org.example.ub4.tile;

import org.example.coll.MyDictionary;
import org.example.coll.tuple.TupleBidirectional;
import org.example.ub4.player.Player;

import java.util.Optional;


public abstract class Tile {
    private Long _id;
    private Tile _north;
    private Tile _east;
    private Tile _south;
    private Tile _west;

    public MyDictionary<Direction, Tile> getTileMap() {
        MyDictionary<Direction, Tile> tileMap = new MyDictionary<>();
        for (Direction direction : Direction.values()) {
            Optional<Tile> tileInDirection = getTileInDirection(direction);
            tileInDirection.ifPresent(tile -> tileMap.put(direction, tile));
        }
        return tileMap;
    }

    public Optional<Tile> getTileInDirection(Direction direction) {
        return switch (direction) {
            case NORTH -> Optional.of(_north);
            case EAST -> Optional.of(_east);
            case SOUTH -> Optional.of(_south);
            case WEST -> Optional.of(_west);
            default -> Optional.empty();
        };
    }

    public abstract InteractionResult interact(Player player, Interaction interaction);

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

    public Long getId() {
        return _id;
    }
}
