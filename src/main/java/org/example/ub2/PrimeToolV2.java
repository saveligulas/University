package org.example.ub2;

import org.example.coll.MyCollection;
import org.example.coll.MySortedIntegerCollection;

import java.util.Arrays;

public class PrimeToolV2 {
    private static final MySortedIntegerCollection PRIMES;
    private static int _highestNumberChecked;

    static {
        PRIMES = new MySortedIntegerCollection();
        PRIMES.add(1);
        PRIMES.add(2);
        _highestNumberChecked = 2;
    }

    /**
     *
     * @param lowerBound inclusive
     * @param upperBound inclusive
     * @return Returns all primes between lowerBound and upperBound
     */
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
        // Size of the array to not start from 0 every time
        int offset = newMax - _highestNumberChecked;
        boolean[] numList = new boolean[offset];
        Arrays.fill(numList, true);

        // Highest number to check for Sieve of Eratosthenes
        int maxNumberToCheck = (int) Math.sqrt(newMax);
        // Current number we checked in the while loop
        // has to exist because maxNumberToCheck could be greater than the highest prime we found
        int currentNumberChecked = 0;
        // Keeps track of the Prime index we are at
        int nextPrimeIndexToCheck = 1;
        // Keeps track of where we are in the numList array
        int lastIndexAddedToNumList = 0;

        // while loop goes on until our Primes contain a number greater than maxNumberToCheck
        while (currentNumberChecked < maxNumberToCheck) {
            // gives us the last index if maxNumberToCheck is greater than our highest prime
            int largestIndexToCheck = PRIMES.getIndexWhenNumberLarger(maxNumberToCheck);
            // sets currentNumberChecked to the highest prime we could find
            currentNumberChecked = PRIMES.get(largestIndexToCheck);
            // Starts at i = 1 so we get 2
            for (int i = nextPrimeIndexToCheck; i <= largestIndexToCheck; i++) {
                int prime = PRIMES.get(i);
                // Finds the multiplier so prime * x is greater than our starting point
                int startPoint = prime * getMultiplierForGreaterThan(prime, _highestNumberChecked);
                // Now it goes through every multiple of the prime in this range and sets them to false in our numList
                for (int j = startPoint; j <= newMax; j += prime) {
                    // Index can not be set directly because our numList starts at the _highestNumberChecked
                    int indexNotPrime = j - _highestNumberChecked - 1;
                    try {
                        numList[indexNotPrime] = false;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        // Debugging
                        System.out.println("AIOOBE -" + prime + "-" + startPoint + "-" + _highestNumberChecked);
                    }
                }
            }

            // Still in while loop we set nextPrimeIndexToCheck to the next primes, which will be added
            // There will always be more primes to add if the loop has to run more than once
            // no danger of ArrayIndexOutOfBoundsException
            nextPrimeIndexToCheck = largestIndexToCheck + 1;
            // we have to use this as the currentNumberChecked could be larger than numList.length
            // and for efficient looping to not add Primes which could not have been checked
            int highestGuaranteedIndex = currentNumberChecked * currentNumberChecked;
            // loops through the entire list or just the currentNumberChecked squared
            for (int i = lastIndexAddedToNumList; i < (Math.min(highestGuaranteedIndex, numList.length)); i++) {
                if (numList[i]) {
                    PRIMES.add(i + _highestNumberChecked + 1);
                }
                // increment to not add primes more than once
                lastIndexAddedToNumList++;
            }
        }
        // update _highestNumberChecked to search efficiently in the next loops
        _highestNumberChecked = newMax;
    }

    /**
     *
     * @param a number to be multiplied.
     * @param b number to reach.
     * @return returns the first x so that x * a > b. Returns 2 if a == b.
     */
    private int getMultiplierForGreaterThan(int a, int b) {
        if (a == b) {
            return 2;
        } else {
            b++;
            return ((int) Math.ceil((double) b / a));
        }
    }

    /**
     * @param upperBound the upper bound of the numbers to be searched for primes.
     * @return the time it takes to expand the primes to the given upper bound. Will return 0 if the upper bound is less than or equal to the highest prime we found.
     */
    public Long expandPrimesTime(int upperBound) {
        if (upperBound <= _highestNumberChecked) {
            return 0L;
        }
        long startTime = System.nanoTime();
        expandPrimes(upperBound);
        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000;
    }

    /**
     * @param a lower bound of the numbers to be searched for primes.
     * @param b upper bound of the numbers to be searched for primes.
     * @return a collection of all primes between a and b. Uses the Sieve of Eratosthenes algorithm without memorization of previously found primes.
     */
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

    /**
     * @param a lower bound of the numbers to be searched for primes.
     * @param b upper bound of the numbers to be searched for primes.
     * @return the time it takes to find all primes between a and b. Uses the Sieve of Eratosthenes algorithm without memorization of previously found primes.
     */
    public Long primesInRangeTime(int a, int b) {
        long startTime = System.nanoTime();
        primesInRange(a, b);
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000;
    }
}
