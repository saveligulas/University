package org.example.animal;

public abstract class Animal extends NaturalFood {
    protected double _health;
    protected double _energy;
    protected double _weight;

    public abstract String getSpeciesName();
    public void eat(Food food) {
        _energy += food.getEnergy();
    }
    public void energyToWeight() {
        _weight += _energy;
        _energy = 0;
    }
}
