package org.example.ub1.three.app;

public enum Gender {
    MALE("Male"), FEMALE("Female");
    private final String name;

    Gender(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }
}
