package blatt2;

import org.apache.commons.math3.primes.Primes;
import org.example.ub2.PrimeTool;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PrimeToolTest {
    private static PrimeTool _primeTool;

    @BeforeAll
    public static void beforeAll() {
        _primeTool = new PrimeTool();
    }

    @Test
    public void testSetup() {
        for (Integer i : _primeTool.getPrimes(2, 100)) {
            System.out.println(i);
            assertTrue(Primes.isPrime(i));
        }
    }

    @Test
    public void testExpandPrimes() {
        for (Integer i : _primeTool.getPrimes(100, 100000)) {
            System.out.println(i);
            assertTrue(Primes.isPrime(i));
        }
    }
}
