package Ch4;

import java.util.Scanner;

import static java.lang.Math.*;
import static java.lang.System.out;

public class P4_6 {
    static final double R = 40.0;

    public static void gen(double[] arr) {
        double deg = 2 * PI * random();
        arr[0] = cos(deg) * R;
        arr[1] = sin(deg) * R;
    }

    public static void main(String[] args) {
        double[] pos = new double[2];
        for (int i = 1; i <= 3; i++) {
            out.println("Point " + i);
            gen(pos);
            out.printf("(%.2f, %.2f)\n", pos[0], pos[1]);
        }
    }
}
