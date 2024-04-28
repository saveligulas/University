package org.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Group {
    private final List<Shape> _shapes;

    public Group(Collection<Shape> shapes) {
        _shapes = new ArrayList<>(shapes);
    }
}
