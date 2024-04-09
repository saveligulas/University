package org.example.ub2;

import org.example.ub1.my.MyCollection;
import org.example.ub1.my.MySortedIntegerCollection;
import org.example.ub1.tuple.Tuple;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PrimeToolV2 {
    private static final MySortedIntegerCollection PRIMES;
    private static int _highestNumberChecked;

    static {
        PRIMES = new MySortedIntegerCollection();
        PRIMES.add(1);
        PRIMES.add(2);
        PRIMES.add(3);
        PRIMES.add(5);
        PRIMES.add(7);
        PRIMES.add(11);
        _highestNumberChecked = 11;
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
        boolean[] numList = new boolean[offset];
        Arrays.fill(numList, true);
        int largestNumberToCheck = (int) Math.sqrt(newMax);
        int largestNumberChecked = 0;
        int nextPrimeIndexToCheck = 1;
        int highestIndexAdded = 0;
        while (largestNumberChecked <= largestNumberToCheck) {
            int largestIndexToCheck = PRIMES.getIndexWhenNumberLarger(largestNumberToCheck);
            largestNumberChecked = PRIMES.get(largestIndexToCheck);
            for (int i = nextPrimeIndexToCheck; i <= largestIndexToCheck; i++) {
                int prime = PRIMES.get(i);
                int startPoint = prime * getMultiplierForGreaterThan(prime, _highestNumberChecked);
                for (int j = startPoint; j <= newMax; j += prime) {
                    int indexNotPrime = j - _highestNumberChecked - 1;
                    try {
                        numList[indexNotPrime] = false;

                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("AIOOBE -" + prime + "-" + startPoint + "-" + _highestNumberChecked);
                    }

                }
            }
            nextPrimeIndexToCheck = largestIndexToCheck + 1;
            int highestGuaranteedIndex = largestNumberChecked * largestNumberChecked;
            for (int i = highestIndexAdded; i < (Math.min(highestGuaranteedIndex, numList.length)); i++) {
                if (numList[i]) {
                    PRIMES.add(i + _highestNumberChecked + 1);
                }
                highestIndexAdded++;
            }
        }
        _highestNumberChecked = newMax;
    }

    private int getMultiplierForGreaterThan(int a, int b) {
        if (a == b) {
            return 2;
        } else {
            b++;
            return ((int) Math.ceil((double) b / a));
        }
    }


    public Long expandPrimesTime(int upperBound) {
        if (upperBound <= _highestNumberChecked) {
            return 0L;
        }
        long startTime = System.nanoTime();
        expandPrimes(upperBound);
        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000;
    }

    public static MyCollection<Integer> primesInRange(int a, int b) {
        boolean[] prime = new boolean[b + 1];
        for (int i = 0; i <= b; i++)
            prime[i] = true;

        for (int p = 2; p * p <= b; p++) {
            if (prime[p]) {
                for (int i = p * p; i <= b; i += p)
                    prime[i] = false;
            }
        }

        MyCollection<Integer> primes = new MyCollection<>();
        for (int i = Math.max(2, a); i <= b; i++) {
            if (prime[i]) {
                primes.add(i);
            }
        }
        return primes;
    }
}
