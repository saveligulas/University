package org.example.ub2;

import java.util.HashSet;
import java.util.Random;

public class NameGenerator {
    private final HashSet<String> _nameSet;
    private final int _length;
    private final Random _random;

    public NameGenerator(int length) {
        _nameSet = new HashSet<>();
        _length = length;
        _random = new Random();
    }

    public String generateUniqueName() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < _length; i++) {
            char c = (char) (_random.nextInt(26) + 'a');
            sb.append(c);
        }
        int counter = 0;
        int wordCounter = 0;
        while (_nameSet.contains(sb.toString())) {
            sb.setCharAt(counter % _length, (char) (wordCounter % 26));
            counter++;
            wordCounter++;
            throw new IllegalArgumentException("Found duplicate");
        }
        _nameSet.add(sb.toString());
        return sb.toString();
    }
}
