package org.example.print;

enum FloorPrinter {
    ZERO(0), ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5);

    private final int _number;
    private final Printer _printer;

    FloorPrinter(int number) {
        _number = number;
        _printer = new Printer();
    }

    public int getNumber() {
        return _number;
    }

    public Printer getPrinter() {
        return _printer;
    }
}
