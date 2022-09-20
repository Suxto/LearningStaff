package Ch5;

import java.util.Scanner;

import static java.lang.System.out;

public class P5_19 {
    public static void main(String[] args) {
        final int lay = 8;
        for (int i = 1; i <= lay; i++) {
            for (int j = 0; j < lay - i; j++) {
                out.print("\t");
            }
            int now = 1;
            for (int j = 0; j < i; j++) {
                out.print(now + "\t");
                now <<= 1;
            }
            now >>= 2;
            while (now > 0) {
                out.print(now + "\t");
                now >>= 1;
            }
            out.print("\n");
        }
    }
}
