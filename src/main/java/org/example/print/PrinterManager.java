package org.example.print;

import org.example.ub1.my.MyCollection;

public class PrinterManager {
    private static final MyCollection<Printer> PRINTERS = new MyCollection<>();

    static {
        for (FloorPrinter fPrinter : FloorPrinter.values()) {
            PRINTERS.add(fPrinter.getPrinter());
        }
    }

    public static Printer get(int index) {
        return PRINTERS.get(index);
    }
}
