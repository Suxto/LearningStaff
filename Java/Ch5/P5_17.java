package Ch5;

import java.util.Scanner;

import static java.lang.System.out;

public class P5_17 {
    public static void main(String[] args) {
        out.print("Enter the number of lines: ");
        int lay = new Scanner(System.in).nextInt();
        for (int i = 1; i <= lay; i++) {
            for (int j = 0; j < lay - i; j++) {
                out.print("  ");
            }
            for (int j = i; j > 1; j--) {
                out.print(j + " ");
            }
            for (int j = 1; j <= i; j++) {
                out.print(j + " ");
            }
            out.print('\n');
        }
    }
}
