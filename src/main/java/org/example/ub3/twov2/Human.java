package org.example.ub3.twov2;

import com.github.javafaker.Faker;
import org.example.coll.MyCollection;
import org.example.ub1.three.app.Gender;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;
import java.util.Random;

public class Human {
    //region <Internal static Fields>
    protected static final MarriageBureauV3 MARRIAGE_BUREAU = new MarriageBureauV3();
    protected static final Faker FAKER = new Faker();
    protected static final Random RAND = new Random();
    //endregion
    //region <public final Fields>
    public final Gender GENDER;
    public final LocalDate BIRTHDAY;
    //endregion
    Name name;

    //region <Constructor>
    public Human() {
        this(Gender.values()[RAND.nextInt(2)]);
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
    //endregion

    //region <Public Methods>
    //region <Getters and State of Human>
    public Optional<Human> getPartner() {
        return MARRIAGE_BUREAU.getPartner(this);
    }

    public Optional<MarriageState> getState() {
        return MARRIAGE_BUREAU.getMarriageState(this);
    }

    public String getNameString() {
        return name.toString();
    }

    public boolean hasProposed() {
        return MARRIAGE_BUREAU.hasProposed(this);
    }

    public Optional<Human> getProposalPartner() {
        return MARRIAGE_BUREAU.getPartner(this);
    }

    public boolean hasProposal() {
        return MARRIAGE_BUREAU.hasProposal(this);
    }
    //endregion

    //region <Marriage steps>
    public MarriageBureauResponse propose(Human human) {
        if (this.equals(human)) {
            return new MarriageBureauResponse();
        }
        return MARRIAGE_BUREAU.propose(this, human);
    }

    public MarriageBureauResponse acceptProposal() {
        return MARRIAGE_BUREAU.moveToPlanned(this);
    }

    public MarriageBureauResponse addHonoraryGuests(Human... honoraryGuests) {
        return this.addHonoraryGuests(new MyCollection<>(honoraryGuests));
    }

    public MarriageBureauResponse addHonoraryGuests(MyCollection<Human> honoraryGuests) {
        return MARRIAGE_BUREAU.addBridesmaidsAndGroomsmen(this, honoraryGuests);
    }

    public MarriageBureauResponse addGuests(Human... guests) {
        return this.addGuests(new MyCollection<>(guests));
    }

    public MarriageBureauResponse addGuests(MyCollection<Human> guests) {
        return MARRIAGE_BUREAU.addGuests(this, guests);
    }

    public MarriageBureauResponse finishPlanning() {
        return MARRIAGE_BUREAU.moveToPrepared(this);
    }

    public MarriageBureauResponse marry() {
        return MARRIAGE_BUREAU.marry(this);
    }
    //endregion

    //region <Marriage Cancellation and Divorce>
    public MarriageBureauResponse cancelMarriage() {
        return MARRIAGE_BUREAU.cancelMarriage(this);
    }

    public MarriageBureauResponse divorce(String reason) {
        return MARRIAGE_BUREAU.divorce(this, reason);
    }
    //endregion
    //endregion

    //region <Overwritten Object methods>
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
    //endregion
}
