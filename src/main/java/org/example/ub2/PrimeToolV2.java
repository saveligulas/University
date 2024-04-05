package org.example.ub2;

import org.example.ub1.my.MyCollection;
import org.example.ub1.my.MySortedIntegerCollection;

public class PrimeToolV2 {
    private static final MySortedIntegerCollection PRIMES;
    private static int _highestNumberChecked;

    static {
        PRIMES = new MySortedIntegerCollection();
        PRIMES.add(1);
        PRIMES.add(2);
        _highestNumberChecked = 2;
    }

    public MyCollection<Integer> getPrimes(int lowerBound, int upperBound) {
        if (lowerBound > upperBound) {
            throw new IllegalArgumentException("lowerBound must be greater than upperBound");
        }
        if (lowerBound < 0) {
            throw new IllegalArgumentException("lowerBound must be greater than zero");
        }
        if (upperBound <= _highestNumberChecked) {
            int lower = PRIMES.getIndexWhenNumberLarger(lowerBound);
            int upper = PRIMES.getIndexWhenNumberLarger(upperBound);
            return PRIMES.subList(lower, upper);
        }
        expandPrimes(upperBound);
        return getPrimes(lowerBound, upperBound);
    }

    private void expandPrimes(int newMax) {
        int offset = newMax - _highestNumberChecked;
        MyCollection<Boolean> numList = new MyCollection<>();
        numList.fill(offset, Boolean.TRUE);
        int largestNumberToCheck = (int) Math.sqrt(newMax);
        int largestIndexToCheck = PRIMES.getIndexWhenNumberLarger(largestNumberToCheck);
        boolean mustExpandPrimesToCheck = largestIndexToCheck == PRIMES.size() - 1;
        for (int i = 2; i < largestNumberToCheck ; i++) {
            int factor = _highestNumberChecked / i;
            for (int j = i * factor; j < newMax; j += i) {
                numList.set(j - _highestNumberChecked, Boolean.FALSE);
            }
        }
        _highestNumberChecked = newMax;
    }
}
