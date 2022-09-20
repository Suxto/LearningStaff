package Ch5;

import java.util.Scanner;

import static java.lang.System.out;

public class P5_4 {
    public static void main(String[] args) {
        out.print("Mile\tKilometer\n");
        for (int i = 1; i < 11; i++) {
            out.printf("%d\t\t%f\n", i, i * 1.609);
        }
    }
}
