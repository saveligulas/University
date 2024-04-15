package org.example.ub3.twov2;

import org.example.coll.MyCollection;
import org.example.coll.MyDictionary;
import org.example.ub1.three.app.Gender;
import org.example.ub3.twov2.exc.OccupiedWithMarriageException;

import java.util.concurrent.atomic.AtomicLong;

public class MarriageBureauV3 {
    private static final AtomicLong ID_COUNTER = new AtomicLong(100L);
    private final MyDictionary<Human, Long> _humanIds;
    private final MyDictionary<Long, Marriage> _idMarriages;
    private final MyCollection<Divorce> _divorces;

    public MarriageBureauV3() {
        _humanIds = new MyDictionary<>();
        _idMarriages = new MyDictionary<>();
        _divorces = new MyCollection<>();
    }

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

    void propose(Human husband, Human spouse) {
        if (husband.GENDER == spouse.GENDER) {
            throw new IllegalArgumentException("Humans who want to marry can not be of the same gender");
        }

        if (husband.GENDER == Gender.FEMALE) {
            Human temp = husband;
            husband = spouse;
            spouse = temp;
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
    }

    void moveToPlanned(Human proposedHuman) {
        if (!hasProposal(proposedHuman)) {
            throw new IllegalArgumentException("You have no open proposals");
        }

        Long id = _humanIds.get(proposedHuman);
        _idMarriages.put(id, Marriage.updateState(MarriageState.PLANNED, _idMarriages.get(id)));
    }

    void moveToPrepared(Human human) {
        if (!hasPlanned(human)) {
            throw new IllegalArgumentException("You have no planned marriage");
        }

        Long id = _humanIds.get(human);

    }
}
