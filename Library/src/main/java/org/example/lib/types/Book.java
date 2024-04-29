package org.example.lib.types;

import org.example.human.NameOfPerson;
import org.example.lib.BookOrJournalLibraryItem;


public class Book extends BookOrJournalLibraryItem {
    public Book(String title, String publisher, NameOfPerson intellectualOwner, String identifier) {
        super(title, publisher, intellectualOwner, identifier);
    }
}
