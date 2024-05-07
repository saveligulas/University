package lib;

import lib.items.Book;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {
    private final TimeService _service;
    private final List<Book> books = new ArrayList<>();
    private final List<LendingInformation> _reservations;
    private final List<LendingInformation> _leases;
    private final List<LendingInformation> _records;

    public ItemManager(int copies) {
        _service = TimeService.INSTANCE;
        _reservations = new ArrayList<>();
        _leases = new ArrayList<>();
        _records = new ArrayList<>();
    }
}
