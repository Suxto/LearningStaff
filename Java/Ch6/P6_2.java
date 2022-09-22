package Ch6;

import java.util.Scanner;

import static java.lang.System.*;

public class P6_2 {
    public static int sumDigits(long n) {
        int ans = 0;
        while (n > 0) {
            ans += n % 10;
            n /= 10;
        }
        return ans;
    }

    public static void main(String[] args) {
        out.print("Enter a number: ");
        long n = new Scanner(in).nextLong();
        out.print("The sum of all digit in the number is " + sumDigits(n));
    }
}
