package org.example.ub1.three;

import org.example.stack.Stack;

public class BackPack {
    private final Stack _stack;


    public BackPack(BackPackType type) {
        _stack = new Stack(type.getSize());
    }
}
