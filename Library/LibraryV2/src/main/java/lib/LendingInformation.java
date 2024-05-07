package lib;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class LendingInformation {
    private final int _id;
    private final int _customerId;
    private final LocalDate _startDate;
    private final LocalDate _endDate;

    public LendingInformation(int _id, int _customerId, LocalDate _startDate, LocalDate _endDate) {
        this._id = _id;
        this._customerId = _customerId;
        this._startDate = _startDate;
        this._endDate = _endDate;
    }

    public int getId() {
        return _id;
    }

    public int getCustomerId() {
        return _customerId;
    }

    public LocalDate getStartDate() {
        return _startDate;
    }

    public LocalDate getEndDate() {
        return _endDate;
    }

    public int getDays() {
        return (int) (ChronoUnit.DAYS.between(_startDate, _endDate) + 1);
    }
}
