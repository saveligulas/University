package org.example.lib;

import org.example.exc.ExtensionLimitReachedException;

import java.time.LocalDate;


public class Reservation implements Comparable<Reservation> {
    private final Integer _customerId;
    private final int _maxExtensions;
    private boolean _isActive;
    private final LocalDate _startDate;
    private LocalDate _endDate;
    private int _extensions;

    public Reservation() {
        _customerId = null;
        _maxExtensions = -1;
        _isActive = false;
        _startDate = LocalDate.MAX;
        _endDate = LocalDate.MAX;
        _extensions = -1;
    }

    public Reservation(Integer customerId, boolean isActive, LocalDate startDate, LocalDate endDate, int maxExtensions) {
        _customerId = customerId;
        _maxExtensions = maxExtensions;
        _isActive = isActive;
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
        return _customerId;
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

    public void setIsActive(boolean isActive) {
        _isActive = isActive;
    }

    @Override
    public int compareTo(Reservation o) {
        return this._startDate.compareTo(o._startDate);
    }
}
