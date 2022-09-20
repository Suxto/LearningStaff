package Ch5;

import static java.lang.System.out;

public class P5_20 {
    static final int MAXN = 10000000;
    static int[] prime = new int[MAXN];
    static int cnt = 1;
    static boolean[] st = new boolean[MAXN];

    static void getPrimes() {
        for (int i = 2; i < MAXN; i++) {
            if (!st[i]) prime[cnt++] = i;
            for (int j = 1; j < cnt && prime[j] * i < MAXN; j++) {
                st[i * prime[j]] = true;
                if (i % prime[j] == 0) break;
            }
        }
    }

    public static void main(String[] args) {
        final int end = 1000;
        getPrimes();
//        out.print(prime[0] + "\t");
        for (int i = 1; i <= MAXN; i++) {
            if (prime[i] > end) break;
            out.print(prime[i] + "\t");
            if (i % 8 == 0) out.print("\n");
        }
    }
}
