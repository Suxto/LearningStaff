package Ch8;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class P8_23 {
    public static Scanner scanner = null;
    static int[][] sum = new int[2][6];//0 for row


    public static void input(int[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                mat[i][j] = scanner.nextInt();
                sum[0][i] += mat[i][j];
                sum[1][j] += mat[i][j];
            }
        }
    }

    public static void output(int[][] a) {
        for (int[] doubles : a) {
            for (int aDouble : doubles) {
                out.printf("%d ", aDouble);
            }
            out.print("\n");
        }
    }

    public static int check(int x) {
        for (int i = 0; i < sum[x].length; i++) {
            if ((sum[x][i] & 1) == 1) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        scanner = new Scanner(in);
        out.print("Enter a 6-by-6 matrix row by row:\n");
        int[][] a = new int[6][6];
        input(a);
        out.printf("The flipped cell is at (%d, %d)", check(0), check(1));
    }
}
/*
1 1 1 0 1 1
1 1 1 1 0 0
0 1 0 1 1 1
1 1 1 1 1 1
0 1 1 1 1 0
1 0 0 0 0 1
 */