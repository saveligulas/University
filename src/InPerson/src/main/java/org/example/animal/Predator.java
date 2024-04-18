package org.example.animal;

public abstract class Predator extends WildAnimal {
    public void eat(Food food) {
        if (food instanceof Meat || food instanceof Animal) {
            super.eat(food);
        }
    }
}
