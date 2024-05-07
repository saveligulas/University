package org.example.stack;

import org.example.coll.MyCollection;

public class StackCalculator {
    private MyStack<Double> _stack;
    private final String[] _operators = new String[] {"+", "-", "*", "/", "**", "%"};
    private final int _maxSize;

    public StackCalculator(int size) {
        _stack = null;
        _maxSize = size;
    }

    private boolean isOperator(String operator) {
        for (String s : _operators) {
            if (s.equals(operator)) {
                return true;
            }
        }
        return false;
    }

    private int getOperatorIndex(String operator) {
        for (int i = 0; i < _operators.length; i++) {
            if (operator.equals(_operators[i])) {
                return i;
            }
        }
        return -1;
    }

    public double calculate(String args) throws IllegalPostfixExpressionException, StackIsFullException {
        _stack = new MyStack<>(args.length());
        String[] values = args.split(" ");
        if (values.length > _maxSize) {
            throw new StackIsFullException();
        }
        if (values.length == 0) {
            throw new StackIsEmptyException();
        }
        if (!isOperator(values[values.length - 1])) {
            throw new IllegalPostfixExpressionException("Last expression must be an operator not - " + values[values.length - 1]);
        }
        try {
            for (String value : values) {
                int operatorIndex = getOperatorIndex(value);
                if (operatorIndex >= 0) {
                    switch (operatorIndex) {
                        case 0 -> add();
                        case 1 -> minus();
                        case 2 -> multiply();
                        case 3 -> divide();
                        case 4 -> pow();
                        case 5 -> modulus();
                    }
                } else {
                    try {
                        _stack.push(Double.valueOf(value));
                    } catch(NumberFormatException e) {
                        throw new IllegalPostfixExpressionException("Invalid expression - " + value);
                    }
                }
            }
            if (_stack.size() > 1) {
                StringBuilder sb = new StringBuilder();
                sb.append("Too many numbers/Too few operators (operators = amount of numbers + 1)\n");
                sb.append("Result: ").append(_stack.pop()).append("\n");
                sb.append("Leftovers: ");
                while (!_stack.isEmpty()) {
                    sb.append(_stack.pop()).append(" ");
                }
                throw new IllegalPostfixExpressionException(sb.toString());
            }
            return _stack.pop();
        } catch (NullPointerException e) {
            throw new StackIsEmptyException();
        }
    }

    private void add() {
        double result = _stack.pop() + _stack.pop();
        _stack.push(result);
    }

    private void minus() {
        double second = _stack.pop();
        double first = _stack.pop();
        double result = first - second;
        _stack.push(result);
    }

    private void multiply() {
        double result = _stack.pop() * _stack.pop();
        _stack.push(result);
    }

    private void divide() throws IllegalPostfixExpressionException {
        double second = _stack.pop();
        double first = _stack.pop();

        if (second == 0) {
            throw new IllegalPostfixExpressionException("Division by zero");
        }

        double result = first / second;

        _stack.push(result);
    }

    private void pow() {
        double second = _stack.pop();
        double first = _stack.pop();
        double result = Math.pow(first, second);
        _stack.push(result);
    }

    private void modulus() {
        double second = _stack.pop();
        double first = _stack.pop();
        double result = first % second;
        _stack.push(result);
    }

    public int getSize() {
        return _maxSize;
    }
}
