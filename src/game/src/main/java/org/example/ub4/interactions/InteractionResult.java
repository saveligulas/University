package org.example.ub4.interactions;

import org.example.coll.MyCollection;

public class InteractionResult {
    private boolean _success;
    private String _message;
    private MyCollection<Interaction> _possibleNextInteractions;

    public InteractionResult(String message) {
        _success = false;
        _message = message;
        _possibleNextInteractions = new MyCollection<>();
    }

    public static InteractionResult empty(String message) {
        return new InteractionResult(message);
    }
}
