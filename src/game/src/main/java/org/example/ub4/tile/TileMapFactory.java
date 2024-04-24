package org.example.ub4.tile;

import com.github.javafaker.Faker;
import org.example.ub4.player.Loot;
import org.example.ub4.tile.deadzone.Wall;
import org.example.ub4.tile.interaction.LootTile;
import org.example.ub4.tile.interaction.SafeTile;
import org.example.ub4.tile.passthrough.DoorTile;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TileMapFactory {
    /*
    Ideas:
    - Create a map of booleans first in a 2d array in which i define where the tiles will be
    - Check that the created map is possible, so no isolated tiles or tiles that are connected only diagonally
    - Read that map and check if there are places to place rooms, doors, interactions etc.
    - Then create the tile and id them correctly, then connect them in the right order
     */

    private static final Random _r = new Random();

    public static InteractionTile getSourceTile(int size) {
        return getSourceTile(createMapAndGetSourceTile(size));
    }

    public static InteractionTile getSourceTile(Tile[][] tileMap) {
        int size = tileMap.length;
        int nextId = 0;
        for (int row = 0; row < tileMap.length; row++) {
            for (int col = 0; col < tileMap[row].length; col++) {
                if (tileMap[row][col] != null) {
                    Tile currentTile = tileMap[row][col];
                    currentTile.setId(nextId);
                    nextId++;
                    if (col < size - 1 && tileMap[row][col + 1] != null) {
                        currentTile.connectTile(Direction.EAST, tileMap[row][col + 1]);
                    }
                    if (row < size - 1 && tileMap[row + 1][col] != null) {
                        currentTile.connectTile(Direction.SOUTH, tileMap[row + 1][col]);
                    }
                }
            }
        }
        return (InteractionTile) tileMap[0][0];
    }

    public static Tile[][] createMapAndGetSourceTile(int size) {
        boolean[][] locationMap = getBooleanMap(size);
        Tile[][] tileMap = new Tile[size][size];
        List<Integer> oneTileRows = new ArrayList<>();
        for (int row = 0; row < size; row++) {
            int amount = 0;
            for (int col = 0; col < size; col++) {
                if (locationMap[row][col]) {
                    amount++;
                }
            }
            if (amount == 1) {
                oneTileRows.add(row);
            } else {
                fillRowWithInteractionTiles(tileMap[row], locationMap[row]);
            }
        }

        removeAdjacentDuplicates(oneTileRows);
        for (Integer row : oneTileRows) {
            int column = getStart(locationMap[row]);
            tileMap[row][column] = new DoorTile(-1);
        }
        if (oneTileRows.size() > 1) {
            addRooms(tileMap, locationMap, oneTileRows);
        }

        return tileMap;
    }



    //region Step 1
    public static boolean[][] getBooleanMap(int size) {
        boolean[][] locations = new boolean[size][size];
        for (int row = 0; row < locations.length; row++) {
            boolean[] previousRow = row > 0 ? locations[row - 1] : null;
            int start = randomStartingPoint(size, previousRow);
            int length = randomLengthThatConnects(size, previousRow, start);
            for (int j = 0; j < length; j++) {
                locations[row][start + j] = true;
            }
        }
        return locations;
    }

    private static int randomStartingPoint(int size, boolean[] previousRow) {
        int start = _r.nextInt(size);

        if (previousRow == null) {
            return 0;
        }

        while (start > getEnd(previousRow)) {
            start = _r.nextInt(size);
        }
        return start;
    }

    private static int randomLengthThatConnects(int size, boolean[] previousRow, int startPoint) {
        int length = 1;
        if (size - startPoint == length) {
            return length;
        }
        length = _r.nextInt(1, size - startPoint);


        if (previousRow == null) {
            return size / 2;
        }

        while (startPoint + length < getStart(previousRow)) {
            length = _r.nextInt(1, size - startPoint);
        }

        return length;
    }

    private static int getStart(boolean[] row) {
        for (int i = 0; i < row.length; i++) {
            if (row[i]) {
                return i;
            }
        }
        return -1;
    }

    private static int getEnd(boolean[] row) {
        boolean startFound = false;
        for (int i = 0; i < row.length; i++) {
            if (row[i]) {
                startFound = true;
            }
            if (startFound && !row[i]) {
                return i - 1;
            }
        }
        return -1;
    }
    //endregion

    //region Step 2
    private static void fillRowWithInteractionTiles(Tile[] tileRow, boolean[] locationRow) {
        boolean found = false;
        Faker faker = new Faker();
        for (int i = 0; i < locationRow.length; i++) {
            if (locationRow[i]) {
                if (found) {
                    InteractionTile tile;
                    if (_r.nextBoolean()) {
                        LootTile lootTile = new LootTile(-1);
                        int amountToPut = _r.nextInt(4);
                        for (int amount = 0; amount < amountToPut; amount++) {
                            lootTile.addLoot(new Loot(faker.hobbit().character() + " Figure"));
                        }
                        tile = lootTile;
                    } else {
                        tile = new SafeTile(-1);
                    }
                    tileRow[i] = tile;
                } else {
                    tileRow[i] = new SafeTile(-1);
                    found = true;
                }

            }
        }
    }

    public static void removeAdjacentDuplicates(List<Integer> list) {
        int i = 0;
        while (i < list.size() - 1) {
            if (list.get(i).equals(list.get(i + 1))) {
                list.remove(i + 1); // Remove the higher one
            } else {
                i++; // Move to the next element only if no removal occurs
            }
        }
    }

    private static void addRooms(Tile[][] tileMap, boolean[][] locationMap, List<Integer> oneTileRows) {
        int rows = locationMap.length;
        int cols = locationMap[0].length;

        while (oneTileRows.size() > 1) {
            int firstDoorRow = oneTileRows.get(0);
            int secondDoorRow = oneTileRows.get(1);
            for (int row = firstDoorRow + 1; row < secondDoorRow; row++) {
                for (int col = 0; col < locationMap[row].length; col++) {
                    if (locationMap[row][col]) {
                        boolean hasAFalseNeighBour = false;

                        if (col > 0 && !locationMap[row][col - 1]) {
                            tileMap[row][col - 1] = new Wall();
                        }

                        if (col < cols - 1 && !locationMap[row][col + 1]) {
                            tileMap[row][col + 1] = new Wall();
                        }

                        if (row > 0 && !locationMap[row - 1][col]) {
                            tileMap[row - 1][col] = new Wall();
                        }
                        if (row < rows - 1 && !locationMap[row + 1][col]) {
                            tileMap[row + 1][col] = new Wall();
                        }

                    }
                }
            }
            oneTileRows.remove(0);
            oneTileRows.remove(0);
        }
    }
    //endregion
}
