package org.example.print;

public class PrinterRanOutOfPages extends RuntimeException {
    public PrinterRanOutOfPages() {
        super();
    }

    public PrinterRanOutOfPages(String message) {
        super(message);
    }

    public PrinterRanOutOfPages(String message, Throwable cause) {
        super(message, cause);
    }

    public PrinterRanOutOfPages(Throwable cause) {
        super(cause);
    }
}
