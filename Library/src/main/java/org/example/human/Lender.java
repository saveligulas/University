package org.example.human;

import org.example.obj.NameOfPerson;

import java.util.concurrent.atomic.AtomicInteger;

public class Lender {
    private static final AtomicInteger ID_COUNTER = new AtomicInteger();

    private final int _id;
    private final NameOfPerson _name;
    private LendingProfile _profile;

    public Lender(NameOfPerson name, LendingProfile profile) {
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