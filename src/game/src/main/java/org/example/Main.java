package org.example;

import org.example.ub4.game.ConsoleGame;
simport org.example.ub4.tile.InteractionTile;
import org.example.ub4.tile.Tile;
import org.example.ub4.tile.TileMapFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Tile[][] map = TileMapFactory.createMapAndGetSourceTile(25);
        for (Tile[] row : map) {
            StringBuilder sb = new StringBuilder();
            for (Tile t : row) {
                sb.append(t == null ? "-" : t.shortToString().charAt(0));
            }
            System.out.println(sb);
        }
        InteractionTile sourceTile = TileMapFactory.getSourceTile(map);
        List<Tile> tiles = sourceTile.getAllConnectedTilesRecursive();
        Set<Tile> distinctTiles = new HashSet<>(tiles);
        System.out.println();
        ConsoleGame game = new ConsoleGame();
        game.run(5, 2, sourceTile);
        System.out.println();
    }
}