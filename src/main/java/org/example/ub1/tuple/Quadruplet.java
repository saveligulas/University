package org.example.ub1.tuple;

public class Quadruplet<T> {
    private final Tuple<T, T> _first;
    private final Tuple<T, T> _second;

    public Quadruplet() {
        _first = new Tuple<>();
        _second = new Tuple<>();
    }

    public T getFirst() {
        return _first.getFirst();
    }

    public void setFirst(T first) {
        _first.setFirst(first);
    }

    public T getSecond() {
        return _first.getSecond();
    }

    public void setSecond(T second) {
        _first.setSecond(second);
    }

    public T getThird() {
        return _second.getFirst();
    }

    public void setThird(T third) {
        _second.setFirst(third);
    }

    public T getFourth() {
        return _second.getSecond();
    }

    public void setFourth(T fourth) {
        _second.setSecond(fourth);
    }
}
