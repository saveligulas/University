package org.example.human;

import java.time.Period;

public class LendingProfile {
    public final int id;
    public final LendingPeriod lendingPeriod;
    public final double pricePerObject;
    public final int extensions;


    private LendingProfile(int weeksForBookOrJournalLendingPeriod, int weeksForOtherLendingPeriod, double lendingPricePerObject, int amountOfExtensions, int id) {
        lendingPeriod = new LendingPeriod(Period.ofWeeks(weeksForBookOrJournalLendingPeriod), Period.ofWeeks(weeksForOtherLendingPeriod));
        pricePerObject = lendingPricePerObject;
        extensions = amountOfExtensions;
        this.id = id;
    }


    public static final LendingProfile EXTERNAL = new LendingProfile(4, 2, 0.5, 1, 0);
    public static final LendingProfile STUDENT = new LendingProfile(4, 2, 0, 1, 1);
    public static final LendingProfile TUTOR = new LendingProfile(8, 2, 0, 2, 2);

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof LendingProfile profile)) {
            return false;
        }
        return this.id == profile.id;
    }
}
