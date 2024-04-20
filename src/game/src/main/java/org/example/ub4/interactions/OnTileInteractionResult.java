package org.example.ub4.interactions;

import java.util.List;

public class OnTileInteractionResult extends InteractionResult<OnTileInteraction> {
    public OnTileInteractionResult() {
    }

    public OnTileInteractionResult(String message) {
        super(message);
    }

    public OnTileInteractionResult(boolean success, String message, List<OnTileInteraction> possibleNextInteractions) {
        super(success, message, possibleNextInteractions);
    }
}
