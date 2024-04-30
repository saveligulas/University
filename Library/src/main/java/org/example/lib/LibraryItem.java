package org.example.lib;

import org.example.exc.ItemIsNotAvailableException;
import org.example.exc.ReservingItemCustomerHasAlreadyLentException;
import org.example.exc.ReservingItemMultipleTimesConsecutivelyException;
import org.example.human.Customer;
import org.example.human.NameOfPerson;
import org.example.obj.MyPriorityQueue;

import java.time.LocalDate;
import java.util.*;

public abstract class LibraryItem {
    private final int _copyNumber;
    private final String _title;
    private final String _identifier;
    private final String _publisher;
    private final NameOfPerson _intellectualOwner;
    private final Category _primaryCategory;
    private final List<Category> _themeCategories;
    //Must ensure there is always one LendingInfo inside
    MyPriorityQueue<Reservation> _reservations;


    public LibraryItem(int copyNumber, String title, String publisher, NameOfPerson intellectualOwner, String identifier, Category primaryCategory, Category... themeCategories) {
        _copyNumber = copyNumber;
        _title = title;
        _publisher = publisher;
        _intellectualOwner = intellectualOwner;
        _identifier = identifier;
        _reservations = new MyPriorityQueue<>();
        _primaryCategory = primaryCategory;
        _themeCategories = List.of(themeCategories);
    }

    public abstract int getWeeksCustomerCanLendFor(Customer customer);

    public abstract int getMaxExtensions(Customer customer);

    //TODO: add exceptions when a customer wants to reserve a book multiple times in a row
    public Reservation lend(Customer customer, int weeks) {
        if (_reservations.size() > 0 && _reservations.peek().isActive()) {
            throw new ItemIsNotAvailableException();
        }
        LocalDate now = LocalDate.now();
        int weeksMax = getWeeksCustomerCanLendFor(customer);
        LocalDate endDate = weeks <= 0 || weeks > weeksMax ? now.plusWeeks(weeksMax) : now.plusWeeks(weeks);
        return addReservationToQueue(customer, now, endDate, true);
    }

    public Reservation reserve(Customer customer, int weeks) throws ReservingItemCustomerHasAlreadyLentException, ReservingItemMultipleTimesConsecutivelyException {
        return reserve(customer, weeks, getEarliestDateAvailable());
    }

    private Reservation reserve(Customer customer, int weeks, LocalDate startDate) {
        int weeksMax = getWeeksCustomerCanLendFor(customer);
        LocalDate endDate = weeks <= 0 || weeks > weeksMax ? startDate.plusWeeks(weeksMax) : startDate.plusWeeks(weeks);
        return addReservationToQueue(customer, startDate, endDate, false);
    }

    private Reservation addReservationToQueue(Customer customer, LocalDate startDate, LocalDate endDate, boolean isActive) {
        Reservation reservation = new Reservation(customer.getId(), isActive, startDate, endDate, getMaxExtensions(customer));
        _reservations.add(reservation);
        return reservation;
    }

    public boolean hasActiveLender() {
        if (_reservations.isEmpty()) {
            return false;
        }
        return _reservations.peek().isActive();
    }

    public LocalDate getEarliestDateAvailable() {
        return _reservations.peekLast().getEndDate();
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
        return this._identifier.equals(item._identifier) && this._copyNumber == item._copyNumber;
    }
}
