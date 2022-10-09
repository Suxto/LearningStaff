package Ch8;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class P8_16 {
    public static Scanner scanner = null;
    static int[][] arr = null;

    public static void sort(int[][] mat, int st, int ed, int k) {
        if (k >= mat[0].length) return;
        for (int i = st; i <= ed; i++) {
            for (int j = i + 1; j <= ed; j++) {
                if (mat[i][k] > mat[j][k]) {
                    int[] temp = mat[i];
                    mat[i] = mat[j];
                    mat[j] = temp;
                }
            }
        }
        int now = mat[st][k];
        for (int i = st + 1; i <= ed; i++) {
            if (mat[i][k] != now) {
                sort(mat, st, i - 1, k + 1);
                now = mat[i][k];
                st = i;
            }
        }
        sort(mat, st, ed, k + 1);
    }

    public static void output(int[][] a) {
        for (int[] doubles : a) {
            for (int aDouble : doubles) {
                out.printf("%d ", aDouble);
            }
            out.print("\n");
        }
    }

    public static void input(int[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
//                mat[i][j] = scanner.nextDouble();
                mat[i][j] = scanner.nextInt();
            }
        }
    }

    public static void main(String[] args) {
        scanner = new Scanner(in);
        out.print("Enter a number of rows and columns of the array: ");
        arr = new int[scanner.nextInt()][scanner.nextInt()];
        out.print("Enter the array: \n");
        input(arr);
        sort(arr, 0, arr.length - 1, 0);
        out.print("The matrix sorted: \n");
        output(arr);
    }
}
