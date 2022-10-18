package Etc;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

class Main {
    static Scanner scanner = new Scanner(in);

    public static boolean go() {
        return false;
    }

    public static void main(String[] argv) {
        int[][] x = {{1, 2}, {3, 4, 5}, {5, 6, 5, 9}};
        for(int[] ints:x) out.println(ints.length);
    }

    public static int ttt(int[][] m) {
        int v = m[0][0];

        for (int i = 0; i < m.length; i++)
            for (int j = 0; j < m[i].length; j++)
                if (v < m[i][j]) v = m[i][j];

        return v;
    }
}
