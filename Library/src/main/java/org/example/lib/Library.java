package org.example.lib;

import org.example.human.Lender;
import org.example.obj.LibraryItem;

import java.util.HashSet;
import java.util.Set;

public class Library {
    private final Set<LibraryItem> _inventory = new HashSet<>();
    private final Set<LibraryItem> _lentItems = new HashSet<>();
    private final Set<Lender> _lenders = new HashSet<>();

    //TODO: add csv file read and load of items
}
