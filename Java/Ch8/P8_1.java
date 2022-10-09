package Ch8;

import java.util.Scanner;

import static java.lang.System.*;

public class P8_1 {
    public static double sumColumn(double[][] m, int columnIndex) {
        double ans = 0;
        for (double[] doubles : m) {
            ans += doubles[columnIndex];
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(in);
        out.println("Enter a 3-by-4 matrix row by row: ");
        double[][] arr = new double[3][4];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                arr[i][j] = scanner.nextDouble();
            }
        }
        for (int i = 0; i < 4; i++) {
            out.printf("Sum of the elements at column %d is %.1f\n", i, sumColumn(arr, i));
        }
    }
}
