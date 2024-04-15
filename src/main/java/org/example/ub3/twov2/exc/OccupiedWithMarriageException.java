package org.example.ub3.twov2.exc;

public class OccupiedWithMarriageException extends RuntimeException {
    public OccupiedWithMarriageException() {
    }

    public OccupiedWithMarriageException(String message) {
        super(message);
    }

    public OccupiedWithMarriageException(String message, Throwable cause) {
        super(message, cause);
    }

    public OccupiedWithMarriageException(Throwable cause) {
        super(cause);
    }
}
