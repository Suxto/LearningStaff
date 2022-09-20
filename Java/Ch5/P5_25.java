package Ch5;

import static java.lang.System.out;

public class P5_25 {
    static void calc(int n) {
        out.print("When i= " + n + " PI= ");
        double sum = 0, sign = -1;
        for (int i = 3; i < n; i += 2) {
            sum += 1 * sign / i;
            sign *= -1.0;
        }
        out.println(4 * (1 + sum));
    }

    public static void main(String[] args) {
        for (int i = 1; i < 11; i++)
            calc(i * 10000);
    }
}
