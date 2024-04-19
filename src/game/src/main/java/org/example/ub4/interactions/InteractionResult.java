package org.example.ub4.interactions;

import java.util.ArrayList;
import java.util.List;

public class InteractionResult {
    private boolean _success;
    private String _message;
    private List<Interaction> _possibleNextInteractions;

    public InteractionResult(String message) {
        _success = false;
        _message = message;
        _possibleNextInteractions = new ArrayList<>();
    }

    public static InteractionResult empty(String message) {
        return new InteractionResult(message);
    }
}
