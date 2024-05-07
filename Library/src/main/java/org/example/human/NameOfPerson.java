package org.example.human;

import java.util.List;

public record NameOfPerson(String first, List<String> middle, String last) {
public static NameOfPerson updateLastName(String newLastName, NameOfPerson name) {
        return new NameOfPerson(name.first, name.middle, newLastName);
        }
        public NameOfPerson() {

        }
}
