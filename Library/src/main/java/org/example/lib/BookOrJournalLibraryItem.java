package org.example.lib;

import org.example.human.Customer;
import org.example.human.NameOfPerson;

public abstract class BookOrJournalLibraryItem extends LibraryItem {
    public BookOrJournalLibraryItem(String title, String publisher, NameOfPerson intellectualOwner, String identifier) {
        super(title, publisher, intellectualOwner, identifier);
    }

    @Override
    public int getWeeksCustomerCanLendFor(Customer customer) {
        return customer.getProfile().getWeeksBookOrJournal();
    }

    @Override
    public int getMaxExtensions(Customer customer) {
        return customer.getProfile().getExtensions();
    }
}
