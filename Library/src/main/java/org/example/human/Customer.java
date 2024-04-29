package org.example.human;

import org.example.lib.Reservation;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Customer {
    private static final AtomicInteger ID_COUNTER = new AtomicInteger();

    private final int _id;
    private final NameOfPerson _name;
    private LendingProfile _profile;
    private List<Reservation> _activeAndOldReservations;

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

    public boolean addReservation(Reservation reservation) {
        return _activeAndOldReservations.add(reservation);
    }

    public LendingProfile getProfile() {
        return _profile;
    }
}