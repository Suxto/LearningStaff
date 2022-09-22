package Ch6;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class P6_9 {
    public static double footToMeter(double n) {
        return n * 0.305;
    }

    public static double meterToFoot(double n) {
        return 3.279 * n;
    }


    public static void main(String[] args) {
        out.print("Foot\tMeter\t\tMeter\tFoot\n");
        out.print("-------------------------------------------\n");
        for (int i = 1; i < 11; i++) {
            double m = 15.0 + i * 5;
            out.printf("%.1f \t%.3f\t\t%.1f\t%.3f\n", i + 0.0, footToMeter(i), m, meterToFoot(m));
        }
    }
}
