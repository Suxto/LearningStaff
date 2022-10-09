package Ch8;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class P8_25 {
    public static Scanner scanner = null;

    public static void input(double[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                mat[i][j] = scanner.nextDouble();
            }
        }
    }

    public static void output(double[][] a) {
        for (double[] doubles : a) {
            for (double aDouble : doubles) {
                out.printf("%f ", aDouble);
            }
            out.print("\n");
        }
    }

    public static boolean isMarkovMatrix(double[][] m) {
        for (int i = 0; i < m[0].length; i++) {
            double sum = 0;
            for (double[] doubles : m) {
                sum += doubles[i];
                if (doubles[i] <= 0) return false;
            }
            if (sum != 1) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        scanner = new Scanner(in);
        out.print("Enter a 3-by-3 matrix row by row:\n");
        double[][] a = new double[3][3];
        input(a);
        out.print("It is " + (isMarkovMatrix(a) ? "" : "not ") + "a Markov matrix");
    }
}
/*
0.15 0.875 0.375
0.55 0.005 0.225
0.30 0.12 0.4


0.95 -0.875 0.375
0.65 0.005 0.225
0.30 0.22 -0.4
 */