package org.example.lib;

import org.example.exc.*;
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
    private final Section _section;
    private final List<Category> _categories;
    //Must ensure there is always one LendingInfo inside
    MyPriorityQueue<Reservation> _reservations;


    public LibraryItem(int copyNumber, String title, String publisher, NameOfPerson intellectualOwner, String identifier, Section section, List<Category> themeCategories) {
        _copyNumber = copyNumber;
        _title = title;
        _publisher = publisher;
        _intellectualOwner = intellectualOwner;
        _identifier = identifier;
        _reservations = new MyPriorityQueue<>();
        _section = section;
        _categories = new ArrayList<>(themeCategories);
    }

    public abstract int getWeeksCustomerCanLendFor(Customer customer);

    public abstract int getMaxExtensions(Customer customer);

    //TODO: add more info in the subclasses to have additional information(for example: FSK, Actor List, Audiobook Speakers...)
    // public abstract String getDetails();

    public Reservation lend(Customer customer, int weeks) throws ItemWithIdentifierAlreadyLentToCustomerException {
        if (!_reservations.isEmpty() && hasActiveLender()) {
            throw new ItemIsNotAvailableException();
        }
        if (customer.getActiveIdentifiers().contains(_identifier)) {
            throw new ItemWithIdentifierAlreadyLentToCustomerException();
        }
        LocalDate now = LocalDate.now();
        int weeksMax = getWeeksCustomerCanLendFor(customer);
        LocalDate endDate = weeks <= 0 || weeks > weeksMax ? now.plusWeeks(weeksMax) : now.plusWeeks(weeks);
        return addReservationToQueue(customer, now, endDate, ReservationStatus.PRESENT);
    }

    public Reservation reserve(Customer customer, int weeks) throws ReservingItemCustomerHasAlreadyLentException, ReservingItemMultipleTimesException, VideoGameCannotBeReservedException {
        if (customer.getFutureIdentifiers().contains(_identifier)) {
            throw new ReservingItemMultipleTimesException();
        }
        if (customer.getActiveIdentifiers().contains(_identifier)) {
            throw new ReservingItemCustomerHasAlreadyLentException();
        }

        return reserve(customer, weeks, getEarliestDateAvailable());
    }

    private Reservation reserve(Customer customer, int weeks, LocalDate startDate) {
        int weeksMax = getWeeksCustomerCanLendFor(customer);
        LocalDate endDate = weeks <= 0 || weeks > weeksMax ? startDate.plusWeeks(weeksMax) : startDate.plusWeeks(weeks);
        return addReservationToQueue(customer, startDate, endDate, ReservationStatus.FUTURE);
    }

    private Reservation addReservationToQueue(Customer customer, LocalDate startDate, LocalDate endDate, ReservationStatus status) {
        Reservation reservation = new Reservation(customer.getId(), this._identifier, this._copyNumber, status, startDate, endDate, getMaxExtensions(customer));
        _reservations.add(reservation);
        return reservation;
    }

    public boolean hasActiveLender() {
        if (_reservations.isEmpty()) {
            return false;
        }
        return _reservations.peek().getStatus() == ReservationStatus.PRESENT || _reservations.peek().getStatus() == ReservationStatus.EXPIRED;
    }

    public boolean returnItem(Customer customer) {
        if (_reservations.isEmpty()) {
            return false;
        }
        if (_reservations.peek().getCustomerId()!= customer.getId()) {
            return false;
        }
        _reservations.poll().setStatus(ReservationStatus.RETURNED);
        if (!_reservations.isEmpty()) {
            _reservations.peek().setStatus(ReservationStatus.PRESENT);
        }
        return true;
    }

    public LocalDate getEarliestDateAvailable() {
        if (_reservations.isEmpty()) {
            return LocalDate.now();
        }
        return _reservations.peekLast().getEndDate();
    }

    public String getTitle() {
        return _title;
    }

    public String getIdentifier() {
        return _identifier;
    }

    public String getUniqueIdentifier() {
        return _identifier + "-" + _copyNumber;
    }

    public String getPublisher() {
        return _publisher;
    }

    public NameOfPerson getIntellectualOwner() {
        return _intellectualOwner;
    }

    public int getCopyNumber() {
        return _copyNumber;
    }

    public Section getSection() {
        return _section;
    }

    public List<Category> getCategories() {
        return _categories;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof LibraryItem item)) {
            return false;
        }
        return this.getUniqueIdentifier().equals(item.getUniqueIdentifier());
    }
}
