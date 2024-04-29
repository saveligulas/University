package org.example.cons;

import java.util.List;

public class InteractionResult {
    private final boolean _success;
    private final String _message;

    public InteractionResult(boolean success, String message) {
        _success = success;
        _message = message;
    }
}
