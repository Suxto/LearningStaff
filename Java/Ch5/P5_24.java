package Ch5;

import java.util.Scanner;

import static java.lang.Math.pow;
import static java.lang.System.in;
import static java.lang.System.out;

public class P5_24 {

    public static void main(String[] args) {
        double sum = 0;
        for (int i = 1; i < 100; i += 2) {
            sum += i / (i + 2.0);
        }
        out.print(sum);
    }
}
