package org.example.lib;

import org.example.exc.ItemIsNotAvailableException;
import org.example.exc.ReservationQueueIsEmptyException;
import org.example.human.Customer;
import org.example.human.NameOfPerson;
import org.example.obj.MyPriorityQueue;

import java.time.LocalDate;
import java.util.*;

public abstract class LibraryItem {
    private final String _title;
    private final String _identifier;
    private final String _publisher;
    private final NameOfPerson _intellectualOwner;
    //Must ensure there is always one LendingInfo inside
    MyPriorityQueue<Reservation> _reservations;


    public LibraryItem(String title, String publisher, NameOfPerson intellectualOwner, String identifier) {
        _title = title;
        _publisher = publisher;
        _intellectualOwner = intellectualOwner;
        _identifier = identifier;
        _reservations = new MyPriorityQueue<>(List.of(new Reservation()));
    }

    public abstract int getWeeksCustomerCanLendFor(Customer customer);

    public abstract int getMaxExtensions(Customer customer);

    public Reservation lend(Customer customer, int weeks) {
        if (_reservations.isEmpty()) {
            throw new ReservationQueueIsEmptyException();
        }
        if (_reservations.peek().isActive()) {
            throw new ItemIsNotAvailableException();
        }
        LocalDate now = LocalDate.now();
        int weeksMax = getWeeksCustomerCanLendFor(customer);
        LocalDate endDate = weeks <= 0 || weeks > weeksMax ? now.plusWeeks(weeksMax) : now.plusWeeks(weeks);
        return addReservation(customer, now, endDate, true);
    }

    public Reservation reserve(Customer customer, int weeks, LocalDate startDate) {
        if (_reservations.size() == 1) {
            return _reservations.peek();
        }
        LocalDate start = startDate.equals(LocalDate.MAX) ? _reservations.peekLast().getEndDate().plusWeeks(weeks) : startDate;
        int weeksMax = getWeeksCustomerCanLendFor(customer);
        LocalDate endDate = weeks <= 0 || weeks > weeksMax ? start.plusWeeks(weeksMax) : start.plusWeeks(weeks);
        return addReservation(customer, startDate, endDate, false);
    }

    public Reservation addReservation(Customer customer, LocalDate startDate, LocalDate endDate, boolean isActive) {
        Reservation reservation = new Reservation(customer.getId(), isActive, startDate, endDate, getMaxExtensions(customer));
        _reservations.add(reservation);
        return reservation;
    }

    public boolean hasActiveLender() {
        if (_reservations.isEmpty()) {
            throw new ReservationQueueIsEmptyException();
        }
        return _reservations.peek().isActive();
    }

    public String getTitle() {
        return _title;
    }

    public String getIdentifier() {
        return _identifier;
    }

    public String getPublisher() {
        return _publisher;
    }

    public NameOfPerson getIntellectualOwner() {
        return _intellectualOwner;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof LibraryItem item)) {
            return false;
        }
        return this._identifier.equals(item._identifier);
    }
}
