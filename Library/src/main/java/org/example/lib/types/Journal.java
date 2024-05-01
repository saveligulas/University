package org.example.lib.types;

import org.example.human.NameOfPerson;
import org.example.lib.BookOrJournalLibraryItem;
import org.example.lib.Category;
import org.example.lib.Section;

import java.util.List;

public class Journal extends BookOrJournalLibraryItem {

    public Journal(int copyNumber, String title, String publisher, NameOfPerson intellectualOwner, String identifier, List<Category> themeCategories) {
        super(copyNumber, title, publisher, intellectualOwner, identifier, Section.JOURNAL, themeCategories);
    }
}
