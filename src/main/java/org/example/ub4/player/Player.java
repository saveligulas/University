package org.example.ub4.player;

import org.example.coll.MyCollection;

public class Player {
    private final String _username;
    private final MyCollection<Consumable> _consumables;
    private final MyCollection<String> _answers;

    public Player(String username) {
        _username = username;
        _consumables = new MyCollection<>();
        _answers = new MyCollection<>();
    }
}
