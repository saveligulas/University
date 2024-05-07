package org.example.ub4.excep;

public class TileCanNotBeAccessedException extends Exception {
    public TileCanNotBeAccessedException() {
    }

    public TileCanNotBeAccessedException(String message) {
        super(message);
    }

    public TileCanNotBeAccessedException(String message, Throwable cause) {
        super(message, cause);
    }

    public TileCanNotBeAccessedException(Throwable cause) {
        super(cause);
    }
}
