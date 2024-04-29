package org.example.cons;

import java.util.*;

public class LibraryConsole {
    private final Scanner _scanner;

    public LibraryConsole() {
        _scanner = new Scanner(System.in);
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
