package org.example.human;

import java.util.concurrent.atomic.AtomicInteger;

public class Customer {
    private static final AtomicInteger ID_COUNTER = new AtomicInteger();

    private final int _id;
    private final NameOfPerson _name;
    private LendingProfile _profile;

    public Customer(NameOfPerson name, LendingProfile profile) {
        _id = ID_COUNTER.getAndIncrement();
        this._name = name;
        _profile = profile;
    }

    public int getId() {
        return _id;
    }

    public NameOfPerson getName() {
        return _name;
    }

    public LendingProfile getProfile() {
        return _profile;
    }
}