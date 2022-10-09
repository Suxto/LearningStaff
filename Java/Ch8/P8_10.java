package Ch8;

import java.util.Scanner;

import static java.lang.System.out;

public class P8_10 {
    public static Scanner scanner = null;
    static int[][] sum = new int[2][4];//0 for row

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

    public static int max(int x) {
        int pos = 0;
        for (int i = 1; i < sum[x].length; i++) {
            if (sum[x][i] > sum[x][pos]) pos = i;
        }
        return pos;
    }

    public static void main(String[] args) {
//        scanner = new Scanner(in);
        int[][] a = new int[4][4];
        rand(a);
        output(a);
        out.println("The largest row index: " + max(0));
        out.print("The largest column index: " + max(1));
    }
}
