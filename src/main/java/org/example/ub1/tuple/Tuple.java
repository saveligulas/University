package org.example.ub1.tuple;

import org.jetbrains.annotations.Nullable;

public class Tuple<A, B> {
    @Nullable
    private A _first;
    @Nullable
    private B _second;

    public Tuple() {
        this(null, null);
    }

    public Tuple(A a, B b) {
        _first = a;
        _second = b;
    }

    public void setFirst(A a) {
        _first = a;
    }

    public void setSecond(B b) {
        _second = b;
    }

    public A getFirst() {
        return _first;
    }

    public B getSecond() {
        return _second;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Tuple<?,?> tuple)) {
            return false;
        }
        //TODO: change when MyCollection get() is implemented
        return tuple.getFirst().equals(this.getFirst()); //&& tuple.getSecond().equals(this.getSecond())
    }
}
