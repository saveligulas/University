package org.example.animal;

public abstract class Pet extends Animal {
    private final String _name;

    public Pet(String name) {
        super();
        _name = name;
    }

    @Override
    public void eat(Food food) {
        if (food instanceof PetFood) {
            super.eat(food);
        }
    }

    public String getName() {
        return _name;
    }
}
