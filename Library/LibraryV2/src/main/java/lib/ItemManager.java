package lib;

import lib.items.Book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ItemManager {
    private final TimeService _service;
    private final List<Book> _books = new ArrayList<>();
    private final List<LeaseService> _leaseServices = new ArrayList<>();
    public ItemManager(List<Book> books) {
        _service = TimeService.INSTANCE;
        for (int i = 0; i < books.size(); i++) {
            _books.add(books.get(i));
            _leaseServices.add(new LeaseService());
        }
    }
}
