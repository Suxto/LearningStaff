import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ArrayMinMaxFinderTest {
    @Test
    public void testFindMaxInteger() {
        List<Integer> integers = Arrays.asList(3, 7, 1, 9, 4, 6, 2, 8, 5);
        int max = ArrayMinMaxFinder.findMax(integers);
        assertEquals(9, max);
    }

    @Test
    public void testFindMaxDouble() {
        List<Double> doubles = Arrays.asList(3.5, 7.2, 1.8, 9.1, 4.6, 6.0, 2.3, 8.9, 5.4);
        double max = ArrayMinMaxFinder.findMax(doubles);
        assertEquals(9.1, max, 0.0001);
    }

    @Test
    public void testFindMaxWithNegativeNumbers() {
        List<Integer> negativeNumbers = Arrays.asList(-3, -7, -1, -9, -4, -6, -2, -8, -5);
        int max = ArrayMinMaxFinder.findMax(negativeNumbers);
        assertEquals(-1, max);
    }

    @Test
    public void testFindMaxLong() {
        List<Long> longs = Arrays.asList(100L, 50L, 200L, 75L, 150L);
        long max = ArrayMinMaxFinder.findMax(longs);
        assertEquals(200L, max);
    }

    @Test
    public void testFindMaxFloat() {
        List<Float> floats = Arrays.asList(3.5f, 7.2f, 1.8f, 9.1f, 4.6f, 6.0f, 2.3f, 8.9f, 5.4f);
        float max = ArrayMinMaxFinder.findMax(floats);
        assertEquals(9.1f, max, 0.0001);
    }

    @Test
    public void testFindMinInteger() {
        List<Integer> integers = Arrays.asList(3, 7, 1, 9, 4, 6, 2, 8, 5);
        int min = ArrayMinMaxFinder.findMin(integers);
        assertEquals(1, min);
    }

    @Test
    public void testFindMinDouble() {
        List<Double> doubles = Arrays.asList(3.5, 7.2, 1.8, 9.1, 4.6, 6.0, 2.3, 8.9, 5.4);
        double min = ArrayMinMaxFinder.findMin(doubles);
        assertEquals(1.8, min, 0.0001);
    }

    @Test
    public void testFindMinWithNegativeNumbers() {
        List<Integer> negativeNumbers = Arrays.asList(-3, -7, -1, -9, -4, -6, -2, -8, -5);
        int min = ArrayMinMaxFinder.findMin(negativeNumbers);
        assertEquals(-9, min);
    }

    @Test
    public void testFindMinLong() {
        List<Long> longs = Arrays.asList(100L, 50L, 200L, 75L, 150L);
        long min = ArrayMinMaxFinder.findMin(longs);
        assertEquals(50L, min);
    }

    @Test
    public void testFindMinFloat() {
        List<Float> floats = Arrays.asList(3.5f, 7.2f, 1.8f, 9.1f, 4.6f, 6.0f, 2.3f, 8.9f, 5.4f);
        float min = ArrayMinMaxFinder.findMin(floats);
        assertEquals(1.8f, min, 0.0001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindMaxWithEmptyArray() {
        Integer[] array = {};
        ArrayMinMaxFinder.findMax(Arrays.asList(array));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindMaxWithNullArray() {
        ArrayMinMaxFinder.findMax(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindMinWithNullArray() {
        ArrayMinMaxFinder.findMin(null);
    }

}

