package org.example.lib;

import org.example.human.Customer;
import org.example.human.NameOfPerson;

public abstract class OtherLibraryItem extends LibraryItem {

    public OtherLibraryItem(int copyNumber, String title, String publisher, NameOfPerson intellectualOwner, String identifier, Category primaryCategory, Category... themeCategories) {
        super(copyNumber, title, publisher, intellectualOwner, identifier, primaryCategory, themeCategories);
    }

    @Override
    public int getWeeksCustomerCanLendFor(Customer customer) {
        return customer.getProfile().getWeeksOther();
    }

    @Override
    public int getMaxExtensions(Customer customer) {
        return customer.getProfile().getExtensions();
    }
}
