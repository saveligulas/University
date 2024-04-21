package org.example.ub4.interactions;

import java.util.List;

public class OnTileInteractionResult extends InteractionResult<OnTileInteraction> {
    public OnTileInteractionResult(boolean success, String message, List<OnTileInteraction> possibleNextInteractions) {
        super(success, message, possibleNextInteractions);
    }

    // Additional methods or overrides if needed

    // Builder for OnTileInteractionResult
    public static class Builder extends InteractionResult.Builder<OnTileInteraction> {
        // Override build() method to return OnTileInteractionResult
        @Override
        public OnTileInteractionResult.Builder success(boolean success) {
            return (OnTileInteractionResult.Builder) super.success(success);
        }

        @Override
        public OnTileInteractionResult.Builder message(String message) {
            return (OnTileInteractionResult.Builder) super.message(message);
        }

        @Override
        public OnTileInteractionResult.Builder addPossibleNextInteraction(OnTileInteraction interaction) {
            return (OnTileInteractionResult.Builder) super.addPossibleNextInteraction(interaction);
        }
        @Override
        public OnTileInteractionResult build() {
            return new OnTileInteractionResult(_success, _message, _possibleNextInteractions);
        }
    }
}
