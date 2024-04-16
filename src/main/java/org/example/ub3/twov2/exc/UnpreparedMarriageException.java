package org.example.ub3.twov2.exc;

public class UnpreparedMarriageException extends RuntimeException {
    public UnpreparedMarriageException() {
    }

    public UnpreparedMarriageException(String message) {
        super(message);
    }

    public UnpreparedMarriageException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnpreparedMarriageException(Throwable cause) {
        super(cause);
    }
}
