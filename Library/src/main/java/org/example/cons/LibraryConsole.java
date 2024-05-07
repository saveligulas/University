package org.example.cons;

import org.example.human.Customer;
import org.example.lib.Category;
import org.example.lib.Library;
import org.example.lib.Section;

import java.io.IOException;
import java.util.*;

public class LibraryConsole {
    private final Scanner _scanner;
    private final Library _library;

    public LibraryConsole() {
        _scanner = new Scanner(System.in);
        _library = new Library();
    }

    public void run(String bookPath) throws IOException {
        _library.fillWithItems(new ArrayList<>(LibraryItemLoader.readBooks(bookPath)));

        boolean run = true;
        while (run) {
            int userOption = askAndGetResponse("Are you a member or staff?", List.of("Staff", "Member", "Exit"));
            if (userOption == 0) {
                System.out.println("Not yet implemented");
            }
            if (userOption == 1) {
                runMember();
            }
            if (userOption == 2) {
                run = false;
            }
        }
    }

    private void runMember() {
        int id = -1;
        Optional<Customer> customerOptional = _library.getCustomer(id);
        while (_library.getCustomer(id).isEmpty()) {
            System.out.println("Please enter your id:");
            id = _scanner.nextInt();
            customerOptional = _library.getCustomer(id);
        }
        Customer customer = customerOptional.get();
        boolean run = true;
        System.out.println(customer);
        while (run) {
            int userOption = askAndGetResponse("What would you like to do?", List.of("Search item", "Lend item", "Reserve Item", "Return item", "Exit"));
            switch (userOption) {
                case 0 -> runSearchItem(customer);
                case 1 -> runLendItem(customer);
                case 2 -> runReserveItem(customer);
                case 3 -> runReturnItem(customer);
                case 4 -> run = false;
            }
        }
    }

    private void runSearchItem(Customer customer) {
        String query = scanForLine("Enter your search query:");
        Section section = null;
        Category category = null;
        if (ask("Do you want to search by section?")) {
            section = section.values()[askAndGetResponse("Which section?", List.of(Section.values()))];
        }
        if (ask("Do you want to search by category?")) {
            category = Category.values()[askAndGetResponse("Which category?", List.of(Category.values()))];
        }
        System.out.println(_library.search(new SearchRequest(List.of(query.split(" ")), section, category == null ? new ArrayList<>() : new ArrayList<>(List.of(category)))));
    }

    private String getIdentifierZeroIfAbort() {
        System.out.println("Enter a 0 to abort.");
        String identifier = "";
        while (!_library.hasItem(identifier)) {
            identifier = scanForLine("Enter the identifier:");
            if (identifier.equals("0")) {
                return identifier;
            }
        }
        return identifier;
    }

    private void runLendItem(Customer customer) {
        String identifier = getIdentifierZeroIfAbort();
        if (identifier.equals("0")) {
            return;
        }

        System.out.println("How many weeks do you want to lend the item for?");
        int weeks = _scanner.nextInt();

        System.out.println(_library.lendItem(customer, identifier, weeks));
    }

    private void runReserveItem(Customer customer) {
        String identifier = getIdentifierZeroIfAbort();
        if (identifier.equals("0")) {
            return;
        }

        System.out.println("How many weeks do you want to reserve the item for?");
        int weeks = _scanner.nextInt();

        System.out.println(_library.reserveItem(customer, identifier, weeks));
    }

    private void runReturnItem(Customer customer) {
        //TODO
        System.out.println("Not yet implemented");
    }

    private String scanForLine(String question) {
        System.out.println();
        System.out.print(question);
        Scanner stringScanner = new Scanner(System.in);
        return stringScanner.nextLine();
    }

    private List<String> getStringList(List<?> list) {
        List<String> result = new ArrayList<>();
        for (Object o : list) {
            result.add(o.toString());
        }
        return result;
    }

    private String constructQuestion(String question, List<String> options) {
        StringBuilder sb = new StringBuilder();
        sb.append(question).append("\nOptions:");
        for (int i = 0; i < options.size(); i++) {
            sb.append("\n").append(i + 1).append(".").append(options.get(i));
        }
        return sb.toString();
    }

    private <T> Map<Integer, T> getOptionMap(List<T> options) {
        Map<Integer, T> result = new HashMap<>();
        for (int i = 1; i <= options.size(); i++) {
            result.put(i, options.get(i - 1));
        }
        return result;
    }

    private int askAndGetResponse(String question, List<?> options) {
        return askAndGetResponse(constructQuestion(question, getStringList(options)), getOptionMap(options));
    }

    private int askAndGetResponse(String questionWithOptions, Map<Integer, ?> indexOptions) {
        int response = -1;
        while (!indexOptions.containsKey(response)) {
            System.out.println(questionWithOptions);
            System.out.println("Please enter your option:");
            response = _scanner.nextInt();
        }
        return response - 1;
    }

    private String askAndGetResponseString(String question, List<String> options) {
        return options.get(askAndGetResponse(question, options));
    }

    private boolean ask(String question) {
        return askAndGetResponse(question, List.of("Yes", "No")) == 0;
    }
}
