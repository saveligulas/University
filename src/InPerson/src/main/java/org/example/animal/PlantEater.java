package org.example.animal;

public abstract class PlantEater extends WildAnimal {

    public void eat(Food food) {
        if (food instanceof Plant plant) {
            super.eat(plant);
        }
    }
}
