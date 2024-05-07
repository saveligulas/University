package org.example.ub4.custom;

import java.util.ArrayList;
import java.util.Collection;

public class LimitedSizeArrayList<E> extends ArrayList<E> {
    private final int _maxSize;

    public LimitedSizeArrayList(int maxSize) {
        super();
        this._maxSize = maxSize;
    }

    public LimitedSizeArrayList(Collection<? extends E> c, int maxSize) {
        super();
        this._maxSize = maxSize;
        if (c.size() > maxSize) {
            throw new IllegalArgumentException("Collection size exceeds maximum size");
        }
        super.addAll(c);
    }

    @Override
    public boolean add(E e) {
        if (size() < _maxSize) {
            return super.add(e);
        }
        return false; // Or throw an exception, depending on your requirements
    }

    @Override
    public void add(int index, E element) {
        if (size() < _maxSize) {
            super.add(index, element);
        } else {
            throw new UnsupportedOperationException("List is already at maximum size");
        }
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        int newSize = size() + c.size();
        if (newSize <= _maxSize) {
            return super.addAll(c);
        }
        return false; // Or throw an exception
    }
}
