package lib;

import lib.items.Section;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Time {
    private static LocalDate _currentDate = LocalDate.now();

    public static LocalDate getCurrentDate() {
        return _currentDate;
    }
}
