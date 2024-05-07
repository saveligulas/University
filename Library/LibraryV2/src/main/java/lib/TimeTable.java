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


    public static TimeTable of(LocalDate start, LocalDate end, List<LendingInformation> infoList, int copies) {
        boolean[] availableArray = new boolean[Time.daysBetweenInclusive(start, end)];
        
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
            _values = new boolean[availableArray.length];
            System.arraycopy(availableArray, 0, _values, 0, availableArray.length);
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
