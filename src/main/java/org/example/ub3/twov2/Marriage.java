package org.example.ub3.twov2;

import org.example.coll.MyCollection;

import java.time.LocalDate;

public record Marriage(MarriageState state, Human proposer, LocalDate proposalDate, LocalDate marriageDate, String previousSpouseLastName, MyCollection<Human> groomsmen, MyCollection<Human> bridesmaids, MyCollection<Human> guests) {


    public static Marriage updateState(MarriageState state, Marriage marriage) {
        return new Marriage(state, marriage.proposer, marriage.proposalDate, marriage.marriageDate, marriage.previousSpouseLastName, marriage.groomsmen, marriage.bridesmaids, marriage.guests);
    }

    public static Marriage updateMarriageDate(LocalDate date, Marriage marriage) {
        return new Marriage(marriage.state, marriage.proposer, marriage.proposalDate, date, marriage.previousSpouseLastName, marriage.groomsmen, marriage.bridesmaids, marriage.guests);
    }
}
