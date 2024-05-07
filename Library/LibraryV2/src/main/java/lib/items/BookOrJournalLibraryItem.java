package lib.items;

import hum.LendingProfile;
import lib.LibraryItem;

import java.util.Set;


public abstract class BookOrJournalLibraryItem extends LibraryItem {

    public BookOrJournalLibraryItem(String _identifier, int _copyNumber, String _title, String _publisher, String _intellectualOwner, String description, Set<Category> _categories) {
        super(_identifier, _copyNumber, _title, _publisher, _intellectualOwner, description, _categories);
    }

    @Override
    public int getWeeksProfileCanLendFor(LendingProfile profile) {
        return profile.getWeeksBookOrJournal();
    }
}
