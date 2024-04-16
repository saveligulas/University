package org.example.ub3.twov2;

import org.example.coll.MyBidirectionalDictionary;
import org.example.coll.MyCollection;
import org.example.coll.MyDictionary;
import org.example.ub1.three.app.Gender;
import org.example.ub3.twov2.exc.OccupiedWithMarriageException;
import org.example.ub3.twov2.exc.UnpreparedMarriageException;

import java.time.LocalDate;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class MarriageBureauV3 {
    private static final AtomicLong ID_COUNTER = new AtomicLong(100L);
    private final MyBidirectionalDictionary<Human, Long> _humanIds;
    private final MyDictionary<Long, Marriage> _idMarriages;
    private final MyCollection<Divorce> _divorces;

    public MarriageBureauV3() {
        _humanIds = new MyBidirectionalDictionary<>();
        _idMarriages = new MyDictionary<>();
        _divorces = new MyCollection<>();
    }

    //region <State of Human methods>
    boolean hasProposal(Human human) {
        Long id = _humanIds.get(human);
        return _idMarriages.get(id) != null && _idMarriages.get(id).state() == MarriageState.PROPOSED && !_idMarriages.get(id).proposer().equals(human);
    }

    boolean hasProposed(Human human) {
        Long id = _humanIds.get(human);
        return _idMarriages.get(id) != null && _idMarriages.get(id).state() == MarriageState.PROPOSED && _idMarriages.get(id).proposer().equals(human);
    }

    boolean hasPlanned(Human human) {
        Long id = _humanIds.get(human);
        return _idMarriages.get(id) != null && _idMarriages.get(id).state() == MarriageState.PLANNED;
    }

    boolean hasPrepared(Human human) {
        Long id = _humanIds.get(human);
        return _idMarriages.get(id) != null && _idMarriages.get(id).state() == MarriageState.PREPARED;
    }

    boolean isMarried(Human human) {
        Long id = _humanIds.get(human);
        return _idMarriages.get(id) != null && _idMarriages.get(id).state() == MarriageState.MARRIED;
    }

    boolean isOccupiedWithMarriage(Human human) {
        Long id = _humanIds.get(human);
        return id != null;
    }

    public Optional<Human> getPartner(Human human) {
        if (!isOccupiedWithMarriage(human)) {
            return Optional.empty();
        }

        Long id = _humanIds.get(human);

        return _humanIds.getFirstKeyExcludingKey(id, human);
    }

    public MarriageState getMarriageState(Human human) {
        if (!isOccupiedWithMarriage(human)) {
            throw new IllegalArgumentException("Human is not occupied with marriage");
        }

        Long id = _humanIds.get(human);
        return _idMarriages.get(id).state();
    }
    //endregion

    //region <Marriage Steps Methods>
    void propose(Human husband, Human spouse) {
        this.propose(husband, spouse, LocalDate.now());
    }

    void propose(Human husband, Human spouse, LocalDate date) {
        if (husband.GENDER == spouse.GENDER) {
            throw new IllegalArgumentException("Humans who want to marry can not be of the same gender");
        }

        if (husband.GENDER == Gender.FEMALE) {
            Human temp = husband;
            husband = spouse;
            spouse = temp;
        }

        LocalDate youngestDateToMarry = LocalDate.now().minusYears(18);
        if (husband.BIRTHDAY.isAfter(youngestDateToMarry)) {
            throw new IllegalArgumentException("Husband too young to marry");
        }
        if (spouse.BIRTHDAY.isAfter(youngestDateToMarry)) {
            throw new IllegalArgumentException("Spouse too young to marry");
        }

        if (isOccupiedWithMarriage(husband)) {
            throw new OccupiedWithMarriageException("Husband is already occupied with a marriage");
        }
        if (isOccupiedWithMarriage(spouse)) {
            throw new OccupiedWithMarriageException("Spouse is already occupied with a marriage");
        }

        Long marriageId = ID_COUNTER.getAndIncrement();
        _humanIds.put(husband, marriageId);
        _humanIds.put(spouse, marriageId);

        Marriage marriage = new Marriage(MarriageState.PROPOSED, husband, date, null,
                spouse.name.last(), new MyCollection<>(), new MyCollection<>(), new MyCollection<>());
        _idMarriages.put(marriageId, marriage);
    }

    //region <Planned>
    void moveToPlanned(Human proposedHuman) {
        if (!hasProposal(proposedHuman)) {
            throw new IllegalArgumentException("You have no open proposals");
        }

        Long id = _humanIds.get(proposedHuman);
        _idMarriages.put(id, Marriage.updateState(MarriageState.PLANNED, _idMarriages.get(id)));
    }

    void addBridesmaidsAndGroomsmen(Human human, MyCollection<Human> humansToAdd) {
        if (!hasPlanned(human)) {
            throw new IllegalArgumentException("You have no planned marriage");
        }

        Long id = _humanIds.get(human);
        Marriage marriage = _idMarriages.get(id);

        for (Human groomsmenOrBridesmaid : humansToAdd) {
            if (groomsmenOrBridesmaid.GENDER == Gender.MALE) {
                marriage.groomsmen().add(groomsmenOrBridesmaid);
            } else {
                marriage.bridesmaids().add(groomsmenOrBridesmaid);
            }
        }
    }

    void addGuests(Human human, MyCollection<Human> guests) {
        if (!hasPlanned(human)) {
            throw new IllegalArgumentException("You have no planned marriage");
        }

        Long id = _humanIds.get(human);
        Marriage marriage = _idMarriages.get(id);

        for (Human guest : guests) {
            marriage.guests().add(guest);
        }
    }
    //endregion

    void moveToPrepared(Human human) {
        if (!hasPlanned(human)) {
            throw new IllegalArgumentException("You have no planned marriage");
        }

        Long id = _humanIds.get(human);
        Marriage marriage = _idMarriages.get(id);

        String errorMessage = null;
        if (marriage.groomsmen().size() == 0) {
            errorMessage = "You have not enough groomsmen";
        }
        if (marriage.bridesmaids().size() == 0) {
            errorMessage = "You have not enough bridesmaids";
        }
        if (marriage.guests().size() == 0) {
            errorMessage = "You have not enough guests";
        }
        if (errorMessage != null) {
            throw new UnpreparedMarriageException(errorMessage);
        }

        _idMarriages.put(id, Marriage.updateState(MarriageState.PREPARED, _idMarriages.get(id)));
    }

    void marry(Human human) {
        this.marry(human, LocalDate.now());
    }

    void marry(Human human, LocalDate date) {
        if (!hasPrepared(human)) {
            throw new IllegalArgumentException("You have no prepared marriage");
        }

        Long id = _humanIds.get(human);
        Marriage marriage = _idMarriages.get(id);

        _idMarriages.put(id, Marriage.updateMarriageDate(date, _idMarriages.get(id)));
    }


    //endregion
}
