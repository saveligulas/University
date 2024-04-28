package org.example.obj;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.WeekFields;

public class LendingInfo {
    private final Integer _lenderId;
    private final int _maxExtensions;
    private boolean _isActive;
    private final LocalDate _startDate;
    private LocalDate _endDate;
    private int _extensions;

    public LendingInfo() {
        _lenderId = null;
        _maxExtensions = -1;
        _isActive = false;
        _startDate = LocalDate.MIN;
        _endDate = LocalDate.MAX;
        _extensions = -1;
    }

    public LendingInfo(Integer lenderId, LocalDate startDate, LocalDate endDate, int maxExtensions) {
        _lenderId = lenderId;
        _maxExtensions = maxExtensions;
        _startDate = startDate;
        _endDate = endDate;
        _extensions = 0;
    }

    public void extend(int weeks) throws ExtensionLimitReachedException {
        if (_extensions + 1 == _maxExtensions) {
            throw new ExtensionLimitReachedException();
        }
        _extensions++;
        _endDate = _endDate.plusWeeks(weeks);
    }

    public Integer getLenderId() {
        return _lenderId;
    }

    public int getMaxExtensions() {
        return _maxExtensions;
    }

    public boolean isActive() {
        return _isActive;
    }

    public LocalDate getStartDate() {
        return _startDate;
    }

    public LocalDate getEndDate() {
        return _endDate;
    }

    public int getExtensions() {
        return _extensions;
    }
}
