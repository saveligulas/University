package org.example.ub4.game;

import org.example.ub4.interactions.NeighbourTileInteraction;
import org.example.ub4.interactions.NeighbourTileInteractionResult;
import org.example.ub4.interactions.OnTileInteractionResult;
import org.example.ub4.player.Loot;
import org.example.ub4.player.Player;
import org.example.ub4.tile.Tile;
import org.example.ub4.tile.interaction.LootTile;

import java.util.*;

public class ConsoleGame {
    private Game _game;
    private final Scanner _scanner;

    public ConsoleGame() {
        _scanner = new Scanner(System.in);
    }

    public void run(int roundsToPlay, int playerAmount) {
        initializeGame(roundsToPlay, playerAmount);
        for (int i = 0; i < roundsToPlay; i++) {
            System.out.println("Round :" + i + 1);
            for (int p = 0; p < playerAmount; p++) {
                LootTile tile = new LootTile(1);
                tile.addLoot(new Loot("Apple"));
                _game.getCurrentPlayer().setTile(new LootTile(1));
                System.out.println(_game.getCurrentPlayer());
                int whatToDo = askAndGetResponse("What action do you want to perform this turn?",
                        new ArrayList<>(List.of("Look at Inventory and consume", "Interact with Tile", "Interact with neighbouring Tile")));
                switch (whatToDo) {
                    case 0:
                        //TODO: Consume and inventory
                        break;
                    case 1:
                        OnTileInteractionResult resultOnTile = getOnTileInteractionResult(_game.getCurrentPlayer());
                        System.out.println(resultOnTile.getMessage());
                        break;
                    case 2:
                        NeighbourTileInteractionResult resultOnNeighbour = getNeighbourTileInteractionResult(_game.getCurrentPlayer());
                        System.out.println(resultOnNeighbour.getMessage());
                        break;
                }
                System.out.println("Your turn has ended!");
                _game.advancePlayerTurn();
            }
            _game.endRound();
        }
    }



    private OnTileInteractionResult getOnTileInteractionResult(Player player) {
        OnTileInteractionResult onTileInteractionResult = _game.getOnTileOptions();
        int onTileSelectedOption = askAndGetResponse("What action do you want to perform on this Tile?",
                onTileInteractionResult.getPossibleNextInteractions());
        return player.getTile().interactOnTile(player, onTileInteractionResult.getPossibleNextInteractions().get(onTileSelectedOption));
    }

    private NeighbourTileInteractionResult getNeighbourTileInteractionResult(Player player) {
        int tileSelected = askAndGetResponse("What Tile do you want to interact with?",
                List.of("North", "East", "South", "West"));
        Tile selectedTile = _game.getNeighbourintTile(tileSelected + 1);
        NeighbourTileInteractionResult result = _game.getNeighbouringTileOptions(selectedTile);
        if (result.wasSuccessful()) {
            int selectedOption = askAndGetResponse("What Interaction do you want to perform?",
                    result.getPossibleNextInteractions());
            return selectedTile.interactFromNeighbour(_game.getCurrentPlayer(), result.getPossibleNextInteractions().get(selectedOption));
        }

        return new NeighbourTileInteractionResult(false, "No possible Interactions with this Tile.");
    }

    private void initializeGame(int roundsToPlay, int playerAmount) {
        Set<String> usernames = new HashSet<>();
        for (int i = 0; i < playerAmount; i++) {
            String username;
            do {
                System.out.println("Enter a username:");
                username = _scanner.nextLine();
            } while (usernames.contains(username));
            usernames.add(username);
        }
        _game = GameInitializer.initialize(roundsToPlay, usernames);
    }

    private List<String> getStringList(List<?> list) {
        List<String> result = new ArrayList<>();
        for (Object o : list) {
            result.add(o.toString());
        }
        return result;
    }

    private String constructQuestion(String question, List<String> options) {
        StringBuilder sb = new StringBuilder();
        sb.append(question).append("\nOptions:");
        for (int i = 0; i < options.size(); i++) {
            sb.append("\n").append(i + 1).append(".").append(options.get(i));
        }
        return sb.toString();
    }

    private <T> Map<Integer, T> getOptionMap(List<T> options) {
        Map<Integer, T> result = new HashMap<>();
        for (int i = 1; i <= options.size(); i++) {
            result.put(i, options.get(i - 1));
        }
        return result;
    }

    private int askAndGetResponse(String question, List<?> options) {
        return askAndGetResponse(constructQuestion(question, getStringList(options)), getOptionMap(options));
    }

    private int askAndGetResponse(String questionWithOptions, Map<Integer, ?> indexOptions) {
        int response = -1;
        while (!indexOptions.containsKey(response)) {
            System.out.println(questionWithOptions);
            System.out.println("Please enter your option:");
            response = _scanner.nextInt();
        }
        return response - 1;
    }
}
