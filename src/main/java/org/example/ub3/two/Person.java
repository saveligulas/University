package org.example.ub3.two;

import com.github.javafaker.Faker;
import org.example.coll.MyCollection;
import org.example.ub1.three.app.Gender;
import org.example.ub2.container.Item;

import java.time.LocalDate;
import java.util.Date;
import java.util.Random;

public class Person {
    public static final MarriageBureau MARRIAGE_BUREAU = new MarriageBureau();
    public static final Faker FAKER = new Faker();
    public final Gender GENDER;
    public final Date BIRTH_DATE;
    public final String NAME;
    private String _surname;

    public Person(Gender g) {
        this(g, FAKER.date().birthday(), FAKER.name().firstName(), FAKER.name().lastName());
    }

    public Person(Gender gender, Date birthDate, String name, String surname) {
        GENDER = gender;
        BIRTH_DATE = birthDate;
        NAME = name;
        _surname = surname;
    }

    public String getSurname() {
        return _surname;
    }

    public void setSurname(String surname) {
        _surname = surname;
    }

    public void marry(Person p) {
        if (!this.equals(p)) {
            MARRIAGE_BUREAU.marry(this, p, new MyCollection<>(), new MyCollection<>());
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Person person)) {
            return false;
        }
        return (this.NAME.equals(person.NAME) && this._surname.equals(person._surname) && this.GENDER == person.GENDER && this.BIRTH_DATE.equals(person.BIRTH_DATE));
    }

    @Override
    public String toString() {
        return GENDER + " " + NAME +  " " +_surname + " " + BIRTH_DATE;
    }
}
