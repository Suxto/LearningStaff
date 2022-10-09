package Ch8;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class P8_2 {
    public static double sumMajorDiagonal(double[][] m) {
        double ans = 0;
        for (int i = 0; i < m.length; i++) {
            ans += m[i][i];
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(in);
        out.println("Enter a 4-by-4 matrix row by row: ");
        double[][] arr = new double[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                arr[i][j] = scanner.nextDouble();
            }
        }
        out.print("Sum of the elements in the major diagonal is " + sumMajorDiagonal(arr));
    }
}
