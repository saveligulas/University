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

    public static Direction getDirectionZeroIndexed(int index) {
        return getDirectionOneIndexed(index + 1);
    }

    public static Direction getDirectionOneIndexed(int index) {
        return switch (index) {
            case 1 -> SOUTH;
            case 2 -> WEST;
            case 3 -> NORTH;
            case 4 -> EAST;
            default -> throw new IllegalArgumentException();
        };
    }
}
