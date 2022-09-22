package Ch6;

import java.util.*;

import static java.lang.System.*;

class P6_31 {
    public static boolean isValid(long num) {
        return (sumOfDoubleEvenPlace(num) + sumOfOddPlace(num)) % 10 == 0;
    }

    public static int sumOfDoubleEvenPlace(long num) {
        int ans = 0, i = 1;
        while (num > 0) {
            if ((i & 1) == 0)
                ans += getDigit(2 * (int) (num % 10));
            num /= 10;
            i++;
        }
        return ans;
    }

    public static int getDigit(int num) {
        if (num < 10)
            return num;
        return num / 10 + num % 10;
    }

    public static int sumOfOddPlace(long num) {
        int i = 1, cnt = 0;
        while (num > 0) {
            if ((i & 1) != 0) {
                cnt += num % 10;
            }
            num /= 10;
            i++;
        }
        return cnt;
    }

    public static boolean prefixMatched(long num, int d) {
        return getPrefix(num, getSize(d)) == d;
    }

    public static int getSize(long num) {
        int cnt = 0;
        while (num > 0) {
            num /= 10;
            cnt++;
        }
        return cnt;
    }

    public static long getPrefix(long num, int k) {
        return num / (int) Math.pow(10, getSize(num) - k);
    }

    public static void main(String[] args) {
        out.print("Enter a credit card number as a long integer: ");
        long num = new Scanner(in).nextLong();
        out.print(num + " is ");
//        out.println(sumOfDoubleEvenPlace(num));
//        out.println(sumOfOddPlace(num));

        if (isValid(num))
            out.print("valid");
        else
            out.print("invalid");
    }
}