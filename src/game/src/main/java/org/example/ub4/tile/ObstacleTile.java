package org.example.ub4.tile;

import org.example.ub4.interactions.Interaction;
import org.example.ub4.obstacle.Obstacle;

import java.util.ArrayList;

public interface ObstacleTile {
    public String getBlockerDescription();
    public ArrayList<Interaction> getPossibleInteractions();
    public boolean isBlocked();
}
