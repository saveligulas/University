package blatt2;

import org.apache.commons.math3.primes.Primes;
import org.example.ub2.PrimeToolV2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PrimeToolV2Test {
    private static PrimeToolV2 tool = new PrimeToolV2();

    @Test
    public void testPrimes() {
        for (Integer i : tool.getPrimes(100, 1000000)) {
            assertTrue(Primes.isPrime(i));
        }
    }
}
