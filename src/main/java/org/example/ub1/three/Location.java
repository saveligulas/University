package org.example.ub1.three;

import org.example.ub1.rect.Point;
import org.example.ub1.rect.Rectangle;

public class Location {
    public static final Location HOME_ISLAND = new Location("Home Island",
            new Rectangle(new Point(0, 0), new Point(500, 500)));
    public static final Location DORNBIRN_ISLAND = new Location("Dornbirn Island",
            new Rectangle(new Point(0, 0), new Point(1500, 1500)));

    public final String NAME;
    public final Rectangle DIMENSIONS;

    public Location(String name, Rectangle dimensions) {
        NAME = name;
        DIMENSIONS = dimensions;
    }
}
