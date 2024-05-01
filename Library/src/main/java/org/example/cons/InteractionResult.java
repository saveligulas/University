package org.example.cons;

import org.example.lib.LibraryItem;

import java.util.ArrayList;
import java.util.List;

public class InteractionResult {
    private final boolean _success;
    private final String _message;

    public InteractionResult(boolean success, String message) {
        _success = success;
        _message = message;
    }

    public String toString() {
        return _success? "Success!" : "Unsuccessful!" + "\n" + _message;
    }
}
