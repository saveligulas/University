package org.example;

import org.example.cons.Interaction;
import org.example.cons.LibraryConsole;
import org.example.human.Customer;
import org.example.human.LendingProfile;
import org.example.human.NameOfPerson;
import org.example.lib.Category;
import org.example.lib.Library;
import org.example.lib.types.Book;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        Book book = new Book(1, "Test Book", "Test Publisher", new NameOfPerson("Lebron", List.of("The King"), "James"), "111-112221-213", new Category[]{Category.HISTORY, Category.FANTASY});
//        Customer customer = new Customer(new NameOfPerson("John", List.of(), "Doe"), LendingProfile.STUDENT);
//        Customer customerExternal = new Customer(new NameOfPerson("John", List.of(), "Doe"), LendingProfile.EXTERNAL);
//        Library lib = new Library();
//        lib.addMember(customer);
//        lib.addMember(customerExternal);
//        lib.fillWithItems(List.of(book));
//        lib.lendOrReserve(Interaction.LEND, customer, "111-112221-213", 3);
//        lib.lendOrReserve(Interaction.RESERVE, customer, "111-112221-213", 5);
//        System.out.println();

        LibraryConsole console = new LibraryConsole();
        try {
            console.run("Library/src/main/resources/books.csv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println();
    }
}