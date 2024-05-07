package lib.items;

import java.util.Set;

public class Book extends BookOrJournalLibraryItem {


    public Book(String _identifier, int _copyNumber, String _title, String _publisher, String _intellectualOwner, String description, Set<Category> _categories) {
        super(_identifier, _copyNumber, _title, _publisher, _intellectualOwner, description, _categories);
    }

    @Override
    public Section getSection() {
        return Section.BOOK;
    }

    @Override
    public String getDetails() {
        return "";
    }
}
