package org.example.stack;

public class StackIsEmptyException extends IllegalPostfixExpressionException {
    public StackIsEmptyException() {
        super("Stack was or has become empty during calculation");
    }
}
