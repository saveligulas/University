package org.example.ub4.game;

import org.example.ub4.interactions.NeighbourTileInteraction;
import org.example.ub4.interactions.NeighbourTileInteractionResult;
import org.example.ub4.interactions.OnTileInteractionResult;
import org.example.ub4.player.Player;
import org.example.ub4.tile.Direction;
import org.example.ub4.tile.InteractionTile;
import org.example.ub4.tile.Tile;

import java.util.*;

public class ConsoleGame {
    private Game _game;
    private final Scanner _scanner;

    public ConsoleGame() {
        _scanner = new Scanner(System.in);
    }

    public void run(int roundsToPlay, int playerAmount, InteractionTile sourceTile) {
        initializeGame(roundsToPlay, playerAmount, sourceTile);
        for (int i = 0; i < roundsToPlay; i++) {
            System.out.println("Round :" + i + 1);
            for (int p = 0; p < playerAmount; p++) {
                System.out.println(_game.getCurrentPlayer());
                int whatToDo = askAndGetResponse("What action do you want to perform this turn?",
                        new ArrayList<>(List.of("Look at Inventory and consume", "Interact with Tile", "Interact with neighbouring Tile")));
                switch (whatToDo) {
                    case 0:
                        //TODO: Consume and inventory
                        break;
                    case 1:
                        OnTileInteractionResult resultOnTile = getOnTileInteractionResult(_game.getCurrentPlayer());
                        System.out.println(resultOnTile.getSuccessString());
                        System.out.println(resultOnTile.getMessage());
                        break;
                    case 2:
                        NeighbourTileInteractionResult resultOnNeighbour = getNeighbourTileInteractionResult(_game.getCurrentPlayer());
                        System.out.println(resultOnNeighbour.getSuccessString());
                        System.out.println(resultOnNeighbour.getMessage());
                        break;
                }
                System.out.println("Your turn has ended!");
                _game.advancePlayerTurn();
            }
            _game.endRound();
        }
        System.out.println("Game has ended!");
    }



    private OnTileInteractionResult getOnTileInteractionResult(Player player) {
        int onTileSelectedOption = askAndGetResponse("What action do you want to perform on this Tile?",
                _game.getOnTileOptions());
        return player.getTile().interactOnTile(player, _game.getOnTileOptions().get(onTileSelectedOption));
    }

    private NeighbourTileInteractionResult getNeighbourTileInteractionResult(Player player) {
        int tileSelected = askAndGetResponse("What Tile do you want to interact with?",
                List.of(Direction.values()));
        Tile selectedTile = player.getTile().getTileInDirection(Direction.values()[tileSelected]).orElseThrow(() -> new RuntimeException("Invalid input for Tile"));
        List<NeighbourTileInteraction> options = selectedTile.getNeighbourTileInteractions();
        if (options.size() == 0) {
            return new NeighbourTileInteractionResult(false, "No possible Interactions with this Tile.");
        }

        int selectedOption = askAndGetResponse("What Interaction do you want to perform?",
                    options);
        return selectedTile.interactFromNeighbour(_game.getCurrentPlayer(), options.get(selectedOption));
    }

    private void initializeGame(int roundsToPlay, int playerAmount, InteractionTile sourceTile) {
        Set<String> usernames = new HashSet<>();
        for (int i = 0; i < playerAmount; i++) {
            String username;
            do {
                System.out.println("Enter a username:");
                username = _scanner.nextLine();
            } while (usernames.contains(username));
            usernames.add(username);
        }
        _game = GameInitializer.initialize(roundsToPlay, sourceTile, usernames);
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
