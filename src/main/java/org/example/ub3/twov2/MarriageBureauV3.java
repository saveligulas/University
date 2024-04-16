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
    //region <Marriage methods>
    //region <Boolean>
    /**
     * Checks if a human has a proposal to marry another human.
     *
     * @param human the human to check
     * @return true if the human has a proposal, false otherwise
     */
    boolean hasProposal(Human human) {
        Long id = _humanIds.get(human);
        return _idMarriages.get(id) != null && _idMarriages.get(id).state() == MarriageState.PROPOSED && !_idMarriages.get(id).proposer().equals(human);
    }

    /**
     * Checks if a human has proposed to marry another human.
     *
     * @param human the human to check
     * @return true if the human has proposed, false otherwise
     */
    boolean hasProposed(Human human) {
        Long id = _humanIds.get(human);
        return _idMarriages.get(id) != null && _idMarriages.get(id).state() == MarriageState.PROPOSED && _idMarriages.get(id).proposer().equals(human);
    }

    /**
     * Checks if a human has a planned marriage.
     *
     * @param human the human to check
     * @return true if the human has a planned marriage, false otherwise
     */
    boolean hasPlanned(Human human) {
        Long id = _humanIds.get(human);
        return _idMarriages.get(id) != null && _idMarriages.get(id).state() == MarriageState.PLANNED;
    }

    /**
     * Checks if a human has a prepared marriage.
     *
     * @param human the human to check
     * @return true if the human has a prepared marriage, false otherwise
     */
    boolean hasPrepared(Human human) {
        Long id = _humanIds.get(human);
        return _idMarriages.get(id) != null && _idMarriages.get(id).state() == MarriageState.PREPARED;
    }

    /**
     * Checks if a human is married.
     *
     * @param human the human to check
     * @return true if the human is married, false otherwise
     */
    boolean isMarried(Human human) {
        Long id = _humanIds.get(human);
        return _idMarriages.get(id) != null && _idMarriages.get(id).state() == MarriageState.MARRIED;
    }

    /**
     * Checks if a human is occupied with a marriage.
     *
     * @param human the human to check
     * @return true if the human is occupied with a marriage, false otherwise
     */
    boolean isOccupiedWithMarriage(Human human) {
        Long id = _humanIds.get(human);
        return id != null;
    }
    //endregion

    //region <Getters>
    /**
     * Returns the partner of the given human, if the human is currently occupied with a marriage.
     *
     * @param human the human to get the partner of
     * @return the partner of the given human, if the human is currently occupied with a marriage, otherwise an empty optional
     */
    public Optional<Human> getPartner(Human human) {
        if (!isOccupiedWithMarriage(human)) {
            return Optional.empty();
        }

        Long id = _humanIds.get(human);

        return _humanIds.getFirstKeyExcludingKey(id, human);
    }

    /**
     * Returns the marriage state of the given human.
     *
     * @param human the human to get the marriage state of
     * @return the marriage state of the given human
     * @throws IllegalArgumentException if the human is not occupied with a marriage
     */
    public MarriageState getMarriageState(Human human) {
        if (!isOccupiedWithMarriage(human)) {
            throw new IllegalArgumentException("Human is not occupied with marriage");
        }

        Long id = _humanIds.get(human);
        return _idMarriages.get(id).state();
    }
    //endregion
    //endregion

    //region <Divorce State>
    /**
     * Checks if the given human has been divorced.
     *
     * @param human the human to check
     * @return true if the human has been divorced, false otherwise
     */
    public boolean hasDivorced(Human human) {
        for (Divorce divorce : _divorces) {
            if (divorce.initiator().equals(human)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the given human was divorced.
     *
     * @param human the human to check
     * @return true if the human was divorced, false otherwise
     */
    public boolean wasDivorced(Human human) {
        for (Divorce divorce : _divorces) {
            if (divorce.divorcee().equals(human)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the given human was married.
     *
     * @param human the human to check
     * @return true if the human was married, false otherwise
     */
    public boolean wasMarried(Human human) {
        return (wasDivorced(human) || hasDivorced(human));
    }
    //endregion
    //endregion

    //region <Marriage Steps Methods>
    void propose(Human husband, Human spouse) {
        this.propose(husband, spouse, LocalDate.now());
    }

    /**
     * Proposes a marriage between two humans.
     *
     * @param husband    the human who will be the husband
     * @param spouse     the human who will be the wife
     * @param proposalDate the date of the proposal
     */
    void propose(Human husband, Human spouse, LocalDate proposalDate) {
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

        Marriage marriage = new Marriage(MarriageState.PROPOSED, husband, proposalDate, null,
                spouse.name.last(), new MyCollection<>(), new MyCollection<>(), new MyCollection<>());
        _idMarriages.put(marriageId, marriage);
    }

    //region <Planned>
    /**
     * Moves the proposal of a marriage to the planned state.
     *
     * @param proposedHuman the human who was proposed to
     */
    void moveToPlanned(Human proposedHuman) {
        if (!hasProposal(proposedHuman)) {
            throw new IllegalArgumentException("You have no open proposals");
        }

        Long id = _humanIds.get(proposedHuman);
        _idMarriages.put(id, Marriage.updateState(MarriageState.PLANNED, _idMarriages.get(id)));
    }

    /**
     * Adds bridesmaids and groomsmen to a planned marriage.
     *
     * @param human        the human who is getting married
     * @param humansToAdd  the humans who will be added as bridesmaids and groomsmen
     */
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

    /**
     * Adds bridesmaids and groomsmen to a planned marriage.
     *
     * @param human        the human who is getting married
     * @param guests  the humans who will be guests
     */
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

    /**
     * Moves the proposal of a marriage to the planned state.
     *
     * @param human can be either partner of the planned marriage
     */
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

    /**
     * Moves the proposal of a marriage to the planned state.
     *
     * @param human can be either partner fo the prepared marriage
     */
    void marry(Human human, LocalDate date) {
        if (!hasPrepared(human)) {
            throw new IllegalArgumentException("You have no prepared marriage");
        }

        Long id = _humanIds.get(human);
        Marriage marriage = _idMarriages.get(id);
        marriage = Marriage.updateState(MarriageState.MARRIED, marriage);
        _idMarriages.put(id, Marriage.updateMarriageDate(date, marriage));

        Human partner = getPartner(human).get();
        if (human.GENDER == Gender.FEMALE) {
            human.name = Name.updateLastName(partner.name.last(), human.name);
        } else {
            partner.name = Name.updateLastName(human.name.last(), partner.name);
        }
    }


    //endregion

    //region <Cancellation and Divorce>
    /**
     * Removes a marriage from the system.
     *
     * @param human the human involved in the marriage
     */
    private void removeMarriage(Human human) {
        Long id = _humanIds.get(human);
        _idMarriages.remove(id);
        _humanIds.remove(human);
        if (!_humanIds.remove(_humanIds.getKeys(id).get(0))) {
            throw new RuntimeException("Unsuspected error occurred while removing the proposal");
        }
    }

    //TODO: finish Documentation
    void cancelMarriage(Human human) {
        if (!isOccupiedWithMarriage(human)) {
            throw new IllegalArgumentException("You have no open proposals");
        }

        if (isMarried(human)) {
            throw new IllegalStateException("You are already married, you need to divorce");
        }

        removeMarriage(human);
    }

    //TODO: finish Documentation
    void divorce(Human human, String reason) {
        if (!isMarried(human)) {
            throw new IllegalArgumentException("You are not married");
        }

        Long id = _humanIds.get(human);
        Marriage marriage = _idMarriages.get(id);
        Human partner = getPartner(human).get();
        Divorce divorce = new Divorce(LocalDate.now(), human, partner, reason);

        if (human.GENDER == Gender.FEMALE) {
            human.name = Name.updateLastName(marriage.previousSpouseLastName(), human.name);
        } else {
            partner.name = Name.updateLastName(marriage.previousSpouseLastName(), partner.name);
        }
        removeMarriage(human);
        _divorces.add(divorce);
    }
    //endregion
}
