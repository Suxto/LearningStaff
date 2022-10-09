package Ch8;

import java.util.Scanner;

import static java.lang.System.out;

public class P8_22 {
    public static Scanner scanner = null;
    static int[][] sum = new int[2][6];//0 for row

    public static void rand(int[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                mat[i][j] = (int) (Math.random() + 0.5);
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

    public static boolean check(int x) {
        for (int i = 0; i < sum[x].length; i++) {
            if ((sum[x][i] & 1) == 1) return false;
        }
        return true;
    }

    public static void main(String[] args) {
//        scanner = new Scanner(in);
        int[][] a = new int[6][6];
        rand(a);
        output(a);
        out.println("The number of 1 in each row is " + (check(0) ? "even" : "odd"));
        out.print("The number of 1 in each column is " + (check(1) ? "even" : "odd"));
    }
}
