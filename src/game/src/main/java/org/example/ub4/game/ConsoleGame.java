package org.example.ub4.game;

import java.util.*;

public class ConsoleGame {
    private Game _game;
    private final Scanner _scanner;

    public ConsoleGame() {
        _scanner = new Scanner(System.in);
    }

    public void run(int roundsToPlay, int playerAmount) {
        initializeGame(roundsToPlay, playerAmount);
    }

    private void initializeGame(int roundsToPlay, int playerAmount) {
        Set<String> usernames = new HashSet<>();
        for (int i = 0; i < playerAmount; i++) {
            String username;
            do {
                username = _scanner.nextLine();
                _scanner.close();
            } while (usernames.contains(username));
            usernames.add(username);
        }
        _game = GameInitializer.initialize(roundsToPlay, usernames);
    }

    private List<String> getStringList(List<Object> list) {
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
            sb.append("\n").append(i).append(".").append(options.get(i));
        }
        return sb.toString();
    }

    private int askAndGetResponse(String questionWithOptions, Map<Integer, ?> indexOptions) {
        int response = -1;
        while (!indexOptions.containsKey(response)) {
            System.out.println(questionWithOptions);
            System.out.println("Please enter your option:");
            response = _scanner.nextInt();
            _scanner.close();
        }
        return response;
    }
}
