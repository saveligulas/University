package org.example.obj;

import java.util.Optional;

public abstract class LibraryItem {
    private final String _title;
    private final String _identifier;
    private final String _publisher;
    private final NameOfPerson _intellectualOwner;
    private LendingInfo _lendingInfo;


    public LibraryItem(String title, String publisher, NameOfPerson intellectualOwner, String identifier) {
        _title = title;
        _lendingInfo = new LendingInfo();
        _publisher = publisher;
        _intellectualOwner = intellectualOwner;
        _identifier = identifier;
    }

    public boolean hasActiveLender() {
        return _lendingInfo.isActive();
    }

    public LendingInfo getLendingInfo() {
        return _lendingInfo;
    }

}
