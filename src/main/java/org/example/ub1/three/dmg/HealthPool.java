package org.example.ub1.three.dmg;

public class HealthPool {
    private final int _maxHealth;
    private int _shield;
    private int _armor;
    private int _health;

    public HealthPool(int maxHealth) {
        _maxHealth = maxHealth;
        _health = maxHealth / 2;
        _armor = maxHealth / 4;
        _shield = maxHealth - (_health + _armor);
    }

    public int getHealth() {
        return _health + _armor + _shield;
    }

    public void takeDamage(Damage damage) {
        double d = damage.amount();
        switch (damage.type()) {
            case TRUE -> takeTrueDamage(d);
            case MAGICAL -> takeShieldDamage(d);
            case PHYSICAL -> takeArmorDamage(d);
        }

    }

    private void takeTrueDamage(double amount) {
        _health -= amount;
    }

    private void takeShieldDamage(double amount) {

    }

    private void takeArmorDamage(double amount) {

    }
}
