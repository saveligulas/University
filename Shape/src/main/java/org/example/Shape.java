package org.example;


import java.util.Collection;

public abstract class Shape implements CanBeMoved {
    private String _name;
    private Color _color;

    protected void setName(String name) {
        _name = name;
    }

    protected void setColor(Color color) {
        _color = color;
    }

    protected String getName() {
        return _name;
    }

    protected Color getColor() {
        return _color;
    }
}
