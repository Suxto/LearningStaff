package Ch6;

import static java.lang.System.out;

public class P6_16 {
    public static int numberOfDaysInAYear(int n) {
        if (n % 4 == 0 && (n % 100 != 0 || n % 400 == 0)) return 366;
        else return 365;
    }

    public static void main(String[] args) {
        int ans = 0;
        for (int i = 2000; i <= 2020; i++) {
            ans += numberOfDaysInAYear(i);
        }
        out.print(ans);
    }
}
