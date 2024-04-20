package org.example.ub4.tile;

public enum Direction {
    NORTH, EAST, SOUTH, WEST;

    public static Direction getOppositeDirection(Direction direction) {
        return switch (direction) {
            case NORTH -> SOUTH;
            case EAST -> WEST;
            case SOUTH -> NORTH;
            case WEST -> EAST;
            default -> throw new IllegalArgumentException();
        };
    }
}
