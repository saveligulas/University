package org.example.ub1.three;

import org.example.ub1.rect.Point;

public class LocationPosition {
    private Location _location;
    private Point _position;

    private boolean isInside(Point position) {
        return _location.DIMENSIONS.isInsideRectangle(position);
    }

    public LocationPosition(Location location, Point position) throws IllegalArgumentException {
        if (isInside(position)) {
            throw new IllegalArgumentException();
        }
        _location = location;
        _position = position;
    }

    public void setPosition(Point newPosition) throws IllegalArgumentException {
        if (!isInside(newPosition)) {
            throw new IllegalArgumentException();
        }
        _position = newPosition;
    }

    public Point getPosition() {
        return _position;
    }

    public void setLocation(Location newLocation) {
        _location = newLocation;
    }

    public Location getLocation() {
        return _location;
    }
}
