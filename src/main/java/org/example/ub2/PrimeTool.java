package org.example.ub2;

import org.example.ub1.my.MyCollection;
import org.example.ub1.my.MySortedIntegerCollection;
import org.example.ub1.tuple.Tuple;

import javax.swing.*;

public class PrimeTool {
    private static final MySortedIntegerCollection PRIMES;
    private static int _highestNumberChecked;

    static {
        PRIMES = new MySortedIntegerCollection();
        initializePrimes(100);
        _highestNumberChecked = 100;
    }


    private static void initializePrimes(int amount) {
        MySortedIntegerCollection numbers = new MySortedIntegerCollection();
        for (int i = 0; i < amount; i++) {
            numbers.add(i);
        }
        for (int k = 2; k < Math.sqrt(amount); k++) {
            for (int removeIndex = k * 2; removeIndex < amount; removeIndex += k) {
                numbers.set(removeIndex, 0);
            }

        }
        numbers.removeFromBehind(0);
        PRIMES.add(numbers);
    }

    public Integer[] getPrimes(int lowerBound, int upperBound) {
        if (lowerBound > upperBound) {
            throw new IllegalArgumentException("lowerBound must be greater than upperBound");
        }
        if (lowerBound < 0) {
            throw new IllegalArgumentException("lowerBound must be greater than zero");
        }
        if (upperBound <= _highestNumberChecked) {
            int lower = PRIMES.getIndexWhenNumberLarger(lowerBound);
            int upper = PRIMES.getIndexWhenNumberLarger(upperBound);
            return PRIMES.subList(lower, upper).toArray(Integer.class);
        }
        expandPrimes(upperBound);
        return getPrimes(lowerBound, upperBound);
    }

    public Tuple<Integer[], Long> getPrimesWithMilliSecondsSpent(int lowerBound, int upperBound) {
        long startTime = System.nanoTime();
        Integer[] primes = getPrimes(lowerBound, upperBound);
        long endTime = System.nanoTime();

        long duration = (endTime - startTime) / 1000000;
        return new Tuple<>(primes, duration);
    }

    private void expandPrimes(int newMax) {
        PRIMES.increaseSize(newMax - _highestNumberChecked);
        int largestNumberToCheck =  Double.valueOf(Math.sqrt(newMax)).intValue();
        int largestIndexToCheck = PRIMES.getIndexWhenNumberLarger(largestNumberToCheck);
        boolean mustExpandPrimesToCheck = largestIndexToCheck == PRIMES.size() - 1;
        for (int i = _highestNumberChecked + 1; i <= newMax; i++) {
            boolean isPrime = true;
            for (int check = 1; check <= largestIndexToCheck; check++) {
                if (i % PRIMES.get(check) == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                PRIMES.add(i);
                mustExpandPrimesToCheck = i >= largestNumberToCheck;
                if (mustExpandPrimesToCheck) {
                    largestIndexToCheck++;
                }
            }
            _highestNumberChecked++;
        }
    }
}
