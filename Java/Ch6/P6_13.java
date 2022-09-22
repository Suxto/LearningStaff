package Ch6;

import static java.lang.System.out;

public class P6_13 {
    public static double calc(int n) {
        double ans = 0;
        for (int i = 1; i <= n; i++) {
            ans += i / (i + 1.0);
        }
        return ans;
    }

    public static void main(String[] args) {
        out.print("i \t\tm(i)\n");
        out.print("--------------------\n");
        for (int i = 1; i < 21; i++) {
            out.printf("%d \t\t%.4f\n", i, calc(i));
        }
    }
}
