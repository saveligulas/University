package org.example.animal;

public class Dog extends Pet {
    public Dog(String name) {
        super(name);
    }

    @Override
    public String getSpeciesName() {
        return "Dog";
    }
}
