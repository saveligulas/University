package org.example.ub3.twov2;

import com.github.javafaker.Faker;
import org.example.Main;
import org.example.coll.MyCollection;
import org.example.ub1.three.app.Gender;
import org.example.ub3.two.Person;
import org.example.ub3.twov2.exc.OccupiedWithMarriageException;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;
import java.util.Random;

public class Human {
    protected static final MarriageBureauV3 MARRIAGE_BUREAU = new MarriageBureauV3();
    protected static final Faker FAKER = new Faker();
    protected static final Random RAND = new Random();
    public final Gender GENDER;
    public final LocalDate BIRTHDAY;
    Name name;

    public Human() {
        this(Gender.values()[RAND.nextInt(1)]);
    }

    public Human(Gender gender) {
        this(gender, FAKER.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }

    public Human(Gender gender, LocalDate birthday) {
        this(gender, birthday, new Name(FAKER.name().firstName(), null, FAKER.name().lastName()));
    }

    public Human(Gender gender, LocalDate birthday, Name name) {
        GENDER = gender;
        BIRTHDAY = birthday;
        this.name = name;
    }

    public Optional<Human> getPartner() {
        return MARRIAGE_BUREAU.getPartner(this);
    }

    public MarriageState getState() {
        try {
            return MARRIAGE_BUREAU.getMarriageState(this);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public boolean propose(Human human) {
        if (this.equals(human)) {
            return false;
        }

        try {
            MARRIAGE_BUREAU.propose(this, human);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Illegal Argument: " + e.getMessage());
        } catch (OccupiedWithMarriageException e) {
            System.out.println("Cannot propose: " + e.getMessage());
        }

        return false;
    }

    public boolean plan() {
        try {
            MARRIAGE_BUREAU.moveToPlanned(this);
            return true;
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void addHonoraryGuests(Human... honoraryGuests) {
        MyCollection<Human> humansToAdd = new MyCollection<>(honoraryGuests);
        try {
            MARRIAGE_BUREAU.addBridesmaidsAndGroomsmen(this, humansToAdd);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addGuests(Human... guests) {
        MyCollection<Human> guestsToAdd = new MyCollection<>(guests);
        try {
            MARRIAGE_BUREAU.addGuests(this, guestsToAdd);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean finishPlanning() {
        try {
            MARRIAGE_BUREAU.moveToPrepared(this);
            return true;
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public String getNameString() {
        return name.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Human human)) {
            return false;
        }
        return (this.name.equals(human.name) &&
                this.GENDER == human.GENDER &&
                this.BIRTHDAY.equals(human.BIRTHDAY));
    }

    @Override
    public String toString() {
        return "Human{" +
                "GENDER=" + GENDER +
                ", BIRTHDAY=" + BIRTHDAY +
                ", name=" + name +
                '}';
    }
}
