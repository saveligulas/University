package org.example.human;

import java.time.Period;

public class LendingProfile {
    private final int _id;
    private final LendingPeriod _lendingPeriod;
    private final double _pricePerObject;
    private final int _extensions;


    private LendingProfile(int weeksForBookOrJournalLendingPeriod, int weeksForOtherLendingPeriod, double lendingPricePerObject, int amountOfExtensions, int id) {
        _lendingPeriod = new LendingPeriod(Period.ofWeeks(weeksForBookOrJournalLendingPeriod), Period.ofWeeks(weeksForOtherLendingPeriod));
        _pricePerObject = lendingPricePerObject;
        _extensions = amountOfExtensions;
        this._id = id;
    }


    public static final LendingProfile EXTERNAL = new LendingProfile(4, 2, 0.5, 1, 0);
    public static final LendingProfile STUDENT = new LendingProfile(4, 2, 0, 1, 1);
    public static final LendingProfile TUTOR = new LendingProfile(8, 2, 0, 2, 2);

    public int getId() {
        return _id;
    }

    public double getPricePerObject() {
        return _pricePerObject;
    }

    public int getExtensions() {
        return _extensions;
    }

    public int getWeeksBookOrJournal() {
        return (_lendingPeriod.bookOrJournal().getDays() + (_lendingPeriod.bookOrJournal().getMonths() * 30)) / 7;
    }

    public int getWeeksOther() {
        return (_lendingPeriod.other().getDays() + (_lendingPeriod.other().getMonths() * 30)) / 7;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof LendingProfile profile)) {
            return false;
        }
        return this._id == profile._id;
    }
}
