package org.example.ub4.interactions;

import java.util.ArrayList;
import java.util.List;

public class InteractionResult<T extends Enum<T>> {
    private final boolean _success;
    private final String _message;
    private final List<T> _possibleNextInteractions;

    public InteractionResult() {
        this("Empty");
    }

    public InteractionResult(String message) {
        this(false, message, new ArrayList<>());
    }

    public InteractionResult(boolean success, String message, List<T> possibleNextInteractions) {
        _success = success;
        _message = message;
        _possibleNextInteractions = possibleNextInteractions;
    }

    public boolean wasSuccessful() {
        return _success;
    }

    public String getMessage() {
        return _message;
    }

    public List<T> getPossibleNextInteractions() {
        return _possibleNextInteractions;
    }

    public InteractionResult<T> emptyUnsuccessful(String message) {
        return null;
    }

    public static class Builder<T extends Enum<T>> {
        protected boolean _success = false; // Initialize to default value
        protected String _message = ""; // Initialize to default value
        protected final List<T> _possibleNextInteractions = new ArrayList<>();


        public Builder<T> success(boolean success) {
            _success = success;
            return this;
        }

        public Builder<T> message(String message) {
            _message = message;
            return this;
        }

        public Builder<T> addPossibleNextInteraction(T interaction) {
            _possibleNextInteractions.add(interaction);
            return this;
        }

        public InteractionResult<T> build() {
            return new InteractionResult<T>(_success, _message, new ArrayList<>(_possibleNextInteractions));
        }
    }
}
