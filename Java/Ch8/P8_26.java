package Ch8;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class P8_26 {
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
                out.printf("%.3f ", aDouble);
            }
            out.print("\n");
        }
    }

    public static double[][] sortRows(double[][] m) {
        double[][] nw = m.clone();
        for (double[] doubles : nw) {
            Arrays.sort(doubles);
        }
        return nw;
    }

    public static void main(String[] args) {
        scanner = new Scanner(in);
        out.print("Enter a 3-by-3 matrix row by row:\n");
        double[][] a = new double[3][3];
        input(a);
        out.print("\nThe row-sorted array is\n");
        output(sortRows(a));
    }
}
/*
0.15 0.875 0.375
0.55 0.005 0.225
0.30 0.12 0.4
 */