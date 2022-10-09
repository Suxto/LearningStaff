package Ch8;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class P8_13 {
    public static Scanner scanner = null;
    static double[][] arr = null;//0 for row
    static int r = 0, c = 0;

    public static void max(double[][] mat) {
        for (int i = mat.length - 1; i > -1; i--) {
            for (int j = mat[i].length - 1; j > -1; j--) {
                if (mat[i][j] > mat[r][c]) {
                    c = i;
                    r = j;
                }
            }
        }
    }

    public static void output(int[][] a) {
        for (int[] doubles : a) {
            for (int aDouble : doubles) {
                out.printf("%c ", aDouble);
            }
            out.print("\n");
        }
    }

    public static void input(double[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                mat[i][j] = scanner.nextDouble();
            }
        }
    }

    public static void main(String[] args) {
        scanner = new Scanner(in);
        out.print("Enter a number of rows and columns of the array: ");
        arr = new double[scanner.nextInt()][scanner.nextInt()];
        out.print("Enter the array: \n");
        input(arr);
        max(arr);
        out.printf("The location of the largest element is at at (%d, %d)", r, c);
    }
}
