package org.example.ub3.twov2;

import org.example.coll.MyCollection;
import org.example.ub3.twov2.Name;

import java.time.LocalDate;

public record Marriage(MarriageState state, Human proposer, LocalDate date, String previousSpouseLastName, MyCollection<Human> bridesmen, MyCollection<Human> bridesmaids, MyCollection<Human> guests) {


    public static Marriage updateState(MarriageState state, Marriage marriage) {
        return new Marriage(state, marriage.proposer, marriage.date, marriage.previousSpouseLastName, marriage.bridesmen, marriage.bridesmaids, marriage.guests);
    }
}
