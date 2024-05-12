package org.example.ub4.tile;

import org.example.ub4.interactions.NeighbourTileInteraction;
import org.example.ub4.interactions.NeighbourTileInteractionResult;
import org.example.ub4.obstacle.Obstacle;
import org.example.ub4.player.Player;
import org.example.ub4.tile.deadzone.Void;

import java.util.*;


public abstract class Tile {
    private int _id;
    private Tile _north;
    private Tile _east;
    private Tile _south;
    private Tile _west;
    private Obstacle _obstacle;
    // is protected to allow subclasses to implement setDescription method
    protected String _description = "";
    protected String _specification = "";

    protected Tile() {
        this(-1);
    }

    public Tile(int id) {
        this(id, new Tile[] {Void.INSTANCE, Void.INSTANCE, Void.INSTANCE, Void.INSTANCE});
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
        setDescription();
        setSpecification();
    }

    //region public Getters
    public HashMap<Direction, Tile> getNeighbourMap() {
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

    public List<Tile> getAllConnectedTilesRecursive() {
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

    public boolean isNeighbour(Tile tile) {
        return getNeighbourMap().containsValue(tile);
    }

    public boolean hasObstacle() {
        return _obstacle!= null;
    }
    //endregion

    //region Fully abstract Methods
    public abstract boolean contains(Player player);

    /**
     * Method is called in constructors and must be implemented to add the correct description
     */
    protected abstract void setDescription();

    protected abstract void setSpecification();
    //endregion

    //region public Methods with abstract Component
    public List<NeighbourTileInteraction> getNeighbourTileInteractions() {
        List<NeighbourTileInteraction> possibleInteractions = new ArrayList<>();
        possibleInteractions.add(NeighbourTileInteraction.DISCOVER);
        possibleInteractions.add(NeighbourTileInteraction.PASS);
        possibleInteractions.addAll(addMorePossibleInteractions());
        return possibleInteractions;
    }

    protected abstract List<NeighbourTileInteraction> addMorePossibleInteractions();

    public NeighbourTileInteractionResult interactFromNeighbour(Player player, NeighbourTileInteraction interaction) {
        if (interaction == NeighbourTileInteraction.DISCOVER) {
            List<NeighbourTileInteraction> possibleInteractions = getNeighbourTileInteractions();
            possibleInteractions.remove(NeighbourTileInteraction.DISCOVER);
            player.addDiscoveredTile(this);
            return new NeighbourTileInteractionResult(true, "You discovered the tile: " + _specification +" -\n" + _description , possibleInteractions);
        }

        if (interaction == NeighbourTileInteraction.PASS) {
            return NeighbourTileInteractionResult.emptyTrue();
        }

        return interactFromNeighbourAdditionalOptions(player, interaction);
    }

    public abstract NeighbourTileInteractionResult interactFromNeighbourAdditionalOptions(Player player, NeighbourTileInteraction interaction);
    //endregion

    //region protected Getters and Setters
    protected Optional<Direction> getDirectionOfNeighbour(Tile tile) {
        if (!isNeighbour(tile)) {
            return Optional.empty();
        }
        if (tile.equals(_north)) {
            return Optional.of(Direction.NORTH);
        }
        if (tile.equals(_east)) {
            return Optional.of(Direction.EAST);
        }
        if (tile.equals(_south)) {
            return Optional.of(Direction.SOUTH);
        }
        if (tile.equals(_west)) {
            return Optional.of(Direction.WEST);
        }
        throw new RuntimeException("Tile is neighbour, but direction could not be determined");
    }

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

    protected void setId(int id) {
        _id = id;
    }
    //endregion

    //TODO: implement
    void setObstacle(Obstacle obstacle) {
        _obstacle = obstacle;
    }

    public void connectTile(Direction directionOfTarget, Tile target) {
        setTile(directionOfTarget, target);
        target.setTile(Direction.getOppositeDirection(directionOfTarget), this);
    }

    //region Object Methods
    public String shortToString() {
        return _specification;
    }

    @Override
    public String toString() {
        return _specification +" -\n" + _description;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Tile tile)) {
            return false;
        }
        return (this._id == tile._id);
    }
    //endregion
}
