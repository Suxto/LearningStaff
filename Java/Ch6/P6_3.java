package Ch6;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class P6_3 {
    public static int reverse(int n) {
        int ans = 0;
        while (n > 0) {
            ans = ans * 10 + n % 10;
            n /= 10;
        }
        return ans;
    }


    public static boolean isPalindrome(int n) {
        return n == reverse(n);
    }

    public static void main(String[] args) {
        out.print("Enter a number: ");
        int n = new Scanner(in).nextInt();
        if (isPalindrome(n)) out.print("This number is a palindrome");
        else out.print("This number is not a palindrome");
    }
}
