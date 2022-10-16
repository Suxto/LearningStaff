package Ch10;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class P10_5 {
    final static int MAXN = 100000;
    static int[] prime = new int[MAXN];
    static int cnt = 0;
    static boolean[] st = new boolean[MAXN];

    static void getPrimes() {
        for (int i = 2; i < MAXN; i++) {
            if (!st[i]) prime[cnt++] = i;
            for (int j = 0; j < cnt && prime[j] * i < MAXN; j++) {
                st[i * prime[j]] = true;
                if (i % prime[j] == 0) break;
            }
        }
    }

    public static void main(String[] args) {
        getPrimes();
        StackOfIntegers stackOfIntegers = new StackOfIntegers(30);
        out.print("Enter a positive integer: ");
        int x = new Scanner(in).nextInt();
        for (int i = 0; i < cnt; i++) {
            if (prime[i] > x) break;
            while (x % prime[i] == 0) {
                stackOfIntegers.push(prime[i]);
                x /= prime[i];
            }
        }
        out.print(stackOfIntegers);
    }
}

class StackOfIntegers {
    int pos = 0;
    int[] arr;

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        while (this.pos-- != 0) {
            stringBuilder.append(this.arr[pos]).append(' ');
        }
        return stringBuilder.toString();
    }

    void push(int x) {
        arr[pos++] = x;
    }

    int pop() {
        return arr[pos--];
    }

    StackOfIntegers(int x) {
        arr = new int[x];
    }

    StackOfIntegers() {
        arr = new int[100];
    }
}
