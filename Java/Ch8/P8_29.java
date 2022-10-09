package Ch8;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class P8_29 {
    public static Scanner scanner = null;
    static int[] vis = new int[10000000];

    public static void input(int[][] mat, int n) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                mat[i][j] = scanner.nextInt();
                if (n == 0) vis[mat[i][j]]++;
                else vis[mat[i][j]]--;
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

    public static boolean equals(int[][] m1, int[][] m2) {
        for (int i : vis) {
            if (i != 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        scanner = new Scanner(in);
        int[][] a = new int[3][3];
        int[][] b = new int[3][3];
        out.print("Enter list1: ");
        input(a, 0);
        out.print("Enter list2: ");
        input(b, 1);
        out.print("The two arrays are " + (equals(a, b) ? "" : "not ") + "identical");
    }
}
/*
51 25 22 6 1 4 24 54 6
51 22 25 6 1 4 24 54 6

51 5 22 6 1 4 24 54 6
51 22 25 6 1 4 24 54 6
 */