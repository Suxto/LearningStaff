package Ch9;

import java.util.Objects;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class P9_13 {
    static Scanner scanner = null;

    public static void main(String[] args) {
        scanner = new Scanner(in);
        out.print("Enter the number of rows and columns in the array: ");
        double[][] arr = new double[scanner.nextInt()][scanner.nextInt()];
        input(arr);
        Location location = locationLargest(arr);
        out.printf("The location of the largest element is %f at (%d, %d)", location.maxValue, location.row, location.column);
    }

    public static Location locationLargest(double[][] a) {
        Location location = new Location();
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                if (location.maxValue < a[i][j]) {
                    location.column = j;
                    location.row = i;
                    location.maxValue = a[i][j];
                }
            }
        }
        return location;
    }

    public static void input(double[][] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                a[i][j] = scanner.nextDouble();
            }
        }
    }
}

class Location {
    public int row, column;
    public double maxValue = Double.MIN_VALUE;
}