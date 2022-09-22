package Ch6;

import static java.lang.System.out;

public class P6_14 {
    public static double calc(int n) {
        double ans = 0;
        double sign = -1;
        for (int i = 3; i < 2 * n; i += 2) {
            ans += sign / i;
            sign *= -1;
        }
        return (1 + ans) * 4.0;
    }

    public static void main(String[] args) {
        out.print("i \t\t\tm(i)\n");
        out.print("--------------------\n");
        for (int i = 0; i < 10; i++) {
            int now = 100 * i + 1;
            out.printf("%d   \t\t%.4f\n", now, calc(now));
        }
    }
}
