package org.example.stack;

import org.example.coll.MyCollection;

public class StackCalculator {
    private MyStack<Double> _stack;
    private final String[] _operators = new String[] {"+", "-", "*", "/", "**", "%"};

    public StackCalculator() {
        _stack = null;
    }

    public boolean isOperator(String operator) {
        for (String s : _operators) {
            if (s.equals(operator)) {
                return true;
            }
        }
        return false;
    }

    public int getOperatorIndex(String operator) {
        for (int i = 0; i < _operators.length; i++) {
            if (operator.equals(_operators[i])) {
                return i;
            }
        }
        return -1;
    }

    public double calculate(String args) {
        _stack = new MyStack<>(args.length());
        String[] values = args.split(" ");
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
                _stack.push(Double.valueOf(value));
            }
        }
        return _stack.pop();
    }

    private void fillCollection(MyCollection<Double> collection) {
        while (!_stack.isEmpty()) {
            collection.add(_stack.pop());
        }
    }

    private void add() {
        MyCollection<Double> values = new MyCollection<>();
        fillCollection(values);
        Double result = 0.0;
        for (Double value : values) {
            result += value;
        }
        _stack.push(result);
    }

    private void minus() {
        MyCollection<Double> values = new MyCollection<>();
        fillCollection(values);
        values.reverse();
        Double result = 0.0;
        for (Double value : values) {
            result -= value;
        }
        _stack.push(result);
    }

    private void multiply() {
        MyCollection<Double> values = new MyCollection<>();
        fillCollection(values);
        Double result = 1.0;
        for (Double value : values) {
            result *= value;
        }
        _stack.push(result);
    }

    private void divide() {
        MyCollection<Double> values = new MyCollection<>();
        fillCollection(values);
        values.reverse();
        Double result = values.get(0);
        for (int i = 1; i < values.size(); i++) {
            result /= values.get(i);
        }
        _stack.push(result);
    }

    private void pow() {
        MyCollection<Double> values = new MyCollection<>();
        fillCollection(values);
        Double result = values.get(0);
        for (int i = 1; i < values.size(); i++) {
             result = Math.pow(result, values.get(i));
        }
        _stack.push(result);
    }

    private void modulus() {
        MyCollection<Double> values = new MyCollection<>();
        fillCollection(values);
        Double result = values.get(0);
        for (int i = 1; i < values.size(); i++) {
            result = (double) Math.ceilMod(result.intValue(), values.get(i).intValue());
        }
        _stack.push(result);
    }
}
