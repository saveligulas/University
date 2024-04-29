package org.example.lib;

import org.example.human.Customer;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Library {
    private final Set<LibraryItem> _inventory = new HashSet<>();
    private final Set<LibraryItem> _lentItems = new HashSet<>();
    private final Set<Customer> _customers = new HashSet<>();

    public void fillWithItems(Collection<LibraryItem> items) {
        for (LibraryItem item : items) {
            if (item.hasActiveLender()) {
                _lentItems.add(item);
            } else {
                _inventory.add(item);
            }
        }
    }

    public boolean addMember(Customer customer) {
        return _customers.add(customer);
    }

    public void addMember(Collection<Customer> customers) {
        _customers.addAll(customers);
    }



    //TODO: add csv file read and load of items
}
