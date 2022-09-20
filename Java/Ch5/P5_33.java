package Ch5;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class P5_33 {
    static boolean isLeap(int n) {
        return n % 4 == 0 && (n % 400 == 0 || n % 100 != 0);
    }

    public static void main(String[] args) {
        for (int i = 6; i < 10_001; i++) {
            int sum = 0;
            for (int j = 1; j <= i >> 1; j++) {
                if (i % j == 0) sum += j;
            }
            if (sum == i) out.print(i + " ");
        }
    }
}
