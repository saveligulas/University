package org.example.obj;

import java.util.Collection;
import java.util.PriorityQueue;
import java.util.SortedSet;

public class MyPriorityQueue<E> extends PriorityQueue<E> {
    public MyPriorityQueue() {
    }

    public MyPriorityQueue(Collection<? extends E> c) {
        super(c);
    }

    public MyPriorityQueue(PriorityQueue<? extends E> c) {
        super(c);
    }

    public MyPriorityQueue(SortedSet<? extends E> c) {
        super(c);
    }

    public E peekLast() {
        if (isEmpty()) {
            return null;
        }

        // To peek at the last element, we temporarily store elements in another priority queue
        PriorityQueue<E> tempQueue = new PriorityQueue<>(this);
        E lastElement = null;

        while (!tempQueue.isEmpty()) {
            lastElement = tempQueue.poll();
        }

        return lastElement;
    }

    // Other methods of the class
}
