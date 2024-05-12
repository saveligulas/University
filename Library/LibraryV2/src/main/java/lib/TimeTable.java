package lib;

import java.time.LocalDate;import java.util.*;

public class TimeTable implements Iterable<Boolean> {
    protected final LocalDate startDate;
    protected final LocalDate endDate;
    private final boolean[] availableArray;

    private TimeTable(LocalDate startDate, LocalDate endDate, boolean[] availableArray) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.availableArray = availableArray;
    }

    public static TimeTable of(LocalDate start, LocalDate end, int copies, boolean[]... availableArrays) {
        return new TimeTable(start, end, availableArray);
    }

    public static TimeTable of(LocalDate start, LocalDate end, List<LendingInformation> infoList) {
        
        return new TimeTable(start, end, availableArray);
    }

    @Override
    public Iterator<Boolean> iterator() {
        return new AvailabilityBooleanIterator(availableArray);
    }

    private static class AvailabilityBooleanIterator implements Iterator<Boolean> {
        private int _currentIndex;
        private final boolean[] _values;

        public AvailabilityBooleanIterator(boolean[] availableArray) {
            this._currentIndex = 0;
            _values = availableArray;
        }

        @Override
        public boolean hasNext() {
            return _currentIndex < _values.length;
        }

        @Override
        public Boolean next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return _values[_currentIndex++];
        }
    }
    }
}
