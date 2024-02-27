import java.util.Collection;

public class ArrayMinMaxFinder {

    public static <T extends Number & Comparable<T>> T findMax(Collection<T> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            throw new IllegalArgumentException("Input collection must not be null or empty");
        }

        T max = numbers.iterator().next();
        for (T number : numbers) {
            if (number.compareTo(max) > 0) {
                max = number;
            }
        }
        return max;
    }


    public static <T extends Number & Comparable<T>> T findMin(Collection<T> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            throw new IllegalArgumentException("Input collection must not be null or empty");
        }

        T min = numbers.iterator().next();
        for (T number : numbers) {
            if (number.compareTo(min) < 0) {
                min = number;
            }
        }
        return min;
    }
}
