import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class PrimeCheckerTest {

    @Test
    public void testIsPrime() {
        assertTrue(PrimeChecker.isPrime(17));
        assertTrue(PrimeChecker.isPrime(2));
        assertTrue(PrimeChecker.isPrime(3));
        assertTrue(PrimeChecker.isPrime(233));
    }

    @Test
    public void testIsNotPrime() {
        assertFalse(PrimeChecker.isPrime(0));
        assertFalse(PrimeChecker.isPrime(4));
        assertFalse(PrimeChecker.isPrime(10));
        assertFalse(PrimeChecker.isPrime(81));
    }

    @Parameterized.Parameters
    public static Collection<?> data() {
        return Arrays.asList(new Object[][]{{1, false}, {2, true}, {4, false}, {2, true}});
    }

    int input;
    boolean output;

    public PrimeCheckerTest(int x, boolean y) {
        input = x;
        output = y;
    }

    @Test
    public void testWithParameters() {
        assertEquals(output, PrimeChecker.isPrime(input));
    }

}
