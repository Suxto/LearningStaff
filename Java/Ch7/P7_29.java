package Ch7;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class P7_29 {
    final static int TIME = 4;

    public static int go() {
        int sum = 0;
        for (int i = 0; i < TIME; i++) {
            int rand = (int) (Math.random() * 13) + 1;
            sum += rand;
        }
        return sum;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(in);
        int now = 0, cnt = 0;
        do {
            now = go();
            cnt++;
        } while (now != 24);
        out.print("It takes " + cnt + " times");
    }
}
