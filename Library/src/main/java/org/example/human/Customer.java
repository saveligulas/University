package org.example.human;

import org.example.lib.Reservation;
import org.example.lib.ReservationStatus;

import java.time.LocalDate;
import java.util.ArrayList;
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
        _activeAndOldReservations = new ArrayList<>();
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

    public List<String> getActiveIdentifiers() {
        List<String> identifiers = new ArrayList<>();
        for (Reservation reservation : _activeAndOldReservations) {
            if (reservation.getStatus() == ReservationStatus.PRESENT) {
                identifiers.add(reservation.getIdentifier());
            }
        }
        return identifiers;
    }

    public List<String> getFutureIdentifiers() {
        List<String> identifiers = new ArrayList<>();
        for (Reservation reservation : _activeAndOldReservations) {
            if (reservation.getStatus() == ReservationStatus.FUTURE) {
                identifiers.add(reservation.getIdentifier());
            }
        }
        return identifiers;
    }

    public List<String> getReturnTodayIdentifiers() {
        List<String> identifiers = new ArrayList<>();
        for (Reservation reservation : _activeAndOldReservations) {
            if (reservation.getEndDate().equals(LocalDate.now())) {
                identifiers.add(reservation.getIdentifier());
            }
        }
        return identifiers;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Customer customer)) {
            return false;
        }
        return this._id == customer._id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_id).append(": ").append(_name).append("\nYour active reservations: ");
        for (Reservation r : _activeAndOldReservations) {
            if (r.getStatus() == ReservationStatus.PRESENT) {
                sb.append(r.getTitle()).append("/").append(r.getIdentifier()).append(": ").append(r.getEndDate().toString().substring(0, 10));
            }
        }
        return sb.toString();
    }
}