package org.example.ub4.interactions;

import java.util.ArrayList;
import java.util.List;

public class NeighbourTileInteractionResult extends InteractionResult<NeighbourTileInteraction> {

    public NeighbourTileInteractionResult(boolean success, String message) {
        this(success, message, new ArrayList<>());
    }

    public NeighbourTileInteractionResult(boolean success, String message, List<NeighbourTileInteraction> possibleNextInteractions) {
        super(success, message, possibleNextInteractions);
    }

    public static NeighbourTileInteractionResult emptyFalse() {
        return new NeighbourTileInteractionResult(false, "Empty", new ArrayList<>());
    }

    public static NeighbourTileInteractionResult emptyTrue() {
        return new NeighbourTileInteractionResult(true, "Empty", new ArrayList<>());
    }

    // Additional methods or overrides if needed

    // Builder for NeighbourTileInteractionResult
    public static class Builder extends InteractionResult.Builder<NeighbourTileInteraction> {
        @Override
        public Builder success(boolean success) {
            return (Builder) super.success(success);
        }

        @Override
        public Builder message(String message) {
            return (Builder) super.message(message);
        }

        @Override
        public Builder addPossibleNextInteraction(NeighbourTileInteraction interaction) {
            return (Builder) super.addPossibleNextInteraction(interaction);
        }
        // Override build() method to return NeighbourTileInteractionResult
        @Override
        public NeighbourTileInteractionResult build() {
            return new NeighbourTileInteractionResult(_success, _message, _possibleNextInteractions);
        }
    }
}
