package lib;

import java.util.HashMap;

public class Library {
    private final HashMap<String, ItemManager> _identifierManagerMap;

    public Library() {
        _identifierManagerMap = new HashMap<>();
    }
}
