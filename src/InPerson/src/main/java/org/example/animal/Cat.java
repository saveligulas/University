package org.example.animal;

public class Cat extends Pet {


    public Cat(String name) {
        super(name);
    }

    @Override
    public String getSpeciesName() {
        return "Cat";
    }
}
