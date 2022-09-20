package Ch5;

import static java.lang.System.out;

public class P5_12 {
    public static void main(String[] args) {
        int n = 10;
        while (n * n < 12_000) {
            n++;
        }
        out.print(n);
    }
}
