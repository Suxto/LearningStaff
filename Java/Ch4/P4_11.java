package Ch4;

import java.util.Scanner;

import static java.lang.System.out;

public class P4_11 {

    public static void main(String[] args) {
        out.print("Enter a decimal value (0 to 15): ");
        int x = new Scanner(System.in).nextInt();
        if (x < 0 || x > 15) {
            out.print(x + " is an in valid input");
            return;
        }
        out.printf("The hex value is %X", x);
    }
}
