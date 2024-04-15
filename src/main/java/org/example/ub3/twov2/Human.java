package org.example.ub3.twov2;

import com.github.javafaker.Faker;
import org.example.coll.MyCollection;
import org.example.ub1.three.app.Gender;
import org.example.ub3.two.Person;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Random;

public class Human {
    protected static final MarriageBureauV2 MARRIAGE_BUREAU = new MarriageBureauV2();
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

    public boolean propose(Human human) {
        if (this.equals(human)) {
            return false;
        }

        try {
            MARRIAGE_BUREAU.proposeMarriage(this, human);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        return false;
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
}
