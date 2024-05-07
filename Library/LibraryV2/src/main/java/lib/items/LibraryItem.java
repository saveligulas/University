package lib;

import hum.LendingProfile;
import lib.items.Category;
import lib.items.Section;

import java.util.Collections;
import java.util.Set;

public abstract class LibraryItem {
    private final String _identifier;
    private final int _copyNumber;

    private final String _title;
    private final String _publisher;
    private final String _intellectualOwner;
    private String _description;
    private Set<Category> _categories;

    public LibraryItem(String _identifier, int _copyNumber, String _title, String _publisher, String _intellectualOwner, String description, Set<Category> _categories) {
        this._identifier = _identifier;
        this._copyNumber = _copyNumber;
        this._title = _title;
        this._publisher = _publisher;
        this._intellectualOwner = _intellectualOwner;
        this._description = description;
        this._categories = _categories;
    }

    public abstract Section getSection();
    public abstract String getDetails();
    public abstract int getWeeksProfileCanLendFor(LendingProfile profile);

    public String getUniqueIdentifier() {
        return _identifier + "-" + _copyNumber;
    }

    public String getIdentifier() {
        return _identifier;
    }

    public int getCopyNumber() {
        return _copyNumber;
    }

    public String getTitle() {
        return _title;
    }

    public String getPublisher() {
        return _publisher;
    }

    public String getIntellectualOwner() {
        return _intellectualOwner;
    }

    public String getDescription() {
        return _description;
    }

    public Set<Category> getCategories() {
        return Collections.unmodifiableSet(_categories);
    }

    public void setCategories(Set<Category> _categories) {
        this._categories = _categories;
    }

    public boolean addCategory(Category category) {
        return _categories.add(category);
    }

    public boolean removeCategory(Category category) {
        return _categories.remove(category);
    }

}
