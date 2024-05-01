package org.example.lib;

import org.example.cons.Interaction;
import org.example.cons.InteractionResult;
import org.example.cons.SearchRequest;
import org.example.cons.SearchResult;
import org.example.human.Customer;
import org.example.obj.Tuple;

import java.time.LocalDate;
import java.util.*;

public class Library {
    private final HashMap<String, List<LibraryItem>> _identifierInventory = new HashMap<>();
    private final Set<Customer> _customers = new HashSet<>();

    public void fillWithItems(Collection<LibraryItem> items) {
        for (LibraryItem item : items) {
            String identifier = item.getIdentifier();
            if (_identifierInventory.containsKey(identifier)) {
                _identifierInventory.get(identifier).add(item);
            } else {
                _identifierInventory.put(identifier, new ArrayList<>(List.of(item)));
            }
        }
    }

    public boolean addMember(Customer customer) {
        return _customers.add(customer);
    }

    public void addMember(Collection<Customer> customers) {
        _customers.addAll(customers);
    }

    public InteractionResult lendOrReserve(Interaction interaction, Customer customer, String identifier, int weeks) {
        switch (interaction) {
            case LEND -> lendItem(customer, identifier, weeks);
            case RESERVE -> reserveItem(customer, identifier, weeks);
        }
        return new InteractionResult(false, "This action has not been implemented yet.");
    }

    private InteractionResult lendItem(Customer customer, String identifier, int weeks) {
        List<LibraryItem> itemsWithIdentifier = _identifierInventory.get(identifier);
        boolean allOccupied = true;
        LibraryItem itemToLend = null;

        for (LibraryItem item : itemsWithIdentifier) {
            if (!item.hasActiveLender()) {
                allOccupied = false;
                itemToLend = item;
                break;
            }
        }

        if (allOccupied) {
            return new InteractionResult(false, "All copies of the Book are currently lent out. You need to reserve one.");
        }

        Reservation reservation = itemToLend.lend(customer, weeks);
        if (!customer.addReservation(reservation)) {
            throw new RuntimeException("Could not add reservation to customer but book is reserved");
        }

        return new InteractionResult(true, "You have lent out a copy of the book and the Reservation has been added to your account.");
    }

    private InteractionResult reserveItem(Customer customer, String identifier, int weeks) {
        List<LibraryItem> itemsWithIdentifier = _identifierInventory.get(identifier);
        LibraryItem itemWithShortestReservationQueue = null;
        LocalDate earliestDate = LocalDate.MAX;

        for (LibraryItem item : itemsWithIdentifier) {
            if (item.getEarliestDateAvailable().isBefore(earliestDate)) {
                earliestDate = item.getEarliestDateAvailable();
            }
        }

        for (LibraryItem item : itemsWithIdentifier) {
            if (item.getEarliestDateAvailable().equals(earliestDate)) {
                itemWithShortestReservationQueue = item;
                break;
            }
        }

        Reservation reservation = itemWithShortestReservationQueue.reserve(customer, weeks);
        if (!customer.addReservation(reservation)) {
            throw new RuntimeException("Could not add reservation to customer");
        }
        return new InteractionResult(true, "Reservation was added to customer.");
    }

    public InteractionResult returnItem(Customer customer, LibraryItem item) { //TODO
        return null;
    }

    public SearchResult search(SearchRequest request) {
        Set<Tuple<String, String>> allItemsStringAvailable = new HashSet<>();
        for (String identifier : _identifierInventory.keySet()) {
            Tuple<String, String> titleAvailable = new Tuple<>(_identifierInventory.get(identifier).get(0).getTitle(), "");
            LocalDate earliestDateAvailable = LocalDate.MAX;
            for (LibraryItem item : _identifierInventory.get(identifier)) {
                LocalDate nextAvailable = item.getEarliestDateAvailable();
                if (nextAvailable.isBefore(earliestDateAvailable)) {
                    earliestDateAvailable = nextAvailable;
                }
                if (!item.hasActiveLender()) {
                    titleAvailable.setSecond("Available");
                    break;
                }
            }
            if (titleAvailable.getSecond().equals("")) {
                // extract only the date part
                titleAvailable.setSecond("Available on:" +earliestDateAvailable.toString().substring(0, 10));
            }

            LibraryItem selectedItem = _identifierInventory.get(identifier).get(0);

            if (!request.getThemeCategories().isEmpty()) {
                boolean allPresent = true;
                for (Category category : request.getThemeCategories()) {
                    if (!selectedItem.getThemeCategories().contains(category)) {
                        allPresent = false;
                        break;
                    }
                }

                if (!allPresent) {
                    continue;
                }
            }

            if (request.getSectionCategory() != null) {
                if (!selectedItem.getPrimaryCategory().equals(request.getSectionCategory())) {
                    continue;
                }
            }

            if (containsMultipleIgnoreCase(selectedItem.getTitle(), request.getMustContains())) {
                allItemsStringAvailable.add(titleAvailable);
            }
        }

        SearchResult result = new SearchResult();

        for (Tuple<String, String> titleAndMessage : allItemsStringAvailable) {
            result.put(titleAndMessage.getFirst(), titleAndMessage.getSecond());
        }

        return result;
    }

    private boolean containsMultipleIgnoreCase(String mainStr, List<String> searchStrings) {
        String lowerCaseMainStr = mainStr.toLowerCase();
        for (String searchString : searchStrings) {
            if (!lowerCaseMainStr.contains(searchString.toLowerCase())) {
                return false;
            }
        }
        return true;
    }



    //TODO: add csv file read and load of items
}
