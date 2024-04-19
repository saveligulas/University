package org.example.ub4.player;

import org.example.coll.MyCollection;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String _username;
    private final List<Consumable> _consumables;
    private final List<String> _answers;

    public Player(String username) {
        _username = username;
        _consumables = new ArrayList<>();
        _answers = new ArrayList<>();
    }
}
