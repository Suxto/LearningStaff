package Ch8;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class P8_11 {
    public static Scanner scanner = null;
    static int[][] sum = new int[2][4];//0 for row

    public static void go(int[][] mat, int x) {
        x <<= 1;
        for (int i = mat.length - 1; i > -1; i--) {
            for (int j = mat[i].length - 1; j > -1; j--) {
                mat[i][j] = ((x >>= 1) & 1) == 1 ? 'T' : 'H';
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


    public static void main(String[] args) {
        scanner = new Scanner(in);
        int[][] a = new int[3][3];
        out.print("Enter a number between 0 and 511: ");
        go(a, scanner.nextInt());
        output(a);
    }
}
