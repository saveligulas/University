package org.example.cons;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.example.human.NameOfPerson;
import org.example.lib.Category;
import org.example.lib.types.Book;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LibraryItemLoader {
    public static List<Book> readBooks(String fileName) throws IOException {
        List<Book> books = new ArrayList<>();
        try (FileReader reader = new FileReader(fileName);
             CSVReader csvReader = new CSVReader(reader)) {
            csvReader.readNext();

            String[] line;
            while ((line = csvReader.readNext()) != null) {
                int copyNumber = Integer.parseInt(line[0]);
                String title = line[1];
                String publisherName = line[2];
                NameOfPerson name = getNameOfPerson(line[3]);
                String isbn = line[4];
                List<Category> categoryArray = getCategories(line[5]);

                for (int i = 1; i <= copyNumber; i++) {
                    Book book = new Book(i, title, publisherName, name, isbn, categoryArray);
                    books.add(book);
                }
            }
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
        return books;
    }

    private static List<Category> getCategories(String line) {
        List<String> categories = new ArrayList<>(List.of(line.split(",")));
        List<Category> categoryArray = new ArrayList<>();
        for (int j = 0; j < categories.size(); j++) {
            String categoryName = categories.get(j).trim(); // Trim leading/trailing spaces
            try {
                categoryArray.add(Category.valueOf(categoryName)); // Use valueOf to convert string to enum
            } catch (IllegalArgumentException e) {
                System.err.println("Warning: Invalid category name: " + categoryName + ". Ignoring.");
            }
        }
        return categoryArray;
    }

    private static NameOfPerson getNameOfPerson(String line) {
        List<String> publisherString = new ArrayList<>(List.of(line.split(" ")));
        String firstname = publisherString.get(0);
        String lastname = publisherString.get(publisherString.size() - 1);
        List<String> middleNames = new ArrayList<>();
        if (publisherString.size() > 2) {
            for (int i = 1; i < publisherString.size() - 1; i++) {
                middleNames.add(publisherString.get(i));
            }
        }
        NameOfPerson publisherName = new NameOfPerson(firstname, middleNames, lastname);
        return publisherName;
    }
}
