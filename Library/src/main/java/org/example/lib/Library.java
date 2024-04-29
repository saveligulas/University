package org.example.lib;

import org.example.cons.Interaction;
import org.example.cons.InteractionResult;
import org.example.human.Customer;

import java.time.LocalDate;
import java.util.*;

public class Library {
    private final HashMap<String, List<LibraryItem>> _identifierInventory = new HashMap<>();
    private final Set<Customer> _customers = new HashSet<>();

    public void fillWithItems(Collection<LibraryItem> items) {
        for (LibraryItem item : items) {
            String identifier = item.getIdentifier();
            if (_identifierInventory.containsKey(identifier)) {
                _identifierInventory.get(identifier).add(item);
            } else {
                _identifierInventory.put(identifier, new ArrayList<>(List.of(item)));
            }
        }
    }

    public boolean addMember(Customer customer) {
        return _customers.add(customer);
    }

    public void addMember(Collection<Customer> customers) {
        _customers.addAll(customers);
    }

    public InteractionResult interact(Interaction interaction, Customer customer, String identifier, int weeks, LibraryItem item) {
        switch (interaction) {
            case SEARCH -> searchItem(customer, identifier);
            case LEND -> lendItem(customer, identifier, weeks);
            case RESERVE -> reserveItem(customer, identifier, weeks);
            case RETURN -> returnItem(customer, item);
        }
    }

    private InteractionResult lendItem(Customer customer, String identifier, int weeks) {
        List<LibraryItem> itemsWithIdentifier = _identifierInventory.get(identifier);
        boolean allOccupied = true;
        LibraryItem itemToLend = null;

        for (LibraryItem item : itemsWithIdentifier) {
            if (!item.hasActiveLender()) {
                allOccupied = false;
                itemToLend = item;
                break;
            }
        }

        if (allOccupied) {
            return new InteractionResult(false, "All copies of the Book are currently lent out. You need to reserve one.");
        }

        Reservation reservation = itemToLend.lend(customer, weeks);
        if (!customer.addReservation(reservation)) {
            throw new RuntimeException("Could not add reservation to customer but book is reserved");
        }

        return new InteractionResult(true, "You have lent out a copy of the book and the Reservation has been added to your account.");
    }

    private InteractionResult reserveItem(Customer customer, String identifier, int weeks) {
        List<LibraryItem> itemsWithIdentifier = _identifierInventory.get(identifier);
        LibraryItem itemWithShortestReservationQueue = null;
        LocalDate earliestDate = LocalDate.MAX;

        for (LibraryItem item : itemsWithIdentifier) {
            if (item.getEarliestDateAvailable().isBefore(earliestDate)) {
                earliestDate = item.getEarliestDateAvailable();
            }
        }

        for (LibraryItem item : itemsWithIdentifier) {
            if (item.getEarliestDateAvailable().equals(earliestDate)) {
                itemWithShortestReservationQueue = item;
                break;
            }
        }

        Reservation reservation = itemWithShortestReservationQueue.reserve(customer, weeks, earliestDate);
        if (!customer.addReservation(reservation)) {
            throw new RuntimeException("Could not add reservation to customer");
        }
        return new InteractionResult(true, "Reservation was added to customer.");
    }

    private InteractionResult searchItem(Customer customer, String name) {
        return null;
    }

    private InteractionResult returnItem(Customer customer, LibraryItem item) {

    }



    //TODO: add csv file read and load of items
}
