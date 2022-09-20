package Ch5;

import java.util.Scanner;

import static java.lang.System.out;

public class P5_16 {
    public static boolean isPrime(int x) {
        for (int i = 2; i * 2 <= x; i++) {
            if (x % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int n = new Scanner(System.in).nextInt();
        int org = n;
        for (int i = 2; i * 2 <= org; i++) {
            if (isPrime(i)) {
                while (n % i == 0) {
                    n /= i;
//                    out.print(i + " " + n + "\n");
                    out.print(i + " ");
                }
                if (n == 1) break;
            }
        }
    }
}
