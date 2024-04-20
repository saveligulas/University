package org.example.ub4.interactions;

import java.util.ArrayList;
import java.util.List;

public class InteractionResult {
    private final boolean _success;
    private final String _message;
    private final List<Interaction> _possibleNextInteractions;

    public InteractionResult() {
        this("Empty");
    }

    public InteractionResult(String message) {
        this(false, message, new ArrayList<>());
    }

    public InteractionResult(boolean success, String message, List<Interaction> possibleNextInteractions) {
        _success = success;
        _message = message;
        _possibleNextInteractions = possibleNextInteractions;
    }

    public static InteractionResult emptyUnsuccessful(String message) {
        return new InteractionResult(message);
    }

    public static class Builder {
        private boolean _success;
        private String _message;
        private final List<Interaction> _possibleNextInteractions;

        public Builder() {
            _possibleNextInteractions = new ArrayList<>();
        }

        public Builder success(boolean success) {
            _success = success;
            return this;
        }

        public Builder message(String message) {
            _message = message;
            return this;
        }

        public Builder addPossibleNextInteraction(Interaction interaction) {
            _possibleNextInteractions.add(interaction);
            return this;
        }

        public InteractionResult build() {
            return new InteractionResult(_success, _message, new ArrayList<>(_possibleNextInteractions));
        }
    }
}
