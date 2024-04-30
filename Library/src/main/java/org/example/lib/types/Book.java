package org.example.lib.types;

import org.example.human.NameOfPerson;
import org.example.lib.BookOrJournalLibraryItem;
import org.example.lib.Category;


public class Book extends BookOrJournalLibraryItem {

    public Book(int copyNumber, String title, String publisher, NameOfPerson intellectualOwner, String identifier, Category... themeCategories) {
        super(copyNumber, title, publisher, intellectualOwner, identifier, Category.BOOK, themeCategories);
    }
}
