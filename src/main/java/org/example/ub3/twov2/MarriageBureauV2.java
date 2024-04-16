package org.example.ub3.twov2;

import org.example.coll.MyCollection;
import org.example.coll.tuple.MyBidirectionalTupleDictionary;
import org.example.coll.tuple.TupleBidirectional;
import org.example.ub1.three.app.Gender;

import java.time.LocalDate;

public class MarriageBureauV2 {
//    private final MyBidirectionalTupleDictionary<Human, TupleBidirectional<Human>, Marriage> _coupleMarriageMap;
//    private final MyBidirectionalTupleDictionary<Human, TupleBidirectional<Human>, Divorce> _divorces;
//
//    public MarriageBureauV2() {
//        _coupleMarriageMap = new MyBidirectionalTupleDictionary<>();
//        _divorces = new MyBidirectionalTupleDictionary<>();
//    }
//
//    boolean hasProposals(Human human) {
//        for (TupleBidirectional<Human> couples : _coupleMarriageMap.getKeys()) {
//            if (couples.equalsOneValue(human) && _coupleMarriageMap.get(couples).state() == MarriageState.PROPOSED) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    boolean hasPlanned(Human human) {
//        for (TupleBidirectional<Human> couples : _coupleMarriageMap.getKeys()) {
//            if (couples.equalsOneValue(human) && _coupleMarriageMap.get(couples).state() == MarriageState.PLANNED) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    boolean isMarried(Human human) {
//        for (TupleBidirectional<Human> couples : _coupleMarriageMap.getKeys()) {
//            if (couples.equalsOneValue(human) && _coupleMarriageMap.get(couples).state() == MarriageState.MARRIED) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    boolean isDivorced(Human human) {
//        for (TupleBidirectional<Human> divorcedCouples : _divorces.getKeys()) {
//            if (divorcedCouples.equalsOneValue(human)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    void proposeMarriage(Human husband, Human spouse) { //TODO add the state of marriage checks
//        if (husband.GENDER == spouse.GENDER) {
//            throw new IllegalArgumentException("You can only marry a different gender!");
//        }
//
//        if (husband.GENDER == Gender.FEMALE) {
//            Human temp = husband;
//            husband = spouse;
//            spouse = temp;
//        }
//
//        LocalDate youngestDateToMarry = LocalDate.now().minusYears(18);
//        if (husband.BIRTHDAY.isAfter(youngestDateToMarry) || spouse.BIRTHDAY.isAfter(youngestDateToMarry)) {
//            throw new IllegalArgumentException("Too young to marry!");
//        }
//
//        if (isMarried(husband) || isMarried(spouse)) {
//            throw new IllegalArgumentException("Already married!");
//        }
//
//        Marriage marriage = new Marriage(MarriageState.PLANNED, LocalDate.now(), spouse.name.last(), new MyCollection<>(), new MyCollection<>(), new MyCollection<>());
//        spouse.name = new Name(spouse.name.first(), spouse.name.middle(), husband.name.last());
//        _coupleMarriageMap.put(new TupleBidirectional<>(husband, spouse), marriage);
//    }
//
//    MyCollection<Human> openProposals(Human human) {
//        MyCollection<Human> proposed = new MyCollection<>();
//        if (!hasProposals(human)) {
//            return proposed;
//        }
//
//        for (Marriage relation : _coupleMarriageMap.getBidirectional(human)) {
//            proposed.add()
//        }
//    }
//
//    void divorce(Human human, String reason) {
//        if (!isMarried(human)) {
//            throw new IllegalArgumentException("You are not Married!");
//        }
//
//        TupleBidirectional<Human> coupleToDivorce = null;
//        Marriage marriage = null;
//        for (TupleBidirectional<Human> couple : _coupleMarriageMap.getKeys()) {
//            if (couple.equalsOneValue(human)) {
//                coupleToDivorce = couple;
//                marriage = _coupleMarriageMap.get(couple);
//                break;
//            }
//        }
//
//        if (coupleToDivorce == null) {
//            throw new RuntimeException("Married couple can not be found!");
//        }
//
//        Divorce divorce = new Divorce(LocalDate.now(), reason);
//        if (human.GENDER == Gender.FEMALE) {
//            human.name = new Name(human.name.first(), human.name.middle(), marriage.previousSpouseLastName());
//        }
//
//        if (_coupleMarriageMap.remove(coupleToDivorce)) {
//            _divorces.put(coupleToDivorce, divorce);
//        } else {
//            throw new RuntimeException("couple to divorce could not be removed");
//        }
//    }

}
