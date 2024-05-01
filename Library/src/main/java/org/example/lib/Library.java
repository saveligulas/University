package org.example.lib;

import org.example.cons.InteractionResult;
import org.example.cons.SearchRequest;
import org.example.cons.SearchResult;
import org.example.exc.ItemWithIdentifierAlreadyLentToCustomerException;
import org.example.exc.ReservingItemCustomerHasAlreadyLentException;
import org.example.exc.ReservingItemMultipleTimesException;
import org.example.exc.VideoGameCannotBeReservedException;
import org.example.human.Customer;
import org.example.human.LendingProfile;
import org.example.human.NameOfPerson;

import java.time.LocalDate;
import java.util.*;

public class Library {
    private final HashMap<String, List<LibraryItem>> _identifierInventory = new HashMap<>();
    private final Set<Customer> _customers = new HashSet<>(List.of(new Customer(new NameOfPerson("John", List.of("The Dummy"), "Doe"), LendingProfile.TUTOR)));

    public void fillWithItems(List<LibraryItem> items) {
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

    public Optional<Customer> getCustomer(int id) {
        for (Customer customer : _customers) {
            if (customer.getId() == id) {
                return Optional.of(customer);
            }
        }
        return Optional.empty();
    }

    public boolean hasItem(String identifier) {
        return _identifierInventory.containsKey(identifier);
    }

    public InteractionResult lendItem(Customer customer, String identifier, int weeks) {
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

        Reservation reservation;
        try {
            reservation = itemToLend.lend(customer, weeks);
        } catch (ItemWithIdentifierAlreadyLentToCustomerException e) {
            return new InteractionResult(false, "You already have lent out a book with the same identifier.");
        }


        if (!customer.addReservation(reservation)) {
            throw new RuntimeException("Could not add reservation to customer but book is reserved");
        }

        return new InteractionResult(true, "You have lent out " + itemToLend.getTitle() + " until the " + reservation.getStartDate().toString().substring(0, 10));
    }

    public InteractionResult reserveItem(Customer customer, String identifier, int weeks) {
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

        Reservation reservation;

        try {
            reservation = itemWithShortestReservationQueue.reserve(customer, weeks);
        } catch (ReservingItemMultipleTimesException e) {
            return new InteractionResult(false, "You cannot reserve a book multiple times.");
        } catch (ReservingItemCustomerHasAlreadyLentException e) {
            return new InteractionResult(false, "You cannot reserve a book you have currently lent out.");
        } catch (VideoGameCannotBeReservedException e) {
            return new InteractionResult(false, "You cannot reserve a video game.");
        }

        if (!customer.addReservation(reservation)) {
            throw new RuntimeException("Could not add reservation to customer");
        }

        return new InteractionResult(true, "Reservation was added to your account.\n" +
                "You need to pickup" + itemWithShortestReservationQueue.getTitle() + " on " + reservation.getStartDate().toString().substring(0, 10) +
                "and return it on the " + reservation.getEndDate().toString().substring(0, 10));
    }

    public InteractionResult returnItem(Customer customer, LibraryItem item) { //TODO
        return null;
    }

    public SearchResult search(SearchRequest request) {
        SearchResult result = new SearchResult();

        for (String identifier : _identifierInventory.keySet()) {
            LocalDate earliestDateAvailable = LocalDate.MAX;
            String availability = "";
            for (LibraryItem item : _identifierInventory.get(identifier)) {
                LocalDate nextAvailable = item.getEarliestDateAvailable();
                if (nextAvailable.isBefore(earliestDateAvailable)) {
                    earliestDateAvailable = nextAvailable;
                }
                if (!item.hasActiveLender()) {
                    availability = "Available";
                    break;
                }
            }
            if (availability.equals("")) {
                // extract only the date part
                availability = "Available on: " + earliestDateAvailable.toString().substring(0, 10);
            }

            LibraryItem selectedItem = _identifierInventory.get(identifier).get(0);

            if (!request.getThemeCategories().isEmpty()) {
                boolean allPresent = true;
                for (Category category : request.getThemeCategories()) {
                    if (!selectedItem.getCategories().contains(category)) {
                        allPresent = false;
                        break;
                    }
                }

                if (!allPresent) {
                    continue;
                }
            }

            if (request.getSectionCategory() != null) {
                if (!selectedItem.getSection().equals(request.getSectionCategory())) {
                    continue;
                }
            }

            if (containsMultipleIgnoreCase(selectedItem.getTitle(), request.getMustContains())) {
                result.add(identifier, selectedItem.getTitle(), availability);
            }
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
