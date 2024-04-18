package org.example.ub4.tile;

import org.example.coll.MyCollection;

public class InteractionResult {
    private boolean _success;
    private String _message;
    private MyCollection<Interaction> _possibleNextInteractions;

    public static InteractionResult empty() {
        return new InteractionResult();
    }
}
