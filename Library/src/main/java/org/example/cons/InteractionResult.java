package org.example.cons;

import org.example.lib.LibraryItem;

import java.util.ArrayList;
import java.util.List;

public class InteractionResult {
    private final boolean _success;
    private final String _message;
    private final List<LibraryItem> _items;

    public InteractionResult(boolean success, String message) {
        _success = success;
        _message = message;
        _items = new ArrayList<>();
    }

    public InteractionResult addItems(List<LibraryItem> items) {
        _items.addAll(items);
        return this;
    }
}
