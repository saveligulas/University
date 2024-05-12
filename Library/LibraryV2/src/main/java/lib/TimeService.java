package lib;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class TimeService {
    public static final TimeService INSTANCE = new TimeService();

    private TimeService() {

    }

    public int daysBetweenInclusive(LocalDate startDate, LocalDate endDate) {
        return (int) ChronoUnit.DAYS.between(startDate, endDate) + 1;
    }
}
