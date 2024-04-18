package org.example.ub4.tile;

import org.example.ub4.player.Player;

public class EmptyTile extends DeadZoneTile {

    @Override
    public InteractionResult interact(Player player, Interaction interaction) {
        return null;
    }
}
