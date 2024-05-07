package org.example.lib;

import org.example.exc.ExtensionLimitReachedException;

import java.time.LocalDate;


public class Reservation implements Comparable<Reservation> {
    private final Integer _customerId;
    private final String _identifier;
    private final int _copyNumber;
    private final String _title;
    private final int _maxExtensions;
    private ReservationStatus _status;
    private final LocalDate _startDate;
    private LocalDate _endDate;
    private int _extensions;

    public Reservation(Integer customerId, String identifier, int copyNumber, String title, ReservationStatus status, LocalDate startDate, LocalDate endDate, int maxExtensions) {
        _customerId = customerId;
        _identifier = identifier;
        _copyNumber = copyNumber;
        _title = title;
        _maxExtensions = maxExtensions;
        _status = status;
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

    public Integer getCustomerId() {
        return _customerId;
    }

    public String getTitle() {
        return _title;
    }

    public int getMaxExtensions() {
        return _maxExtensions;
    }

    public ReservationStatus getStatus() {
        return _status;
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

    public String getIdentifier() {
        return _identifier;
    }

    public int getCopyNumber() {
        return _copyNumber;
    }

    public void setStatus(ReservationStatus status) {
        _status = status;
    }

    @Override
    public int compareTo(Reservation o) {
        return this._startDate.compareTo(o._startDate);
    }
}
