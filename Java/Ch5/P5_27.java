package Ch5;

import static java.lang.System.out;

public class P5_27 {
    static boolean isLeap(int n) {
        return n % 4 == 0 && (n % 400 == 0 || n % 100 != 0);
    }

    public static void main(String[] args) {
        int cnt = 0;
        for (int i = 101; i < 2100; i++)
            if (isLeap(i)) {
                out.print(i + "\t");
                cnt++;
                if (cnt % 10 == 0) out.print("\n");
            }
        out.print("\nThe total is " + cnt);
    }
}
