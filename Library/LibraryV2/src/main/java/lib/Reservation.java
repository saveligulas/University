package lib;

import java.time.LocalDate;

public class Reservation extends LendingInformation {
    public Reservation(int _id, int _customerId, LocalDate _startDate, LocalDate _endDate) {
        super(_id, _customerId, _startDate, _endDate);
    }
}
