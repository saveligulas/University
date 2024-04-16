package org.example.ub3.twov2;

import org.example.coll.MyCollection;

public record Name(String first, MyCollection<String> middle, String last) {
    public static Name updateLastName(String newLastName, Name name) {
        return new Name(name.first, name.middle, newLastName);
    }
}
