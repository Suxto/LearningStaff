package Ch8;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class P8_35 {
    public static Scanner scanner = null;
    static int[] vis = new int[10000000];

    public static void input(int[][] mat) {
        for (int i = 1; i < mat.length; i++) {
            for (int j = 1; j < mat[i].length; j++) {
                mat[i][j] = scanner.nextInt();
            }
        }
    }

    public static int[] findLargestBlock(int[][] m) {
        int[] arr = new int[3];

        int[][] pre = m.clone();
        for (int i = 1; i < m.length; i++) {
            for (int j = 1; j < m[i].length; j++) {
                pre[i][j] += pre[i][j - 1];
            }
        }

        for (int i = 1; i < m.length; i++) {
            int r = 1;
            for (int l = 1; l < m[i].length; l++) {
                if (m[i][l] == 0) continue;
                while (r + 1 < m[i].length && pre[i][r + 1] - pre[i][l - 1] == r + 2 - l)
                    r++;
                int len = r - l + 1;
                if (i + len - 1 >= m.length) continue;
                boolean flag = true;
                for (int j = 1; j < len; j++) {
                    if (pre[j + i][r] - pre[j + i][l - 1] != r - l + 1) {
                        flag = false;
                        break;
                    }
                }
                if (flag && arr[2] < len) {
                    arr[0] = i - 1;
                    arr[1] = l - 1;
                    arr[2] = len;
                }
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        scanner = new Scanner(in);
        out.print("Enter the number of rows in the matrix: ");
        int x = scanner.nextInt();
        int[][] a = new int[x + 1][x + 1];
        out.print("Enter the matrix row by row: \n");
        input(a);
        int[] arr = findLargestBlock(a);
        out.printf("\nThe maximum square submatrix is at (%d, %d) with size %d", arr[0], arr[1], arr[2]);
    }
}
/*
1 0 1 0 1
1 1 1 0 1
1 0 1 1 1
1 0 1 1 1
1 0 1 1 1

1 0 1 0 1
1 1 1 1 0
1 1 1 1 0
1 1 1 1 0
0 1 1 1 1
 */