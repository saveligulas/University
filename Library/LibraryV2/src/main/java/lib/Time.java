package lib;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Time {
    private static LocalDate _currentDate = LocalDate.now();

    public static LocalDate getCurrentDate() {
        return _currentDate;
    }

    public static int daysBetweenInclusive(LocalDate startDate, LocalDate endDate) {
        return (int) ChronoUnit.DAYS.between(startDate, endDate) + 1;
    }
}
